package com.mastery.java.task.service;

import com.mastery.java.task.dao.EmployeeDAO;
import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.exceptions.EmployeeServiceNotFoundException;
import com.mastery.java.task.jms.producer.MessageProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeDAO employeeDAO;
    private final MessageProducer messageProducer;

    public EmployeeService(EmployeeDAO employeeDAO, MessageProducer messageProducer) {
        this.employeeDAO = employeeDAO;
        this.messageProducer = messageProducer;
    }

    public Employee getEmployeeById(Integer employeeId) {
        return employeeDAO.findById(employeeId).orElseThrow(() -> new EmployeeServiceNotFoundException("Not found Employee with id = " + employeeId));
    }


    public List<Employee> getAllEmployeesOrGetEmployeesByNameAndLastName(String firstName, String lastName) {
        return employeeDAO.searchByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCase(firstName, lastName);
//        return employeeDAO.getByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCase(firstName, lastName);
//        return employeeDAO.findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCase(firstName, lastName);
    }

    //    @Transactional is already in the realization of the code in SimpleJPARepository
    public Employee createEmployee(Employee employee) {
        return employeeDAO.save(employee);
    }

    @Transactional
    //I need this here bcuz I have 2 actions (1- take out employee, 1- save employee | editing the employee does not happen in the db)
    public Employee updateEmployee(int employeeId, Employee employeeDetails) {
            Employee currentEmployee = employeeDAO.findById(employeeId).orElseThrow(() ->new EmployeeServiceNotFoundException("Not found Employee with id = " + employeeId));
            currentEmployee.setFirstName(employeeDetails.getFirstName());
            currentEmployee.setLastName(employeeDetails.getLastName());
            currentEmployee.setGender(employeeDetails.getGender());
            currentEmployee.setDepartmentId(employeeDetails.getDepartmentId());
            currentEmployee.setJobTitle(employeeDetails.getJobTitle());
            currentEmployee.setDateOfBirth(employeeDetails.getDateOfBirth());
            return employeeDAO.save(currentEmployee);
    }

    public void deleteEmployee(int employeeId) {
        try {
            employeeDAO.deleteById(employeeId);
        }
        catch(Exception e) {
            throw new EmployeeServiceNotFoundException("Not found Employee with id = " + employeeId);
        }
    }

    public void createEmployeeUsingMessageBroker(Employee employee) {
        messageProducer.createEmployeeUsingMessageBroker(employee);
    }

}
