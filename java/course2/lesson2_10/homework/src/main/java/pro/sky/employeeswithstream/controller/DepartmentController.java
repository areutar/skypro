package pro.sky.employeeswithstream.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.employeeswithstream.data.Employee;
import pro.sky.employeeswithstream.service.DepartmentService;

import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/max-salary")
    public Employee getMaxSalaryEmployee(@RequestParam Integer departmentId) {
        return departmentService.getMaxSalaryEmployee(departmentId);
    }

    @GetMapping("/min-salary")
    public Employee getMinSalaryEmployee(@RequestParam Integer departmentId) {
        return departmentService.getMinSalaryEmployee(departmentId);
    }

    @GetMapping("/all")
    public List<Employee> getAllEmployeesInDepartment(@RequestParam(required = false) Integer departmentId) {
        return departmentService.getAllEmployees(departmentId);
    }

}