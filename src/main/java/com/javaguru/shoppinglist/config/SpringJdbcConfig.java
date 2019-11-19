package com.javaguru.shoppinglist.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import javax.xml.crypto.Data;

@Configuration
@ComponentScan(basePackages = "com.javaguru.shoppinglist")
@PropertySource("classpath:application.properties")
public class SpringJdbcConfig {

    @Value("${jdbc.url}")
    private String jdbcUrl;

    @Value("${driverClass}")
    private String driverClass;

    @Value("${database.user.name}")
    private String userName;

    @Value("${database.user.password}")
    private String password;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public DataSource mysqlDataSource() {
        DriverManagerDataSource datasource = new DriverManagerDataSource();
        datasource.setDriverClassName(driverClass);
        datasource.setUrl(jdbcUrl);
        datasource.setUsername(userName);
        datasource.setPassword(password);

        return datasource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(mysqlDataSource());
    }
}
