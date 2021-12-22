package pro.sky.employeeswithmock.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.employeeswithmock.exception.EmployeeNotFoundException;
import pro.sky.employeeswithmock.service.EmployeeService;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static pro.sky.employeeswithmock.service.impl.constant.Constants.*;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceImplTest {
    @Mock
    EmployeeService employeeService;

    @InjectMocks
    private DepartmentServiceImpl out;

    @BeforeEach
    void initList() {
        Mockito.when(employeeService.getEmployees()).thenReturn(EMPLOYEE_LIST);
    }

    @Test
    void shouldReturnEmployeeWithMaxSalary() {
        assertEquals(out.getMaxSalaryEmployee(3), GOOD_EMPLOYEE3);
        assertEquals(out.getMaxSalaryEmployee(1), GOOD_EMPLOYEE2);
    }

    @Test
    void shouldReturnEmployeeWithMinSalary() {
        assertEquals(out.getMinSalaryEmployee(3), GOOD_EMPLOYEE1);
        assertEquals(out.getMinSalaryEmployee(1), GOOD_EMPLOYEE2);
    }

    @Test
    void shouldReturnEmployeeNotFoundExceptionWhenDepartmentIsIncorrect() {
        assertThrows(EmployeeNotFoundException.class, () -> out.getMaxSalaryEmployee(0));
        assertThrows(EmployeeNotFoundException.class, () -> out.getMaxSalaryEmployee(6));
        assertThrows(EmployeeNotFoundException.class, () -> out.getMinSalaryEmployee(2));
    }

    @Test
    void shouldReturnListOfAllEmployeesInDepartmentWhenDepartmentIsCorrect() {
        assertEquals(out.getAllEmployees(3), EMPLOYEE_LIST_DEPARTMENT3);
        assertEquals(out.getAllEmployees(1), EMPLOYEE_LIST_DEPARTMENT1);

    }

    @Test
    void shouldReturnEmptyListWhenDepartmentIsIncorrect() {
        assertEquals(out.getAllEmployees(0), Collections.emptyList());
        assertEquals(out.getAllEmployees(2), Collections.emptyList());
    }

    @Test
    void shouldReturnAllEmployeesOrderedByDepartmentWhenDepartmentIsNull() {
        assertEquals(out.getAllEmployees(null), EMPLOYEE_LIST_ORDERED);
    }

}