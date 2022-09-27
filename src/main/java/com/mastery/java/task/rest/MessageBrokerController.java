package com.mastery.java.task.rest;

import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.service.EmployeeService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/messageBroker")
public class MessageBrokerController {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);
    private final EmployeeService employeeService;

    public MessageBrokerController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping()
    @ApiOperation(value = "createEmployee using MessageProducer")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public void createEmployeeUsingMessageBroker(@RequestBody @Valid Employee employee) {
        logger.info("IN createEmployeeUsingMessageBroker - params: {}", employee);
        employeeService.createEmployeeUsingMessageBroker(employee);
        logger.info("OUT createEmployeeUsingMessageBroker - params: {}", employee);
    }
}


