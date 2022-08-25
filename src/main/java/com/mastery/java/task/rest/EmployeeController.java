package com.mastery.java.task.rest;

import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @GetMapping()
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }


    @GetMapping("/{employeeId}")
    public Employee getEmployeeById(@PathVariable(value = "employeeId") int employeeId) { // how to break the program by changing this int name???
        return employeeService.getEmployeeById(employeeId);
    }


    @PostMapping()
    public Employee createEmployee(@RequestBody Employee employee) {
        if (checkEmployeeAge(employee)) {
            throw new IllegalArgumentException("Date of birth is incorrect");
        }
        return employeeService.createEmployee(employee);
    }


    @PutMapping("/{employeeId}")
    public Employee updateEmployee(@PathVariable(value = "employeeId") int employeeId, @RequestBody Employee employee) {  //since the names for the method parameter and the path variable are the same we do not need to add a parameter to @PathVariable
        if (employeeId != employee.getEmployeeId()) {
            throw new IllegalArgumentException("Employee ids do not match");
        } else if (checkEmployeeAge(employee)) {
            throw new IllegalArgumentException("Date of birth is incorrect");
        }
        return employeeService.updateEmployee(employeeId, employee);
    }


    @DeleteMapping("/{employeeId}")
    public void deleteEmployee(@PathVariable(value = "employeeId") int employeeId) {
        employeeService.deleteEmployee(employeeId);
    }

    public Boolean checkEmployeeAge(Employee employee) {
        boolean condition;
        LocalDate employeeBD = employee.getDateOfBirth();

        LocalDate max = LocalDate.now().minus(18, ChronoUnit.YEARS);
        LocalDate min = LocalDate.now().minus(80, ChronoUnit.YEARS);
        condition = employeeBD == null || employeeBD.isBefore(min) || employeeBD.isAfter(max);  //if (employeeBD == null || employeeBD.isBefore(min) || employeeBD.isAfter(max)) {condition = true;} else {condition = false;}
        return condition;
    }
}
