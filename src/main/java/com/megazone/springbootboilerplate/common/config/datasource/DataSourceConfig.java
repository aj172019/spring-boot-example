package com.megazone.springbootboilerplate.common.config.datasource;

import com.zaxxer.hikari.HikariConfig;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.config.BeanDefinitionCustomizer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;

import javax.sql.DataSource;
import java.util.Map;

@RequiredArgsConstructor
@ConditionalOnBean(DataSourceProperties.class)
@Configuration
public class DataSourceConfig {

    private static final String DATA_SOURCE_SUFFIX = "DataSource";

    private final DataSourceProperties dataSourceProperties;
    private final GenericApplicationContext applicationContext;

    @PostConstruct
    public void init() {
        for (var entry: dataSourceProperties.groups().entrySet()) {
            String key = entry.getKey();
            var dataSourceInfo = entry.getValue();
            HikariConfig defaultHikariConfig = dataSourceProperties.hikariConfig();
            DataSource dataSource = DataSourceFactory.generateDataSource(key, dataSourceInfo, defaultHikariConfig);
            registerDataSourceBean(entry, dataSource);
        }
    }

    private void registerDataSourceBean(Map.Entry<String, DataSourceProperties.DataSourceGroup> dataSourceInfoEntry, DataSource dataSource) {
        String beanName = dataSourceInfoEntry.getKey() + DATA_SOURCE_SUFFIX;
        var dataSourceInfo = dataSourceInfoEntry.getValue();
        registerDataSourceBeanWithCustomization(dataSource, dataSourceInfo, beanName);
    }

    private void registerDataSourceBeanWithCustomization(DataSource dataSource, DataSourceProperties.DataSourceGroup dataSourceInfo, String beanName) {
        if (dataSourceInfo.isCluster()) {
            applicationContext.getBeanFactory().initializeBean(dataSource, beanName);
            applicationContext.registerBean(beanName, LazyConnectionDataSourceProxy.class, primaryBeanCustomizer(dataSourceInfo, dataSource));
            return;
        }
        applicationContext.registerBean(beanName, DataSource.class, () -> dataSource, primaryBeanCustomizer(dataSourceInfo, dataSource));
    }

    private static BeanDefinitionCustomizer primaryBeanCustomizer(DataSourceProperties.DataSourceGroup dataSourceInfo, DataSource dataSource) {
        return beanDefinition -> {
            beanDefinition.getConstructorArgumentValues().addGenericArgumentValue(dataSource);
            beanDefinition.isSingleton();
            if (dataSourceInfo.primary()) {
                beanDefinition.setPrimary(true);
            }
        };
    }
}
