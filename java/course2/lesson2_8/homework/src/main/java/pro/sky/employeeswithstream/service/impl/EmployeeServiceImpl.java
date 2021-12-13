package pro.sky.employeeswithstream.service.impl;

import org.springframework.stereotype.Service;
import pro.sky.employeeswithstream.data.Employee;
import pro.sky.employeeswithstream.exception.DuplicateEmployeeException;
import pro.sky.employeeswithstream.exception.EmployeeNotFoundException;
import pro.sky.employeeswithstream.service.EmployeeService;

import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private Map<String, Employee> employeeMap = new HashMap<>();

    public EmployeeServiceImpl() {
        addEmployee("Василий", "Иванович", "Чапаев", 1, 200);
        addEmployee("Семён", "Михайлович", "Будённый", 1, 150);
        addEmployee("Климент", "Ефремович", "Ворошилов", 2, 300);
        addEmployee("Григорий", "Иванович", "Котовский", 1, 150);
        addEmployee("Михаил", "Васильевич", "Фрунзе", 5, 200);
    }

    private String getKeyString(String firstName, String secondName, String lastName) {
        return firstName + '/' + secondName + '/' + lastName;

    }

    @Override
    public Employee addEmployee(String firstName, String secondName, String lastName, int department, int salary) {
        String key = getKeyString(firstName, secondName, lastName);
        if (!employeeMap.containsKey(key)) {
            Employee employee = new Employee(firstName, secondName, lastName, department, salary);
            employeeMap.put(key, employee);
            return employee;
        }
        throw new DuplicateEmployeeException("Такой сотрудник уже есть.");
    }

    @Override
    public Employee getEmployee(String firstName, String secondName, String lastName) {
        String key = getKeyString(firstName, secondName, lastName);
        if (employeeMap.containsKey(key)) {
            return employeeMap.get(key);
        }
        throw new EmployeeNotFoundException();
    }

    @Override
    public Employee removeEmployee(String firstName, String secondName, String lastName) {
        String key = getKeyString(firstName, secondName, lastName);
        if (employeeMap.containsKey(key)) {
            return employeeMap.remove(key);
        }
        throw new EmployeeNotFoundException();
    }

    @Override
    public Collection<Employee> getEmployees() {
        return Collections.unmodifiableCollection(employeeMap.values());
    }
}
