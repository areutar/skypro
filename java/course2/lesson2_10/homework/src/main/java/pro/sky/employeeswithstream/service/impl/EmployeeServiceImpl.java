package pro.sky.employeeswithstream.service.impl;

import org.springframework.stereotype.Service;
import pro.sky.employeeswithstream.data.Employee;
import pro.sky.employeeswithstream.exception.DuplicateEmployeeException;
import pro.sky.employeeswithstream.exception.EmployeeNotFoundException;
import pro.sky.employeeswithstream.exception.IllegalNameException;
import pro.sky.employeeswithstream.service.EmployeeService;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.apache.commons.lang3.StringUtils.capitalize;
import static org.apache.commons.lang3.StringUtils.isAlpha;

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

    private void checkAlpha(String firstName, String secondName, String lastName) {
        if (!isAlpha(firstName) || !isAlpha(secondName) || !isAlpha(lastName)) {
            throw new IllegalNameException();
        }
    }

    @Override
    public Employee addEmployee(String fName, String sName, String lName,
                                int department, int salary) {
        checkAlpha(fName, sName, lName);

        String firstName = capitalize(fName);
        String secondName = capitalize(sName);
        String lastName = capitalize(lName);

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
        checkAlpha(firstName, secondName, lastName);
        String key = getKeyString(firstName, secondName, lastName);
        if (employeeMap.containsKey(key)) {
            return employeeMap.get(key);
        }
        throw new EmployeeNotFoundException();
    }

    @Override
    public Employee removeEmployee(String firstName, String secondName, String lastName) {
        checkAlpha(firstName, secondName, lastName);

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
