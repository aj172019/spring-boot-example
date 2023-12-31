package com.megazone.springbootboilerplate.common.config.web.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.*;

@Slf4j
@RequiredArgsConstructor
@Component
public class LogInterceptor implements HandlerInterceptor {

    public static final List<String> LOG_HEADER_NAMES = List.of(
        //HttpHeaders.ACCEPT,
        //HttpHeaders.USER_AGENT
    );

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        putCommonInfoToMdc(request);
        logRequestBasicInfo(request);
        logRequestHeaderInfo(request);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        logResponseBasicInfo(request);
        clearCommonInfoFromMdc();
    }

    private static void putCommonInfoToMdc(HttpServletRequest request) {
        MDC.put("userId", "SAMPLE_USER_ID");
        MDC.put("userIp", "SAMPLE_USER_IP");
    }

    private static void logRequestBasicInfo(HttpServletRequest request) {
        String method = request.getMethod();
        String requestUri = request.getRequestURI();
        String queryString = request.getQueryString();
        log.info("[{}][{}][{}] Request", method, requestUri, queryString);
    }

    private static void logRequestHeaderInfo(HttpServletRequest request) {
        if (LOG_HEADER_NAMES.isEmpty()) {
            return;
        }
        String method = request.getMethod();
        String requestUri = request.getRequestURI();
        Map<String, String> headerNameValueMap = LOG_HEADER_NAMES.stream()
            .collect(LinkedHashMap::new, (map, name) -> map.put(name, request.getHeader(name)), LinkedHashMap::putAll);
        log.info("[{}][{}] [Request Headers] {}", method, requestUri, headerNameValueMap);
    }

    private static void logResponseBasicInfo(HttpServletRequest request) {
        String method = request.getMethod();
        String requestUri = request.getRequestURI();
        Long responseTime = (Long) request.getAttribute(MetricInterceptor.RESPONSE_TIME);
        log.info("[{}][{}] Response {}ms", method, requestUri, responseTime);
    }

    private static void clearCommonInfoFromMdc() {
        MDC.clear();
    }
}
