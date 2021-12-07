package pro.sky.employeeswithmap.service;

import pro.sky.employeeswithmap.data.Employee;
import pro.sky.employeeswithmap.data.FullName;

import java.util.Set;

public interface EmployeeService {
    Employee addEmployee(String firstName, String secondName, String lastName);
    FullName addEmployeeF(String firstName, String secondName, String lastName);

    Employee getEmployee(String firstName, String secondName, String lastName);

    Employee removeEmployee(String firstName, String secondName, String lastName);

    Set<FullName> getEmployeeNames();
}
