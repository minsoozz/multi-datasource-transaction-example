package com.example.batch.job;

import com.example.batch.active.entity.ActiveMember;
import com.example.batch.active.service.ActiveMemberService;
import com.example.batch.config.offset.QuerydslNoOffsetPagingItemReader;
import com.example.batch.config.offset.expression.Expression;
import com.example.batch.config.offset.options.QuerydslNoOffsetNumberOptions;
import com.example.batch.inactive.service.InactiveMemberService;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;

import static com.example.batch.active.entity.QActiveMember.activeMember;

@Configuration
public class MemberConfiguration {

    private final StepBuilderFactory stepBuilderFactory;
    private final JobBuilderFactory jobBuilderFactory;
    private final EntityManagerFactory entityManagerFactory;
    private final ActiveMemberService activeMemberService;
    private final InactiveMemberService inactiveMemberService;
    private final PlatformTransactionManager platformTransactionManager;

    public MemberConfiguration(StepBuilderFactory stepBuilderFactory,
                               JobBuilderFactory jobBuilderFactory,
                               @Qualifier("activeEntityManager") EntityManagerFactory entityManagerFactory,
                               ActiveMemberService activeMemberService,
                               InactiveMemberService inactiveMemberService, PlatformTransactionManager platformTransactionManager) {
        this.stepBuilderFactory = stepBuilderFactory;
        this.jobBuilderFactory = jobBuilderFactory;
        this.entityManagerFactory = entityManagerFactory;
        this.activeMemberService = activeMemberService;
        this.inactiveMemberService = inactiveMemberService;
        this.platformTransactionManager = platformTransactionManager;
    }

    @Bean
    public Job memberJob() {
        return jobBuilderFactory.get("memberJob")
                .start(step())
                .build();
    }

    @Bean
    @JobScope
    public Step step() {
        return stepBuilderFactory.get("memberStep")
                .<ActiveMember, ActiveMember>chunk(100)
                .reader(reader())
                .writer(writer())
                .transactionManager(platformTransactionManager)
                .build();
    }

    @Bean
    @JobScope
    public QuerydslNoOffsetPagingItemReaderJobParameter jobParameter() {
        return new QuerydslNoOffsetPagingItemReaderJobParameter();
    }

    @Bean
    public QuerydslNoOffsetPagingItemReader<ActiveMember> reader() {
        QuerydslNoOffsetNumberOptions<ActiveMember, Long> options =
                new QuerydslNoOffsetNumberOptions<>(activeMember.id, Expression.ASC);

        return new QuerydslNoOffsetPagingItemReader<>(entityManagerFactory, 100, options, jpaQueryFactory ->
                activeMemberService.activeMemberList()
        );
    }

    @Bean
    public ItemWriter<ActiveMember> writer() {
        return activeMembers -> {
            for (ActiveMember member : activeMembers) {
                inactiveMemberService.toInactiveMember(member);
            }
        };
    }
}
