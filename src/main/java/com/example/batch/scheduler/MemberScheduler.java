package com.example.batch.scheduler;

import com.example.batch.quartz.job.MemberQuartzJob;
import lombok.RequiredArgsConstructor;
import org.quartz.*;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class MemberScheduler {

    private final Scheduler scheduler;

    @PostConstruct
    public void start() {
        JobDetail jobDetail = createJobDetail();

        try {
            scheduler.deleteJob(jobDetail.getKey());
            scheduler.scheduleJob(jobDetail, createCronJobTrigger());
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    private JobDetail createJobDetail() {
        Map<String, Object> jobDataMap = new HashMap<>();
        return JobBuilder
                .newJob(MemberQuartzJob.class)
                .withIdentity("회원 -> 휴면회원 전환")
                .withIdentity("일년동안 접속하지 않는 회원은 휴면회원으로 전환한다")
                .usingJobData(new JobDataMap(jobDataMap))
                .build();
    }

    private Trigger createCronJobTrigger() {
        return TriggerBuilder.newTrigger().withSchedule(CronScheduleBuilder.cronSchedule("0/10 * * * * ?")).build();
    }
}
