package com.example.batch.quartz.job;

import com.example.batch.job.MemberConfiguration;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.quartz.JobExecutionContext;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class MemberQuartzJob extends QuartzJobBean {

    private final JobLauncher jobLauncher;
    private final MemberConfiguration memberConfiguration;

    @SneakyThrows
    @Override
    protected void executeInternal(JobExecutionContext context) {

        JobParameters jobParameters = new JobParametersBuilder().addString("date", LocalDateTime.now().toString()).toJobParameters();
        jobLauncher.run(memberConfiguration.memberJob(), jobParameters);
    }
}
