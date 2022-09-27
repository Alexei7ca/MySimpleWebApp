package com.mastery.java.task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.jms.core.JmsTemplate;

@SpringBootApplication
public class EmployeeServiceApp {

    public static void main(String[] args) {
        SpringApplication.run(EmployeeServiceApp.class, args);

    }

}

//        ApplicationContext ctx = SpringApplication.run(EmployeeServiceApp.class, args);
//        JmsTemplate jms = ctx.getBean(JmsTemplate.class);
//        jms.convertAndSend("employeeServiceApp-queue", "test message");