package pro.sky.calculator.service.impl;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pro.sky.calculator.service.CalculatorService;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static pro.sky.calculator.CalculatorServiceConstants.*;

public class CalculatorServiceParameterizedTest {
    private final CalculatorService calculatorService = new CalculatorServiceImpl();


    public static Stream<Arguments> provideParamsForPlusTests() {
        return Stream.of(
                Arguments.of(POSITIVE_NUMBER, POSITIVE_NUMBER, POSITIVE_NUMBER + POSITIVE_NUMBER),
                Arguments.of(POSITIVE_NUMBER, NEGATIVE_NUMBER, POSITIVE_NUMBER + NEGATIVE_NUMBER),
                Arguments.of(ZERO_NUMBER, NEGATIVE_NUMBER, ZERO_NUMBER + NEGATIVE_NUMBER)
        );
    }

    @ParameterizedTest
    @MethodSource("provideParamsForPlusTests")
    public void shouldGenerateCorrectSubtractions(int arg1, int arg2, int expectedSum) {
        int result = calculatorService.plus(arg1, arg2);
        assertEquals(expectedSum, result);
    }

    public static Stream<Arguments> provideParamsForMinusTests() {
        return Stream.of(
                Arguments.of(POSITIVE_NUMBER, POSITIVE_NUMBER, POSITIVE_NUMBER - POSITIVE_NUMBER),
                Arguments.of(POSITIVE_NUMBER, NEGATIVE_NUMBER, POSITIVE_NUMBER - NEGATIVE_NUMBER),
                Arguments.of(ZERO_NUMBER, NEGATIVE_NUMBER, ZERO_NUMBER - NEGATIVE_NUMBER)
        );
    }

    @ParameterizedTest
    @MethodSource("provideParamsForMinusTests")
    public void shouldGenerateCorrectSums(int arg1, int arg2, int expectedSubtraction) {
        int result = calculatorService.minus(arg1, arg2);
        assertEquals(expectedSubtraction, result);
    }

    public static Stream<Arguments> provideParamsForMultiplicationTests() {
        return Stream.of(
                Arguments.of(POSITIVE_NUMBER, POSITIVE_NUMBER, POSITIVE_NUMBER * POSITIVE_NUMBER),
                Arguments.of(POSITIVE_NUMBER, NEGATIVE_NUMBER, POSITIVE_NUMBER * NEGATIVE_NUMBER),
                Arguments.of(ZERO_NUMBER, NEGATIVE_NUMBER, ZERO_NUMBER * NEGATIVE_NUMBER)
        );
    }

    @ParameterizedTest
    @MethodSource("provideParamsForMultiplicationTests")
    public void shouldGenerateCorrectMultiplications(int arg1, int arg2, int expectedMultiplication) {
        int result = calculatorService.multiply(arg1, arg2);
        assertEquals(expectedMultiplication, result);
    }

    public static Stream<Arguments> provideParamsForDivisionTests() {
        return Stream.of(
                Arguments.of(POSITIVE_NUMBER, POSITIVE_NUMBER, POSITIVE_NUMBER / POSITIVE_NUMBER),
                Arguments.of(POSITIVE_NUMBER, NEGATIVE_NUMBER, POSITIVE_NUMBER / NEGATIVE_NUMBER),
                Arguments.of(ZERO_NUMBER, NEGATIVE_NUMBER, ZERO_NUMBER / NEGATIVE_NUMBER)
        );
    }

    @ParameterizedTest
    @MethodSource("provideParamsForDivisionTests")
    public void shouldGenerateCorrectDivisions(int arg1, int arg2, int expectedDivision) {
        int result = calculatorService.divide(arg1, arg2);
        assertEquals(expectedDivision, result);
    }


}
