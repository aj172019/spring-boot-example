package com.megazone.springbootboilerplate.common.infra.client.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

@ConfigurationProperties(prefix = "other-service")
public record OtherServiceProperties(
    Map<String, ServiceProperties> services
) {

    public record ServiceProperties(
        String url
    ) {
    }
}
