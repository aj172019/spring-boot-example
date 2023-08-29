package com.megazone.springbootboilerplate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan
@SpringBootApplication
public class SpringbootBoilerplateApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootBoilerplateApplication.class, args);
    }

}
