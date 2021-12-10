package pro.sky.employeeswithstream.service;

import pro.sky.employeeswithstream.data.Employee;
import pro.sky.employeeswithstream.data.FullName;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

public interface EmployeeService {
    Employee addEmployee(String firstName, String secondName, String lastName, int department, int salary);

    Employee getEmployee(String firstName, String secondName, String lastName);

    Employee removeEmployee(String firstName, String secondName, String lastName);

    Set<FullName> getEmployeeNames();

    Collection<Employee> getEmployees();
}
