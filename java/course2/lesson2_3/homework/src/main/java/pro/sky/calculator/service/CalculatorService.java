package pro.sky.calculator.service;

import pro.sky.calculator.exception.ZeroDivideException;

public interface CalculatorService {
    int plus(int a, int b);

    int minus(int a, int b);

    int multiply(int a, int b);

    int divide(int a, int b) throws ZeroDivideException;
}
