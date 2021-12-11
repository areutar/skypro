package pro.sky.employeeswithmap.service;

import pro.sky.employeeswithmap.data.Employee;

import java.util.Collection;

public interface EmployeeService {
    Employee addEmployee(String firstName, String secondName, String lastName);

    Employee getEmployee(String firstName, String secondName, String lastName);

    Employee removeEmployee(String firstName, String secondName, String lastName);

    Collection<Employee> getEmployees();
}
