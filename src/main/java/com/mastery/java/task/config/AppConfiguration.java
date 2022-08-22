package com.mastery.java.task.config;

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
    String className;                     //модификатор доступа?
    @Value("${spring.datasource.url}")
    String url;                             //модификатор доступа?
    @Value("${spring.datasource.username}")
    String username;                        //модификатор доступа?
    @Value("${spring.datasource.password}")
    String password;                        //модификатор доступа?


    //if we were planning to use this anywhere else then it should be a Bean, so we can autowire it
    //ths should have a bean annotation, figure out why
    public DataSource getDataSource() {
        DriverManagerDataSource driver = new DriverManagerDataSource();
        driver.setDriverClassName(className);
        driver.setUrl(url);
        driver.setUsername(username);
        driver.setPassword(password);
        return driver;
    }

//how would this work without the bean annotation??
    @Bean
    public JdbcTemplate getJdbcTemplate() {
        return new JdbcTemplate(getDataSource());
    }
}

