package pro.sky.employeeset.emplsspring.service.impl;

import org.springframework.stereotype.Service;
import pro.sky.employeeset.emplsspring.data.Employee;
import pro.sky.employeeset.emplsspring.exception.DuplicateEmployeeException;
import pro.sky.employeeset.emplsspring.exception.EmployeeNotFountException;
import pro.sky.employeeset.emplsspring.service.EmployeeService;

import java.util.HashSet;
import java.util.Set;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final Set<Employee> employees = new HashSet<>();

    @Override
    public Employee addEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (employees.add(employee)) {
            return employee;
        }
        throw new DuplicateEmployeeException();
    }

    @Override
    public Employee getEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (employees.contains(employee)) {
            return employee;
        }
        throw new EmployeeNotFountException();
    }

    @Override
    public Employee removeEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (employees.remove(employee)) {
            return employee;
        }
        throw new EmployeeNotFountException();

    }

    @Override
    public Set<Employee> getEmployeeSet() {
        return employees;
    }
}
