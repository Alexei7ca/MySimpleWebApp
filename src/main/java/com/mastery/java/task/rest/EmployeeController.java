package com.mastery.java.task.rest;

import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@RestController
@Validated
@RequestMapping("/")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

@GetMapping("/")
    public String welcome(){
    return "Rest seems to be working";
}


    @ResponseBody
    @GetMapping("/employee/all")
    public List<Employee> getAllEmployees() {
        return this.employeeService.getAllEmployees();
    }

    @ResponseBody
    @GetMapping("/employee")
    public List<Employee> getRangeEmployees(@RequestParam Integer pFrom, @RequestParam Integer pCount) {
        int from = (pFrom != null && pFrom > 0) ? pFrom : 0;  //if (pFrom != null && pFrom>0) from = pFrom;
        int count = (pCount != null && pCount > 0) ? pCount : 0;
        if (count > 100) count = 100;
        return this.employeeService.getRangeEmployees(from, count);
    }


    @GetMapping("/employee/{employeeId}")
    public Employee getEmployeeById(@PathVariable(value = "employeeId")  int employeeId) {
        return employeeService.getEmployeeById(employeeId);
    }


    @ResponseBody
    @RequestMapping(value = "employee/count", method = RequestMethod.GET)
    public Integer getEmployeeCount() {
        return employeeService.getEmployeeCount();
    }

    @PostMapping("/employee")
    public Employee createEmployee(@RequestBody Employee employee) {
        LocalDate employeeBD = employee.getDateOfBirth();
        if (employeeBD == null) throw new IllegalArgumentException("Date of birth is NULL");

        LocalDate max = LocalDate.now().minus(18, ChronoUnit.YEARS);
        LocalDate min = LocalDate.now().minus(80, ChronoUnit.YEARS);
        if (employeeBD.isBefore(min) || employeeBD.isAfter(max))
            throw new IllegalArgumentException("Age out of Boundaries");


        return this.employeeService.createEmployee(employee);
    }


    @PutMapping("/employee/{employeeId}")
    public Employee updateEmployee(@PathVariable(value = "employeeId") int employeeId, @RequestBody Employee employee) {
        LocalDate employeeBD = employee.getDateOfBirth();
        if (employeeBD == null) throw new IllegalArgumentException("Date of birth is NULL");

        LocalDate max = LocalDate.now().minus(18, ChronoUnit.YEARS);
        LocalDate min = LocalDate.now().minus(80, ChronoUnit.YEARS);
        if (employeeBD.isBefore(min) || employeeBD.isAfter(max))
            throw new IllegalArgumentException("Age out of Boundaries");

        return this.employeeService.updateEmployee(employeeId, employee);
    }


    @DeleteMapping("/employee/{employeeId}")
    public void deleteEmployee(@PathVariable(value = "employeeId") int employeeId) {
        this.employeeService.deleteEmployee(employeeId);
    }

}
