package com.mastery.java.task.service;

import com.mastery.java.task.dao.EmployeeDAO;
import com.mastery.java.task.dto.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeDAO employeeDAO;

    public EmployeeService(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }


    public List<Employee> getAllEmployees() {
        return employeeDAO.getEmployees();
    }

    public Employee getEmployeeById(int employeeId) {
        return employeeDAO.getEmployeeById(employeeId);
    }

    public List<Employee> getRangeEmployees(int from, int count) {
        return employeeDAO.getRangeEmployees(from, count);
    }


    public Integer getEmployeeCount() {
        return employeeDAO.getEmployeesCount();
    }


    public Employee createEmployee(Employee employee) {
        return employeeDAO.addEmployee(employee);
    }


    public Employee updateEmployee(int employeeId, Employee employee) {
        //below is code that HttpMessageConverter already does for you, so it's not needed here
//    public Employee updateEmployee(int employeeId, Employee employee){
//        Employee currentEmployee = employeeDAO.getEmployeeById(employeeId);
//        currentEmployee.setFirstName(employee.getFirstName());
//        currentEmployee.setLastName(employee.getLastName());
//        currentEmployee.setGender(employee.getGender());
//        currentEmployee.setDepartmentId(employee.getDepartmentId());
//        currentEmployee.setJobTitle(employee.getJobTitle());
//        currentEmployee.setDateOfBirth(employee.getDateOfBirth());
//        return this.employeeDAO.updateEmployee(currentEmployee);
        return employeeDAO.updateEmployee(employeeId, employee);
    }

    public void deleteEmployee(int employeeId) {
        employeeDAO.deleteEmployee(employeeId);
    }

}
