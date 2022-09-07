package com.mastery.java.task.rest;

import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.service.EmployeeService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/employees")
//@RequestMapping(value = "/employees", produces = MediaType.APPLICATION_XML_VALUE, consumes = MediaType.APPLICATION_XML_VALUE)
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);  //это можно заменить на аннотацию @Slf4j если подключить библиотеку "lombok"


    @ApiOperation(value = "getAllEmployeesOrGetByNameLastName")
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @GetMapping() //  /employees?firstName=&lastName=
    public List<Employee> getAllEmployeesOrGetByNameLastName(@RequestParam(defaultValue = "") String firstName, @RequestParam(defaultValue = "") String lastName) {
        logger.info("GetAllEmployeesOrGetByNameLastName - params: firstName - {}, lastName - {}", firstName, lastName);
        List<Employee> employeesResult = employeeService.getAllEmployeesOrGetEmployeesByNameOrLastName(firstName, lastName);
        return employeesResult;
    }

    @ApiOperation(value = "getEmployeeById")
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @GetMapping("/{employeeId}")
    public Employee getEmployeeById(@PathVariable(value = "employeeId") int employeeId) { // how to break the program by changing this int name???
        logger.info("GetEmployeeById - params: {}", employeeId);
        return employeeService.getEmployeeById(employeeId);
    }


    @ApiOperation(value = "createEmployee")
    @ApiResponse(code = 500, message = "Internal Server Error")
    @PostMapping()
    public Employee createEmployee(@RequestBody @Valid Employee employee) {
        logger.info("CreateEmployee - params: {}", employee);
        return employeeService.createEmployee(employee);
    }


    @ApiOperation(value = "updateEmployee")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @PutMapping("/{employeeId}")
    public Employee updateEmployee(@PathVariable(value = "employeeId") int employeeId, @Valid @RequestBody Employee employee) {  //since the names for the method parameter and the path variable are the same we do not need to add a parameter to @PathVariable
        logger.info("UpdateEmployee - params: Id of employee to be updated {}, new employee params {}", employeeId, employee);
        if (employeeId != employee.getEmployeeId()) {
            throw new IllegalArgumentException("Employee ids do not match");
        }
        return employeeService.updateEmployee(employeeId, employee);
    }


    @ApiOperation(value = "deleteEmployee")
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @DeleteMapping("/{employeeId}")
    public void deleteEmployee(@PathVariable(value = "employeeId") int employeeId) {
        logger.info("DeleteEmployee - params: employeeId{}", employeeId);
        employeeService.deleteEmployee(employeeId);
    }

}
