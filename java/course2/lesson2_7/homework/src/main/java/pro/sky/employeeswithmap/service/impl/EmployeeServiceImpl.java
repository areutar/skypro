package pro.sky.employeeswithmap.service.impl;

import org.springframework.stereotype.Service;
import pro.sky.employeeswithmap.data.Employee;
import pro.sky.employeeswithmap.data.FullName;
import pro.sky.employeeswithmap.exception.DuplicateEmployeeException;
import pro.sky.employeeswithmap.exception.EmployeeNotFoundException;
import pro.sky.employeeswithmap.service.EmployeeService;

import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private Map<FullName, Employee> employeeMap = new HashMap<>();

    public EmployeeServiceImpl() {
        addEmployee("Василий", "Иванович", "Чапаев");
        addEmployee("Семён", "Михайлович", "Будённый");
        addEmployee("Климент", "Ефремович", "Ворошилов");
    }

    @Override
    public FullName addEmployeeF(String firstName, String secondName, String lastName) {
        FullName fullName = new FullName(firstName, secondName, lastName);
        if (!employeeMap.containsKey(fullName)) {
            Employee employee = new Employee(fullName);
            employeeMap.put(fullName, employee);
            return fullName;
        }
        throw new DuplicateEmployeeException("Такой сотрудник уже есть.");
    }

    @Override
    public Employee addEmployee(String firstName, String secondName, String lastName) {
        FullName fullName = new FullName(firstName, secondName, lastName);
        if (!employeeMap.containsKey(fullName)) {
            Employee employee = new Employee(fullName);
            employeeMap.put(fullName, employee);
            return employee;
        }
        throw new DuplicateEmployeeException("Такой сотрудник уже есть.");
    }

    @Override
    public Employee getEmployee(String firstName, String secondName, String lastName) {
        FullName fullName = new FullName(firstName, secondName, lastName);
        if (employeeMap.containsKey(fullName)) {
            return employeeMap.get(fullName);
        }
        throw new EmployeeNotFoundException();
    }

    @Override
    public Employee removeEmployee(String firstName, String secondName, String lastName) {
        FullName fullName = new FullName(firstName, secondName, lastName);
        if (employeeMap.containsKey(fullName)) {
            Employee employee = employeeMap.get(fullName);
            employeeMap.remove(fullName, employee);
            return employee;
        }
        throw new EmployeeNotFoundException();
    }

    @Override
    public Set<FullName> getEmployeeNames() {
        return Collections.unmodifiableSet(employeeMap.keySet());
    }
}
