package com.mastery.java.task.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;

@Configuration
@EnableJms
public class JmsConfiguration {



    public static final String EMPLOYEES_QUEUE = "employeeServiceApp-queue";


//    @Bean
//    public Queue queue(){
//        return new ActiveMQQueue(EMPLOYEES_QUEUE);
//    }

}

