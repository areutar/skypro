package pro.sky.employeeswithstream.service;

import pro.sky.employeeswithstream.data.Employee;

import java.util.Collection;

public interface EmployeeService {
    Employee addEmployee(String firstName, String secondName, String lastName, int department, int salary);

    Employee getEmployee(String firstName, String secondName, String lastName);

    Employee removeEmployee(String firstName, String secondName, String lastName);

    Collection<Employee> getEmployees();
}