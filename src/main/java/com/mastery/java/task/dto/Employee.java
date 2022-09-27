package com.mastery.java.task.dto;

import com.mastery.java.task.annotations.DateOfBirthConstraint;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity  // объект который будет мапиться в таблицу в базе данных
@Table(name = "employees")
public class Employee implements Serializable {  //implemented Serializable so there's no need to override default message converter bean
    public Employee(Integer employeeId, String firstName, String lastName, Gender gender, int departmentId, String jobTitle, LocalDate dateOfBirth) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.departmentId = departmentId;
        this.jobTitle = jobTitle;
        this.dateOfBirth = dateOfBirth;
    }

    public Employee() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //the IDENTITY strategy relies on the DB auto-increment column
    @Column(name = "employee_id")
    private Integer employeeId;

    @Column(name = "first_name")
    @NotBlank(message = "Name is mandatory")
    private String firstName;

    @Column(name = "last_name")
    @NotBlank(message = "Surname is mandatory")
    private String lastName;


    //    It can sometimes be desirable to have a Java enum type to represent a particular column in a database.
//    JPA supports converting database data to and from Java enum types via the @javax.persistence.Enumerated annotation.
    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    @Column(name = "department_id")
    private int departmentId;

    @Column(name = "job_title")
    private String jobTitle;

    @Column(name = "date_of_birth")
    @DateTimeFormat(pattern = "YYYY-MM-DD")
    @DateOfBirthConstraint
    private LocalDate dateOfBirth;


    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }


    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }


    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }


    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee employee)) return false;
        return getDepartmentId() == employee.getDepartmentId() && getEmployeeId().equals(employee.getEmployeeId()) && getFirstName().equals(employee.getFirstName()) && getLastName().equals(employee.getLastName()) && getGender() == employee.getGender() && Objects.equals(getJobTitle(), employee.getJobTitle()) && Objects.equals(getDateOfBirth(), employee.getDateOfBirth());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEmployeeId(), getFirstName(), getLastName(), getGender(), getDepartmentId(), getJobTitle(), getDateOfBirth());
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender=" + gender +
                ", departmentId=" + departmentId +
                ", jobTitle='" + jobTitle + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }
}