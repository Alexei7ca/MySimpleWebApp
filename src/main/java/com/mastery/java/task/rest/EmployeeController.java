package com.mastery.java.task.rest;

import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.service.EmployeeService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping(value = "/employees")
@Validated
//needed in the controller at the class level, so that Spring can evaluate the constraint annotations on method parameters
//@RequestMapping(value = "/employees", produces = MediaType.APPLICATION_XML_VALUE, consumes = MediaType.APPLICATION_XML_VALUE)
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);  //это можно заменить на аннотацию @Slf4j если подключить библиотеку "lombok"


    @GetMapping() //  /employees?firstName=&lastName=
    @ApiOperation(value = "getAllEmployeesOrGetByNameLastName")
    @ApiResponse(code = 500, message = "Internal Server Error")
    public List<Employee> getAllEmployeesOrGetByNameAndLastName(@RequestParam(defaultValue = "") String firstName, @RequestParam(defaultValue = "") String lastName) {
        logger.info("GetAllEmployeesOrGetByNameLastName - params: firstName - {}, lastName - {}", firstName, lastName);
        return employeeService.getAllEmployeesOrGetEmployeesByNameAndLastName(firstName, lastName);
    }

    @GetMapping("/{employeeId}")
    @ApiOperation(value = "getEmployeeById")
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public Employee getEmployeeById(
            @PathVariable(value = "employeeId") @Min(value = 1, message = "id cannot be 0 or a negative number") int employeeId)
    {
        logger.info("GetEmployeeById - params: {}", employeeId);
        return employeeService.getEmployeeById(employeeId);
    }


    @PostMapping()
    @ApiOperation(value = "createEmployee")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public Employee createEmployee(@RequestBody @Valid Employee employee) {
        logger.info("CreateEmployee - params: {}", employee);
        return employeeService.createEmployee(employee);
    }


    @PutMapping("/{employeeId}")
    @ApiOperation(value = "updateEmployee")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public Employee updateEmployee(@PathVariable(value = "employeeId") @Min(value = 1, message = "id cannot be 0 or a negative number") int employeeId, @Valid @RequestBody Employee employee) {  //since the names for the method parameter and the path variable are the same we do not need to add a parameter to @PathVariable
        logger.info("UpdateEmployee - params: Id of employee to be updated {}, new employee params {}", employeeId, employee);
        if (employeeId != employee.getEmployeeId()) {
            throw new IllegalArgumentException("Employee ids do not match");
        }
        return employeeService.updateEmployee(employeeId, employee);
    }


    @DeleteMapping("/{employeeId}")
    @ApiOperation(value = "deleteEmployee")
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public void deleteEmployee(@PathVariable(value = "employeeId") @Min(value = 1, message = "id cannot be 0 or a negative number") int employeeId) {
        logger.info("DeleteEmployee - params: employeeId{}", employeeId);
        employeeService.deleteEmployee(employeeId);
    }

}
