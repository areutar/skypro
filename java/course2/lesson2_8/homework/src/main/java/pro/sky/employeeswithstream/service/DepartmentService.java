package pro.sky.employeeswithstream.service;

import pro.sky.employeeswithstream.data.Employee;

import java.util.List;

public interface DepartmentService {
    Employee getMaxSalaryEmployee(Integer department);

    Employee getMinSalaryEmployee(Integer department);

    List<Employee> getAllEmployees(Integer department);
}
