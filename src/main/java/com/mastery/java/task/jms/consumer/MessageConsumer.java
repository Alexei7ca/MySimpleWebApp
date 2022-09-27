package com.mastery.java.task.jms.consumer;

import com.mastery.java.task.dao.EmployeeDAO;
import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.rest.EmployeeController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import static com.mastery.java.task.config.JmsConfiguration.EMPLOYEES_QUEUE;

@Component
public class MessageConsumer {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);
    private final EmployeeDAO employeeDAO;

    public MessageConsumer(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }


    @JmsListener(destination = EMPLOYEES_QUEUE)
    //The @JmsListener annotation marks a method to be the target of a JMS message listener on the specified destination
    public void receiveMessageFromEmployeesQueue(Employee employee) {

        logger.info("IN receiveMessageFromEmployeesQueue - params {}", employee);
        employeeDAO.save(employee);
        logger.info("OUT receiveMessageFromEmployeesQueue - params{} ", employee);
    }
}



