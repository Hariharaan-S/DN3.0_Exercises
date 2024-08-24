package com.employee_management_system.Employee.Management.System.config;


import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;


@Configuration
public class H2DatabaseConfiguration {
    @ConfigurationProperties("spring.datasource.h2database")
    @Bean
    public DataSourceProperties h2DatabaseProperties(){
        return new DataSourceProperties();
    }

    @Bean
    public DataSource h2DataSource() {
        return DataSourceBuilder.create()
                .driverClassName(h2DatabaseProperties().getDriverClassName())
                .url(h2DatabaseProperties().getUrl())
                .username(h2DatabaseProperties().getUsername())
                .password(h2DatabaseProperties().getPassword())
                .build();
    }
}


