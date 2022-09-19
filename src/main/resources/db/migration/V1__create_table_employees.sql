create table employees (
                          employeeId SERIAL,
                          first_name VARCHAR(250),
                          last_name VARCHAR(250),
                          gender VARCHAR(250),
                          department_id INT,
                          job_title VARCHAR(250),
                          date_of_birth DATE,
                          PRIMARY KEY (employeeId)
);