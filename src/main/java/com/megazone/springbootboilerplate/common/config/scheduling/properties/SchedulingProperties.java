package com.megazone.springbootboilerplate.common.config.scheduling.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.util.Map;

@ConfigurationProperties(prefix = "scheduling")
public record SchedulingProperties(
    boolean enabled,
    Map<String, SchedulingGroup> groups
) {

    public SchedulingProperties(
        @DefaultValue("true") boolean enabled,
        @DefaultValue Map<String, SchedulingGroup> groups
    ) {
        this.enabled = enabled;
        this.groups = groups;
    }

    public boolean containsGroup(String name) {
        return groups.containsKey(name);
    }

    public boolean isGroupEnabled(String name) {
        if (!containsGroup(name)) {
            return true;
        }
        SchedulingGroup group = groups.get(name);
        return group.enabled;
    }


    private record SchedulingGroup(boolean enabled) {

        public SchedulingGroup(
            @DefaultValue("true") boolean enabled
        ) {
            this.enabled = enabled;
        }
    }
}
