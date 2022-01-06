package pro.sky.employeeswithmock.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pro.sky.employeeswithmock.data.Employee;
import pro.sky.employeeswithmock.exception.*;
import pro.sky.employeeswithmock.service.EmployeeService;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static pro.sky.employeeswithmock.service.impl.constant.Constants.*;


class EmployeeServiceImplTest {
    private final EmployeeService out = new EmployeeServiceImpl();

    @ParameterizedTest
    @MethodSource("provideParamsForPositiveAddTests")
    void shouldAddCorrectEmployee(String firstName, String secondName, String lastName,
                                  int department, int salary, Employee employee) {
        Employee actualEmployee = out.addEmployee(firstName, secondName, lastName, department, salary);
        assertEquals(employee, actualEmployee);
    }

    public static Stream<Arguments> provideParamsForPositiveAddTests() {
        return Stream.of(
                Arguments.of(GOOD_FIRST_NAME1, GOOD_SECOND_NAME1, GOOD_LAST_NAME1,
                        GOOD_DEPARTMENT1, GOOD_SALARY1, GOOD_EMPLOYEE1),
                Arguments.of(GOOD_FIRST_NAME2, GOOD_SECOND_NAME2, GOOD_LAST_NAME2,
                        GOOD_DEPARTMENT2, GOOD_SALARY2, GOOD_EMPLOYEE2)
        );
    }

    @ParameterizedTest
    @MethodSource("provideParamsForIllegalNameException")
    void shouldThrowExceptionWhenNameIsInvalid(String firstName, String secondName, String lastName,
                                               int department, int salary) {
        Assertions.assertThrows(IllegalNameException.class,
                () -> out.addEmployee(firstName, secondName, lastName, department, salary));
    }

    public static Stream<Arguments> provideParamsForIllegalNameException() {
        return Stream.of(
                // bad firstName
                Arguments.of(BAD_FIRST_NAME1, GOOD_SECOND_NAME1, GOOD_LAST_NAME1,
                        GOOD_DEPARTMENT1, GOOD_SALARY1),
                Arguments.of(BAD_FIRST_NAME2, GOOD_SECOND_NAME1, GOOD_LAST_NAME1,
                        GOOD_DEPARTMENT1, GOOD_SALARY1),
                Arguments.of(BAD_FIRST_NAME3, GOOD_SECOND_NAME1, GOOD_LAST_NAME1,
                        GOOD_DEPARTMENT1, GOOD_SALARY1),
                Arguments.of(BAD_FIRST_NAME4, GOOD_SECOND_NAME1, GOOD_LAST_NAME1,
                        GOOD_DEPARTMENT1, GOOD_SALARY1),

                // bad secondName
                Arguments.of(GOOD_FIRST_NAME2, BAD_SECOND_NAME1, GOOD_LAST_NAME2,
                        GOOD_DEPARTMENT2, GOOD_SALARY2),
                Arguments.of(GOOD_FIRST_NAME2, BAD_SECOND_NAME2, GOOD_LAST_NAME2,
                        GOOD_DEPARTMENT2, GOOD_SALARY2),
                Arguments.of(GOOD_FIRST_NAME2, BAD_SECOND_NAME3, GOOD_LAST_NAME2,
                        GOOD_DEPARTMENT2, GOOD_SALARY2),
                Arguments.of(GOOD_FIRST_NAME2, BAD_SECOND_NAME4, GOOD_LAST_NAME2,
                        GOOD_DEPARTMENT2, GOOD_SALARY2),

                // bad lastName
                Arguments.of(GOOD_FIRST_NAME2, GOOD_SECOND_NAME1, BAD_LAST_NAME1,
                        GOOD_DEPARTMENT2, GOOD_SALARY2),
                Arguments.of(GOOD_FIRST_NAME2, GOOD_SECOND_NAME1, BAD_LAST_NAME2,
                        GOOD_DEPARTMENT2, GOOD_SALARY2),
                Arguments.of(GOOD_FIRST_NAME2, GOOD_SECOND_NAME1, BAD_LAST_NAME3,
                        GOOD_DEPARTMENT2, GOOD_SALARY2),
                Arguments.of(GOOD_FIRST_NAME2, GOOD_SECOND_NAME1, BAD_LAST_NAME4,
                        GOOD_DEPARTMENT2, GOOD_SALARY2)
        );
    }

    @ParameterizedTest
    @MethodSource("provideParamsForIllegalDepartmentException")
    void shouldThrowExceptionWhenDepartmentIsInvalid(String firstName, String secondName, String lastName,
                                                     int department, int salary) {
        Assertions.assertThrows(IllegalDepartmentException.class,
                () -> out.addEmployee(firstName, secondName, lastName, department, salary));
    }

    public static Stream<Arguments> provideParamsForIllegalDepartmentException() {
        return Stream.of(
                Arguments.of(GOOD_FIRST_NAME2, GOOD_SECOND_NAME1, GOOD_LAST_NAME1,
                        BAD_DEPARTMENT1, GOOD_SALARY1),
                Arguments.of(GOOD_FIRST_NAME1, GOOD_SECOND_NAME1, GOOD_LAST_NAME1,
                        BAD_DEPARTMENT2, GOOD_SALARY1),
                Arguments.of(GOOD_FIRST_NAME1, GOOD_SECOND_NAME1, GOOD_LAST_NAME1,
                        BAD_DEPARTMENT3, GOOD_SALARY1)
        );
    }

    @ParameterizedTest
    @MethodSource("provideParamsForIllegalSalaryException")
    void shouldThrowExceptionWhenSalaryIsInvalid(String firstName, String secondName, String lastName,
                                                 int department, int salary) {
        Assertions.assertThrows(IllegalSalaryException.class,
                () -> out.addEmployee(firstName, secondName, lastName, department, salary));
    }

    public static Stream<Arguments> provideParamsForIllegalSalaryException() {
        return Stream.of(
                Arguments.of(GOOD_FIRST_NAME2, GOOD_SECOND_NAME1, GOOD_LAST_NAME1,
                        GOOD_DEPARTMENT1, TOO_BIG_SALARY),
                Arguments.of(GOOD_FIRST_NAME1, GOOD_SECOND_NAME1, GOOD_LAST_NAME1,
                        GOOD_DEPARTMENT1, NEGATIVE_SALARY)
        );
    }

    @Test
    void shouldThrowExceptionWhenAddDuplicateEmployee() {
        out.addEmployee(GOOD_FIRST_NAME1, GOOD_SECOND_NAME1, GOOD_LAST_NAME1,
                GOOD_DEPARTMENT1, GOOD_SALARY1);
        Assertions.assertThrows(DuplicateEmployeeException.class,
                () -> out.addEmployee(GOOD_FIRST_NAME1, GOOD_SECOND_NAME1, GOOD_LAST_NAME1,
                        GOOD_DEPARTMENT2, GOOD_SALARY2));
    }

    @Test
    void correctReturnEmployee() {
        Employee employee = out.addEmployee(GOOD_FIRST_NAME1, GOOD_SECOND_NAME1, GOOD_LAST_NAME1,
                GOOD_DEPARTMENT1, GOOD_SALARY1);
        assertEquals(out.getEmployee(GOOD_FIRST_NAME1, GOOD_SECOND_NAME1, GOOD_LAST_NAME1), employee);

        Employee employee1 = out.addEmployee(GOOD_FIRST_NAME2, GOOD_SECOND_NAME2, GOOD_LAST_NAME2,
                GOOD_DEPARTMENT2, GOOD_SALARY2);
        assertEquals(out.getEmployee(GOOD_FIRST_NAME2, GOOD_SECOND_NAME2, GOOD_LAST_NAME2), employee1);
    }

    @Test
    void shouldThrowExceptionWhenGetEmployee() {
        out.addEmployee(GOOD_FIRST_NAME1, GOOD_SECOND_NAME1, GOOD_LAST_NAME1,
                GOOD_DEPARTMENT1, GOOD_SALARY1);
        Assertions.assertThrows(EmployeeNotFoundException.class,
                () -> out.getEmployee(GOOD_FIRST_NAME1, GOOD_SECOND_NAME2, GOOD_LAST_NAME1));
    }

    @Test
    void correctRemoveEmployee() {
        Employee employee = out.addEmployee(GOOD_FIRST_NAME1, GOOD_SECOND_NAME1, GOOD_LAST_NAME1,
                GOOD_DEPARTMENT1, GOOD_SALARY1);
        assertEquals(out.removeEmployee(GOOD_FIRST_NAME1, GOOD_SECOND_NAME1, GOOD_LAST_NAME1), employee);

        Employee employee1 = out.addEmployee(GOOD_FIRST_NAME2, GOOD_SECOND_NAME2, GOOD_LAST_NAME2,
                GOOD_DEPARTMENT2, GOOD_SALARY2);
        assertEquals(out.removeEmployee(GOOD_FIRST_NAME2, GOOD_SECOND_NAME2, GOOD_LAST_NAME2), employee1);
    }

    @Test
    void shouldThrowExceptionWhenRemoveEmployee() {
        out.addEmployee(GOOD_FIRST_NAME1, GOOD_SECOND_NAME1, GOOD_LAST_NAME1,
                GOOD_DEPARTMENT1, GOOD_SALARY1);
        Assertions.assertThrows(EmployeeNotFoundException.class,
                () -> out.removeEmployee(GOOD_FIRST_NAME1, GOOD_SECOND_NAME2, GOOD_LAST_NAME1));
    }

}