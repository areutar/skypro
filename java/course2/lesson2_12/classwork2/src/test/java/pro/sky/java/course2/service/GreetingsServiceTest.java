package pro.sky.java.course2.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static pro.sky.java.course2.constants.GreetingsServiceTestConstants.*;


class GreetingsServiceTest {

    private final GreetingsService out = new GreetingsService();

    @Test
    public void shouldReturnDefaultMessageWhenNameIsNotExists() {
        String result = out.generateGreetings();
        Assertions.assertTrue(result.contains(DEFAULT_NAME));
        Assertions.assertEquals(DEFAULT_MESSAGE, result);
    }

    @Test
    public void shouldReturnDefaultMessageWhenNameIsNull() {
        String result = out.generateGreetings(null);
        Assertions.assertTrue(result.contains(DEFAULT_NAME));
        Assertions.assertEquals(DEFAULT_MESSAGE, result);
    }

    @Test
    public void shouldReturnDefaultMessageWhenNameIsEmpty() {
        String result = out.generateGreetings(EMPTY_NAME);
        Assertions.assertTrue(result.contains(DEFAULT_NAME));
        Assertions.assertEquals(DEFAULT_MESSAGE, result);
    }

    @Test
    public void shouldReturnDefaultMessageWhenNameIsSpace() {
        String result = out.generateGreetings(ONLY_SPACES_NAME);
        Assertions.assertTrue(result.contains(DEFAULT_NAME));
        Assertions.assertEquals(DEFAULT_MESSAGE, result);
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenNameContainsIllegalCharacters() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> out.generateGreetings(ILLEGAL_CHARACTERS_NAME)
        );
    }

    @Test
    public void shouldReturnCorrectMessageWhenNameIsCorrect() {
        String result = out.generateGreetings(CORRECT_NAME);
        Assertions.assertTrue(result.contains(CORRECT_NAME));
        Assertions.assertEquals(CORRECT_MESSAGE, result);
    }

    @Test
    public void shouldReturnCorrectMessageWhenNameIsInUpperCase() {
        String result = out.generateGreetings(UPPER_CASE_NAME);
        Assertions.assertTrue(result.contains(CORRECT_NAME));
        Assertions.assertEquals(CORRECT_MESSAGE, result);
    }

    @Test
    public void shouldReturnCorrectMessageWhenNameIsInLowerCase() {
        String result = out.generateGreetings(LOWER_CASE_NAME);
        Assertions.assertTrue(result.contains(CORRECT_NAME));
        Assertions.assertEquals(CORRECT_MESSAGE, result);
    }
}