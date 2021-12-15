package pro.sky.calculator.service.impl;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static pro.sky.calculator.CalculatorServiceConstants.*;

class CalculatorServiceTest {
    private final CalculatorServiceImpl calculatorService = new CalculatorServiceImpl();

    @Test
    void plusTwoPositiveNumbers() {
        int expected = POSITIVE_NUMBER + POSITIVE_NUMBER;
        int actual = calculatorService.plus(POSITIVE_NUMBER, POSITIVE_NUMBER);
        assertEquals(expected, actual);
    }

    @Test
    void plusTwoNegativeNumbers() {
        int expected = NEGATIVE_NUMBER + NEGATIVE_NUMBER;
        int actual = calculatorService.plus(NEGATIVE_NUMBER, NEGATIVE_NUMBER);
        assertEquals(expected, actual);
    }

    @Test
    void minusTwoPositiveNumbers() {
        int expected = POSITIVE_NUMBER - POSITIVE_NUMBER;
        int actual = calculatorService.minus(POSITIVE_NUMBER, POSITIVE_NUMBER);
        assertEquals(expected, actual);
    }

    @Test
    void minusPositiveAndNegativeNumbers() {
        int expected = POSITIVE_NUMBER - NEGATIVE_NUMBER;
        int actual = calculatorService.minus(POSITIVE_NUMBER, NEGATIVE_NUMBER);
        assertEquals(expected, actual);
    }

    @Test
    void multiplyTwoPositiveNumbers() {
        int expected = POSITIVE_NUMBER * POSITIVE_NUMBER;
        int actual = calculatorService.multiply(POSITIVE_NUMBER, POSITIVE_NUMBER);
        assertEquals(expected, actual);
    }

    @Test
    void multiplyNegativeAndZeroNumbers() {
        int expected = NEGATIVE_NUMBER * ZERO_NUMBER;
        int actual = calculatorService.multiply(NEGATIVE_NUMBER, ZERO_NUMBER);
        assertEquals(expected, actual);
    }

    @Test
    void divideTwoPositiveNumbers() {
        int expected = POSITIVE_NUMBER / POSITIVE_NUMBER;
        int actual = calculatorService.divide(POSITIVE_NUMBER, POSITIVE_NUMBER);
        assertEquals(expected, actual);
    }

    @Test
    void divideZeroAndNegativeNumbers() {
        int expected = ZERO_NUMBER / NEGATIVE_NUMBER;
        int actual = calculatorService.divide(ZERO_NUMBER, NEGATIVE_NUMBER);
        assertEquals(expected, actual);
    }

    @Test
    void dividePositiveAndZeroNumbers() {
        assertThrows(IllegalArgumentException.class,
                () -> calculatorService.divide(POSITIVE_NUMBER, ZERO_NUMBER));
    }
}