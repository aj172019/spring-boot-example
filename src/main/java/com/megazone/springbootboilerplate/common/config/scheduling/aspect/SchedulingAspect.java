package com.megazone.springbootboilerplate.common.config.scheduling.aspect;

import com.megazone.springbootboilerplate.common.config.scheduling.annotation.SchedulingGrouping;
import com.megazone.springbootboilerplate.common.config.scheduling.properties.SchedulingProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@Slf4j
@RequiredArgsConstructor
@Aspect
@Component
public class SchedulingAspect {

    private final SchedulingProperties schedulingProperties;

    @Around("@annotation(org.springframework.scheduling.annotation.Scheduled)")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        String classMethodName = joinPoint.getSignature().toShortString();
        boolean isSchedulingEnabled = false;
        try {
            isSchedulingEnabled = isSchedulingEnabled(joinPoint);
            if (isSchedulingEnabled) {
                log.info("스케줄러 시작: {}", classMethodName);
                return joinPoint.proceed(); //스케줄러 실행
            }
            //TODO - 스케줄러를 미실행할 때, @SchedulerLock도 동작하지 않도록 제어 필요
            return null; //스케줄러 미실행
        } catch (Exception e) {
            log.error("스케줄러 오류: {}", classMethodName, e);
            throw e;
        } finally {
            if (isSchedulingEnabled) {
                log.info("스케줄러 종료: {}", classMethodName);
            }
        }
    }

    private boolean isSchedulingEnabled(JoinPoint joinPoint) {
        return isAllSchedulingEnabled() && isGroupSchedulingEnabled(joinPoint);
    }

    private boolean isAllSchedulingEnabled() {
        return schedulingProperties.enabled();
    }

    private boolean isGroupSchedulingEnabled(JoinPoint joinPoint) {
        SchedulingGrouping annotation = getSchedulingGroupingAnnotation(joinPoint);
        if (annotation == null) {
            return true;
        }
        return Stream.of(annotation.value())
            .allMatch(schedulingProperties::isGroupEnabled);
    }

    private static SchedulingGrouping getSchedulingGroupingAnnotation(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Class<?> declaringClass = signature.getMethod().getDeclaringClass();
        return declaringClass.getAnnotation(SchedulingGrouping.class);
    }
}
