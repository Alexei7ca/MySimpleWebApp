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

    private final String scriptGetEmployeesCount = "SELECT COUNT(*) FROM employees;";
    private final String scriptGetEmployees = "SELECT * FROM employees";
    private final String scriptRangeEmployees = "SELECT * FROM employees LIMIT ? OFFSET ?";
    private final String scriptGetEmployeeById = "SELECT * FROM employees WHERE employee_id = ?";
    private final String scriptAddEmployee = "insert into employees(first_name, last_name, gender, department_id, job_title, date_of_birth) values(?,?,?::gender,?,?,?)";
    private final String scriptUpdateEmployee = "update employees set first_name=?,last_name=?,gender=?::gender,department_id=?, job_title=?,date_of_birth=? where employee_id=?";
    private final String scriptDeleteEmployee = "delete from employees where employee_id=?";


    public int getEmployeesCount(){
        return template.queryForObject(scriptGetEmployeesCount, Integer.class);
    }


    public List<Employee> getEmployees() {
//        List<Employee> results = template.query(query, new EmployeeRowMapper());
//        List<Employee> results = template.query(query,BeanPropertyRowMapper.newInstance(Employee.class));
        return template.query(scriptGetEmployees, new BeanPropertyRowMapper<>(Employee.class));
    }

    //how to pass the arguments in browser ->  /employees/range?from=00&count=00
    public List<Employee> getRangeEmployees(int from, int count) {
//        List<Employee> results = template.query( query, new Object[] { count, from }, new EmployeeRowMapper());
//        List<Employee> results = template.query(query, new Object[]{count, from}, BeanPropertyRowMapper.newInstance(Employee.class));
        return template.query(scriptRangeEmployees, new Object[]{count, from}, new BeanPropertyRowMapper<>(Employee.class));
    }

    public Employee getEmployeeById(int employeeId) {
//        Employee employee = template.queryForObject( query, new Object[] { employeeId }, new EmployeeRowMapper());
//        Employee employee = template.queryForObject(query, new Object[]{employeeId}, BeanPropertyRowMapper.newInstance(Employee.class));
        return template.queryForObject(scriptGetEmployeeById, new Object[]{employeeId}, new BeanPropertyRowMapper<>(Employee.class));
    }

    public Employee addEmployee(Employee employee) {
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
        String newEmployeeId = "SELECT MAX(employee_id) FROM employees"; //we find the id of the employee we just added (the max id because it was the last employee to be added)
        int id = template.queryForObject(newEmployeeId, Integer.class); // we populate our variable with the id we just got from the sql query
        return getEmployeeById(id); // we return our employee after finding it via the id
    }

    public Employee updateEmployee(int employeeId, Employee employee) {
        Object[] params = new Object[]{
//                employee.getFirstName(), employee.getLastName(), employee.getGender().toString(), employee.getDepartmentId(), employee.getJobTitle(), employee.getDateOfBirth(), employee.getEmployeeId()
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
        template.update(scriptDeleteEmployee, employeeId);
    }


    //  заполняем объект Employee из объекта ResultSet  // this is what BeanPropertyRowMapper does
//    public static class EmployeeRowMapper implements RowMapper<Employee> {
//        @Override
//        public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
//            Employee employee = new Employee();
//            employee.employeeId = rs.getInt("employeeId");
//            employee.setFirstName(rs.getString("first_name"));
//            employee.setLastName(rs.getString("last_name"));
//            employee.setGender(Gender.valueOf(rs.getString("gender")));
//            employee.setDepartmentId(Integer.parseInt(rs.getString("department_id")));
//            employee.setJobTitle(rs.getString("job_title"));
//            employee.setDateOfBirth(LocalDate.parse(rs.getString("date_of_birth")));
//            return employee;
//        }
//    }

}


//    private void createEmployeeFromTable() {
//
//        var sql = "SELECT * FROM employee";
//
//        var rowMapper = BeanPropertyRowMapper.newInstance(Employee.class);
//        var employee = template.query(sql, rowMapper);
//    }

