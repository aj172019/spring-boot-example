package com.megazone.springbootboilerplate.common.config.cache;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator;
import com.fasterxml.jackson.databind.jsontype.PolymorphicTypeValidator;
import com.megazone.springbootboilerplate.common.utils.EnvironmentUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.cache.support.NoOpCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class CacheConfig implements CachingConfigurer {

    private final EnvironmentUtils environmentUtils;

    //@Profile("local")
    //@Bean
    public CacheManager noRedisCacheManager() {
        log.info(">>> CacheManager is not configured");
        return new NoOpCacheManager();
    }

    //@Profile("!local")
    @Bean
    public CacheManager redisCacheManager(RedisConnectionFactory redisConnectionFactory) {
        String prefixCacheName = getPrefixCacheName();
        log.info(">>> CacheManager is configured with redis: Prefix={}", prefixCacheName);

        ObjectMapper mapper = createObjectMapperForSerializer();
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration
            .defaultCacheConfig()
            .prefixCacheNameWith(prefixCacheName)
            .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
            .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer(mapper)));

        Map<String, RedisCacheConfiguration> redisCacheConfigMap = new HashMap<>();

        return RedisCacheManager.RedisCacheManagerBuilder
            .fromConnectionFactory(redisConnectionFactory)
            .cacheDefaults(redisCacheConfiguration)
            .withInitialCacheConfigurations(redisCacheConfigMap)
            .build();
    }

    private String getPrefixCacheName() {
        String prefixCacheName = environmentUtils.getApplicationName() + "::";
        if (environmentUtils.isLocalProfile()) {
            return environmentUtils.getLocalProfile() + ":" + prefixCacheName;
        }
        return prefixCacheName;
    }

    private ObjectMapper createObjectMapperForSerializer() {
        PolymorphicTypeValidator ptv = BasicPolymorphicTypeValidator.builder()
            .allowIfSubType(Object.class)
            .build();
        return new ObjectMapper()
            .activateDefaultTyping(ptv, ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY)
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Override
    public CacheErrorHandler errorHandler() {
        return new CustomRedisCacheErrorHandler();
    }


    @Slf4j
    public static class CustomRedisCacheErrorHandler implements CacheErrorHandler {

        @Override
        public void handleCacheGetError(RuntimeException e, Cache cache, Object key) {
            log.error("Unable to get from cache. key: {}, name: {}", key, cache.getName(), e);
        }

        @Override
        public void handleCachePutError(RuntimeException e, Cache cache, Object key, Object value) {
            log.error("Unable to put into cache. key: {}, name: {}", key, cache.getName(), e);
        }

        @Override
        public void handleCacheEvictError(RuntimeException e, Cache cache, Object key) {
            log.error("Unable to evict from cache. key: {}, name: {}", key, cache.getName(), e);
        }

        @Override
        public void handleCacheClearError(RuntimeException e, Cache cache) {
            log.error("Unable to clean cache. name: {}", cache.getName(), e);
        }
    }
}
