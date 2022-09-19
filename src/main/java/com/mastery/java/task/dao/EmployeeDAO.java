package com.mastery.java.task.dao;

import com.mastery.java.task.dto.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository //not needed but is recommended for readability.
// Spring sees that we extend JpaRepository and creates bean SimpleJpaRepository (the class that has annotation @repository)
public interface EmployeeDAO extends JpaRepository<Employee, Integer> {

    List<Employee> findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCase(String firstName, String lastName);
    List<Employee> getByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCase(String firstName, String lastName);
    List<Employee> searchByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCase(String firstName, String lastName);
}

//findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCase как это выглядет на sql