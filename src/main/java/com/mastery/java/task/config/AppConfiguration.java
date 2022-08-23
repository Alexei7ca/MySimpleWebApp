package com.mastery.java.task.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;


@Configuration
@EnableWebMvc
@PropertySource("classpath:/application.properties")
@ComponentScan("com.mastery.java.task")
public class AppConfiguration {
    @Value("${spring.datasource.class}")
    private String className;
    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;


    //if we were planning to use this anywhere else then it should be a Bean, so we can autowire it
    //ths should have a bean annotation, figure out why
    @Bean
    public DataSource getDataSource() {
        DriverManagerDataSource driver = new DriverManagerDataSource();
        driver.setDriverClassName(className);
        driver.setUrl(url);
        driver.setUsername(username);
        driver.setPassword(password);
        return driver;
    }

    @Bean
    public DataSource secondDataSource() {
        DriverManagerDataSource driver = new DriverManagerDataSource();
        driver.setDriverClassName(className);
        driver.setUrl(url);
        driver.setUsername(username);
        driver.setPassword(password);
        return driver;
    }

    @Bean
    public JdbcTemplate getJdbcTemplate(@Qualifier("getDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}

