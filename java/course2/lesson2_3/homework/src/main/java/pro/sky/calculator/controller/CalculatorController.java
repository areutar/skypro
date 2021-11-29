package pro.sky.calculator.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pro.sky.calculator.exception.ZeroDivideException;
import pro.sky.calculator.service.CalculatorService;

@RestController
@RequestMapping("/calculator")

public class CalculatorController {
    private final CalculatorService calculatorService;

    public CalculatorController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    @GetMapping
    public String greet() {
        return "Добро пожаловать в калькулятор";
    }

    @GetMapping("/plus")
    public String plus(@RequestParam int a, @RequestParam int b) {
        return a + " + " + b + " = " + calculatorService.plus(a, b);
    }

    @GetMapping("/minus")
    public String minus(@RequestParam int a, @RequestParam int b) {
        return a + " - " + b + " = " + calculatorService.minus(a, b);
    }

    @GetMapping("/multiply")
    public String multiply(@RequestParam int a, @RequestParam int b) {
        return a + " * " + b + " = " + calculatorService.multiply(a, b);
    }

    @GetMapping("/divide")
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String divide(@RequestParam int a, @RequestParam int b) {
        try {
            return a + " / " + b + " = " + calculatorService.divide(a, b);
        } catch (ZeroDivideException zeroDivideException) {
            return zeroDivideException.getMessage();
        }
    }
}
