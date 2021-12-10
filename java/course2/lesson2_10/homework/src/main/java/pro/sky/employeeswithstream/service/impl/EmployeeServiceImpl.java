package pro.sky.employeeswithstream.service.impl;

import org.springframework.stereotype.Service;
import pro.sky.employeeswithstream.data.Employee;
import pro.sky.employeeswithstream.data.FullName;
import pro.sky.employeeswithstream.exception.DuplicateEmployeeException;
import pro.sky.employeeswithstream.exception.EmployeeNotFoundException;
import pro.sky.employeeswithstream.exception.IllegalNameException;
import pro.sky.employeeswithstream.service.EmployeeService;

import java.util.*;

import static org.apache.commons.lang3.StringUtils.capitalize;
import static org.apache.commons.lang3.StringUtils.isAlpha;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private Map<FullName, Employee> employeeMap = new HashMap<>();

    public EmployeeServiceImpl() {
        addEmployee("Василий", "Иванович", "Чапаев", 1, 200);
        addEmployee("Семён", "Михайлович", "Будённый", 1, 150);
        addEmployee("Климент", "Ефремович", "Ворошилов", 2, 300);
        addEmployee("Григорий", "Иванович", "Котовский", 1, 150);
        addEmployee("Михаил", "Васильевич", "Фрунзе", 5, 200);
    }

    @Override
    public Employee addEmployee(String firstName, String secondName, String lastName,
                                int department, int salary) {
        checkAlpha(firstName, secondName, lastName);
        FullName fullName = new FullName(capitalize(firstName), capitalize(secondName), capitalize(lastName));

        if (!employeeMap.containsKey(fullName)) {
            Employee employee = new Employee(fullName, department, salary);
            employeeMap.put(fullName, employee);
            return employee;
        }
        throw new DuplicateEmployeeException("Такой сотрудник уже есть.");
    }

    private void checkAlpha(String firstName, String secondName, String lastName) {
        if (!isAlpha(firstName)|| !isAlpha(secondName)|| !isAlpha(lastName)) {
            throw new IllegalNameException();
        }
    }

    @Override
    public Employee getEmployee(String firstName, String secondName, String lastName) {
        checkAlpha(firstName, secondName, lastName);
        FullName fullName = new FullName(firstName, secondName, lastName);
        if (employeeMap.containsKey(fullName)) {
            return employeeMap.get(fullName);
        }
        throw new EmployeeNotFoundException();
    }

    @Override
    public Employee removeEmployee(String firstName, String secondName, String lastName) {
        checkAlpha(firstName, secondName, lastName);
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

    @Override
    public Collection<Employee> getEmployees() {
        return Collections.unmodifiableCollection(employeeMap.values());
    }
}
