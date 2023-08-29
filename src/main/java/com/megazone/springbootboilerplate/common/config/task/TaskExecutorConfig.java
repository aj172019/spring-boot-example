package com.megazone.springbootboilerplate.common.config.task;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@EnableAsync
@Configuration
public class TaskExecutorConfig {

    //@Bean
    public TaskExecutor defaultTaskExecutor() {
        ThreadPoolTaskExecutor threadPoolExecutor = new ThreadPoolTaskExecutor();
        threadPoolExecutor.setCorePoolSize(20);
        threadPoolExecutor.setMaxPoolSize(50);
        threadPoolExecutor.setQueueCapacity(100);
        //threadPoolExecutor.setThreadNamePrefix("task-");
        threadPoolExecutor.setWaitForTasksToCompleteOnShutdown(true);
        threadPoolExecutor.setAwaitTerminationSeconds(30);
        //threadPoolExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        threadPoolExecutor.initialize();
        return threadPoolExecutor;
    }
}
