package com.mastery.java.task.jms.producer;

import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.rest.EmployeeController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import static com.mastery.java.task.config.JmsConfiguration.EMPLOYEES_QUEUE;

@Component
public class MessageProducer {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);
    private final JmsTemplate jmsTemplate;



    public MessageProducer(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void createEmployeeUsingMessageBroker(Employee employee) {
        logger.info("IN createEmployeeUsingMessageBroker - params: {}", employee);
        jmsTemplate.convertAndSend(EMPLOYEES_QUEUE, employee);
        logger.info("OUT createEmployeeUsingMessageBroker - params: {}", employee);
    }
}