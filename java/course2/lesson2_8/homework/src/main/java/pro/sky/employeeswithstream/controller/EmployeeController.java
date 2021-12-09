package pro.sky.employeeswithstream.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.employeeswithstream.data.Employee;
import pro.sky.employeeswithstream.data.FullName;
import pro.sky.employeeswithstream.service.EmployeeService;

import java.util.Collection;
import java.util.Set;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/post")
    public Employee addEmployee(@RequestParam String firstName,
                                @RequestParam String secondName,
                                @RequestParam String lastName,
                                @RequestParam int department,
                                @RequestParam int salary
    ) {
        return employeeService.addEmployee(firstName, secondName, lastName, department, salary);
    }

    @GetMapping("/remove")
    public Employee removeEmployee(@RequestParam String firstName,
                                   @RequestParam String secondName,
                                   @RequestParam String lastName) {
        return employeeService.removeEmployee(firstName, secondName, lastName);
    }

    @GetMapping("/find")
    public Employee find(@RequestParam String firstName,
                         @RequestParam String secondName,
                         @RequestParam String lastName) {
        return employeeService.getEmployee(firstName, secondName, lastName);
    }

    @GetMapping("/employeeNames")
    public Set<FullName> getEmployeeSet() {
        return employeeService.getEmployeeNames();
    }

    @GetMapping("employees")
    public Collection<Employee> getEmployeeNames() {
        return employeeService.getEmployees();
    }

}
