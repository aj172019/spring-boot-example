package com.megazone.springbootboilerplate.common.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class EnvironmentUtils {

    private static final String LOCAL_PROFILE = "local";

    private final Environment env;

    @Value("${spring.application.name}")
    private String applicationName;

    public List<String> getActiveProfiles() {
        return List.of(env.getActiveProfiles());
    }

    public String getActiveProfile() {
        return getActiveProfiles().stream()
            .findFirst()
            .orElseThrow(() -> new IllegalStateException("No active profiles"));
    }

    public String getLocalProfile() {
        return LOCAL_PROFILE;
    }

    public boolean isLocalProfile() {
        return getActiveProfile().equals(getLocalProfile());
    }

    public String getApplicationName() {
        return applicationName;
    }
}
