package com.megazone.springbootboilerplate.common.config.scheduling;

import com.megazone.springbootboilerplate.common.utils.EnvironmentUtils;
import lombok.RequiredArgsConstructor;
import net.javacrumbs.shedlock.core.LockProvider;
import net.javacrumbs.shedlock.provider.redis.spring.RedisLockProvider;
import net.javacrumbs.shedlock.spring.annotation.EnableSchedulerLock;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableSchedulerLock(defaultLockAtMostFor = "10s")
@EnableScheduling
@RequiredArgsConstructor
@Configuration
public class SchedulingConfig {

    private final EnvironmentUtils environmentUtils;

    //@Bean
    //public LockProvider lockProvider(DataSource dataSource) {
    //    return new JdbcTemplateLockProvider(dataSource);
    //}

    @Bean
    public LockProvider lockProvider(RedisConnectionFactory connectionFactory) {
        return new RedisLockProvider.Builder(connectionFactory)
            .environment(getEnvironmentName())
            .keyPrefix(getKeyPrefix())
            .build();
    }

    private String getKeyPrefix() {
        return environmentUtils.getApplicationName(); //서비스명을 기본 Prefix로 지정
    }

    private String getEnvironmentName() {
        String env = environmentUtils.isLocalProfile() ? ":" + environmentUtils.getLocalProfile() : ""; //로컬 환경에서만 Key에 환경 스트링 추가
        return env + ":SchedulerLock";
    }
}
