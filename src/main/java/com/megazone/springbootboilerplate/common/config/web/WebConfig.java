package com.megazone.springbootboilerplate.common.config.web;

import com.megazone.springbootboilerplate.common.config.web.interceptor.LogInterceptor;
import com.megazone.springbootboilerplate.common.config.web.interceptor.MetricInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    public static final String[] ALLOWED_ORIGINS = {
        "http://front-end.com"
    };

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LogInterceptor()).order(1);
        registry.addInterceptor(new MetricInterceptor()).order(2);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins(ALLOWED_ORIGINS);
    }
}
