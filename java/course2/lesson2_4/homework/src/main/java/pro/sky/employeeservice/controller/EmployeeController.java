package pro.sky.employeeservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.employeeservice.model.Employee;
import pro.sky.employeeservice.service.EmployeeServiceImpl;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeServiceImpl employeeService;

    public EmployeeController(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/add")
    public Employee add(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
        return employeeService.add(firstName, lastName);
    }

    @GetMapping("/remove")
    public Employee delete(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
        return employeeService.delete(firstName, lastName);
    }

    @GetMapping("/find")
    public Employee find(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
        return employeeService.find(firstName, lastName);
    }
}
