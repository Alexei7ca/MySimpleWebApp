package com.mastery.java.task.dao;

import com.mastery.java.task.dto.Employee;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.List;

@Repository
public class EmployeeDAO {

    private final JdbcTemplate template;

    //    @Autowired    //annotation not needed when using injection via constructor
    public EmployeeDAO(JdbcTemplate template) {
        this.template = template;
    }


    public List<Employee> getEmployees() {
        String scriptGetEmployees = "SELECT * FROM employees";
        return template.query(scriptGetEmployees, new BeanPropertyRowMapper<>(Employee.class));
    }

    public Employee getEmployeeById(int employeeId) {
        String scriptGetEmployeeById = "SELECT * FROM employees WHERE employee_id = ?";
        return template.queryForObject(scriptGetEmployeeById, new Object[]{employeeId}, new BeanPropertyRowMapper<>(Employee.class));
    }

    public Employee addEmployee(Employee employee) {
        String scriptAddEmployee = "insert into employees(first_name, last_name, gender, department_id, job_title, date_of_birth) values(?,?,?::gender,?,?,?)";
        Object[] params = new Object[]{
                employee.getFirstName(), employee.getLastName(), employee.getGender().toString(), employee.getDepartmentId(), employee.getJobTitle(), employee.getDateOfBirth()
        };
        int[] types = new int[]{
                Types.VARCHAR,
                Types.VARCHAR,
                Types.VARCHAR,
                Types.INTEGER,
                Types.VARCHAR,
                Types.DATE
        };
        template.update(scriptAddEmployee, params, types);
        return employee;
    }

    public Employee updateEmployee(int employeeId, Employee employee) {
        String scriptUpdateEmployee = "update employees set first_name=?,last_name=?,gender=?::gender,department_id=?, job_title=?,date_of_birth=? where employee_id=?";
        Object[] params = new Object[]{
                employee.getFirstName(), employee.getLastName(), employee.getGender().toString(), employee.getDepartmentId(), employee.getJobTitle(), employee.getDateOfBirth(), employeeId
        };
        int[] types = new int[]{
                Types.VARCHAR,
                Types.VARCHAR,
                Types.VARCHAR,
                Types.INTEGER,
                Types.VARCHAR,
                Types.DATE,
                Types.INTEGER
        };

        template.update(scriptUpdateEmployee, params, types);
        return employee;
    }

    public void deleteEmployee(int employeeId) {

        String scriptDeleteEmployee = "delete from employees where employee_id=?";
        template.update(scriptDeleteEmployee, employeeId);
    }
}

