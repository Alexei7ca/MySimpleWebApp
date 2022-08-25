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
        return this.employeeDAO.getEmployees();
    } //do we need "this" here??

    public Employee getEmployeeById( int employeeId) {
        return employeeDAO.getEmployeeById(employeeId);
    }

    public List<Employee> getRangeEmployees(int from, int count) {
        return this.employeeDAO.getRangeEmployees(from, count);
    }


    public Integer getEmployeeCount(){
        return employeeDAO.getEmployeesCount();
    }


    public Employee createEmployee(Employee employee) {
        return this.employeeDAO.addEmployee(employee);
    }


    public Employee updateEmployee(int employeeId, Employee employee){
//    public Employee updateEmployee(int employeeId, Employee employee){
//        Employee currentEmployee = employeeDAO.getEmployeeById(employeeId);
//        currentEmployee.setFirstName(employee.getFirstName());
//        currentEmployee.setLastName(employee.getLastName());
//        currentEmployee.setGender(employee.getGender());
//        currentEmployee.setDepartmentId(employee.getDepartmentId());
//        currentEmployee.setJobTitle(employee.getJobTitle());
//        currentEmployee.setDateOfBirth(employee.getDateOfBirth());
//        return this.employeeDAO.updateEmployee(currentEmployee);
        return this.employeeDAO.updateEmployee(employeeId, employee);
    }

    public void deleteEmployee(int employeeId) {
        this.employeeDAO.deleteEmployee(employeeId);
    }

}
