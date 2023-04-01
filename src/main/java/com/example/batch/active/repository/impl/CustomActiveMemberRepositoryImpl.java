package com.example.batch.active.repository.impl;

import com.example.batch.active.entity.ActiveMember;
import com.example.batch.active.repository.CustomActiveMemberRepository;
import com.example.batch.config.querydsl.ActiveQuerydslRepositorySupport;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import static com.example.batch.active.entity.QActiveMember.activeMember;

@Repository
public class CustomActiveMemberRepositoryImpl extends ActiveQuerydslRepositorySupport implements CustomActiveMemberRepository {

    private final JPAQueryFactory activeJpaQueryFactory;

    public CustomActiveMemberRepositoryImpl(JPAQueryFactory activeJpaQueryFactory) {
        super(ActiveMember.class);
        this.activeJpaQueryFactory = activeJpaQueryFactory;
    }

    @Override
    public JPAQuery<ActiveMember> getAll() {
        return activeJpaQueryFactory.selectFrom(activeMember);
    }
}
