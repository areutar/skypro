package pro.sky.employeeservice.service;

import org.springframework.stereotype.Service;
import pro.sky.employeeservice.exception.ArrayEmployeesOverflowException;
import pro.sky.employeeservice.exception.EmployeeNotFoundException;
import pro.sky.employeeservice.model.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    public static final int maxNumberEmployees = 6;
    private int spareIndex = 3;
    private final Employee[] employees = {
            new Employee("Иван", "Бунин"),
            new Employee("Степан", "Жулин"),
            new Employee("Харитон", "Платов"),
            null,
            new Employee("Charles", "Sitka"),
            new Employee("Родион", "Нахапетов")
    };

    @Override
    public Employee add(String firstName, String lastName) {
        if (spareIndex >= 0) {
            Employee employee = new Employee(firstName, lastName);
            employees[spareIndex] = employee;
            spareIndex = findSpareIndex();
            return employee;
        }
        throw new ArrayEmployeesOverflowException();
    }

    @Override
    public Employee find(String firstName, String lastName) {
        int index = findEmployeeIndex(firstName, lastName);
        if (index >= 0) {
            return employees[index];
        }
        throw new EmployeeNotFoundException();
    }

    @Override
    public Employee delete(String firstName, String lastName) {
        int indexToDel = findEmployeeIndex(firstName, lastName);
        if (indexToDel < 0) {
            throw new EmployeeNotFoundException();
        }
        Employee employee = employees[indexToDel];
        employees[indexToDel] = null;
        spareIndex = findSpareIndex();
        return employee;
    }

    private int findEmployeeIndex(String firstName, String lastName) {
        for (int i = 0; i < maxNumberEmployees; i++) {
            if (employees[i] != null
                    && employees[i].getFirstName().equals(firstName)
                    && employees[i].getLastName().equals(lastName)) {
                return i;
            }
        }
        return -1;
    }

    private int findSpareIndex() {
        spareIndex = -1;
        for (int i = 0; i < maxNumberEmployees; i++) {
            if (employees[i] == null) {
                spareIndex = i;
                break;
            }
        }
        return spareIndex;
    }

}
