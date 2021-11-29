package pro.sky.calculator.service;

import org.springframework.stereotype.Service;
import pro.sky.calculator.exception.ZeroDivideException;

@Service
public class CalculatorServiceImpl implements CalculatorService {


    @Override
    public int plus(int a, int b) {
        return a + b;
    }

    @Override
    public int minus(int a, int b) {
        return a - b;
    }

    @Override
    public int multiply(int a, int b) {
        return a * b;
    }

    @Override
    public int divide(int a, int b) throws ZeroDivideException {
        if (b == 0) {
            throw new ZeroDivideException("Делить на ноль низя!");
        }
        return a / b;

    }
}
