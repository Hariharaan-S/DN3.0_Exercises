package com.employee_management_system.Employee.Management.System.config;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class MySqlDatabaseConfiguration {

    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource.mysql")
    public DataSourceProperties mySqlDatabaseProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    public DataSource mySqlDataSource(DataSourceProperties mySqlDatabaseProperties) {
        return mySqlDatabaseProperties.initializeDataSourceBuilder().build();
    }
}
