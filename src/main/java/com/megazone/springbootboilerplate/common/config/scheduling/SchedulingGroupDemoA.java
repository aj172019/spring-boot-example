package com.megazone.springbootboilerplate.common.config.scheduling;

import com.megazone.springbootboilerplate.common.config.scheduling.annotation.SchedulingGrouping;
import com.megazone.springbootboilerplate.common.utils.DateTimeUtils;
import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@SchedulingGrouping({"demoA"})
public class SchedulingGroupDemoA {

    @Scheduled(cron = "*/10 * * * * *")
    @SchedulerLock(name = "schedulerLockA", lockAtMostFor = "9s", lockAtLeastFor = "9s")
    public void scheduleA() {
        log.info("[{}] 스케줄러 실행: {}", Thread.currentThread().getName(), DateTimeUtils.nowFormat());
    }

}
