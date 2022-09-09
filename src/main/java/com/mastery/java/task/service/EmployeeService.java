package com.mastery.java.task.service;

import com.mastery.java.task.dao.EmployeeDAO;
import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.exceptions.EmployeeServiceNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeDAO employeeDAO;

    public EmployeeService(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    public Employee getEmployeeById(Integer employeeId) {
        return employeeDAO.findById(employeeId).orElseThrow(() -> new EmployeeServiceNotFoundException("Not found Employee with id = " + employeeId));
    }


    public List<Employee> getAllEmployeesOrGetEmployeesByNameOrLastName(String firstName, String lastName) {
        List<Employee> employeesNameLastNameMatch = employeeDAO.findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCase(firstName, lastName);
        if (employeesNameLastNameMatch.isEmpty()) {
            throw new EmployeeServiceNotFoundException("Employee Not found");
        }
        return employeesNameLastNameMatch;
    }

    //    @Transactional is already in the realization of the code in SimpleJPARepository
    public Employee createEmployee(Employee employee) {
        return employeeDAO.save(employee);
    }

    @Transactional
    //I need this here bcuz I have 2 actions (1- take out employee, 1- save employee | editing the employee does not happen in the db)
    public Employee updateEmployee(int employeeId, Employee employeeDetails) {
        if (employeeDAO.findById(employeeId).isPresent()) {
            Employee currentEmployee = getEmployeeById(employeeId);
            currentEmployee.setFirstName(employeeDetails.getFirstName());
            currentEmployee.setLastName(employeeDetails.getLastName());
            currentEmployee.setGender(employeeDetails.getGender());
            currentEmployee.setDepartmentId(employeeDetails.getDepartmentId());
            currentEmployee.setJobTitle(employeeDetails.getJobTitle());
            currentEmployee.setDateOfBirth(employeeDetails.getDateOfBirth());
            return employeeDAO.save(currentEmployee);
        } else throw new EmployeeServiceNotFoundException("Not found Employee with id = " + employeeId);
    }

    public void deleteEmployee(int employeeId) {
        employeeDAO.deleteById(employeeId);
    }

}
