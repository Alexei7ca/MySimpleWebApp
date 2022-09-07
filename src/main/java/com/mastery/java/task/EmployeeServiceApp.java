package com.mastery.java.task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@Import(AppConfiguration.class)  //if we need to explicitly use this configuration, (if the class has @Configuration this is not necessary)
public class EmployeeServiceApp {
    public static void main(String[] args) {
        SpringApplication.run(EmployeeServiceApp.class, args);
    }
}
