package com.megazone.springbootboilerplate.common.config.mybatis;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.TypeHandler;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@RequiredArgsConstructor
@MapperScan(basePackages = "com.megazone.springbootboilerplate.*.infra.mapper")
@Configuration
public class MybatisConfig {

    private final List<TypeHandler<?>> typeHandlers;

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource, ApplicationContext context) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setTypeAliasesPackage("com.megazone.springbootboilerplate.*.domain, com.megazone.springbootboilerplate.*.service.dto.response.query");
        factoryBean.setMapperLocations(context.getResources("classpath:com/megazone/springbootboilerplate/*/infra/mapper/*.xml"));
        factoryBean.setTypeHandlers(typeHandlers.toArray(TypeHandler[]::new));
        return factoryBean.getObject();
    }
}
