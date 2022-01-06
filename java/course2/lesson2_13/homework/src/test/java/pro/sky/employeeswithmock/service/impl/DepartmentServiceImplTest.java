package pro.sky.employeeswithmock.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.employeeswithmock.exception.EmployeeNotFoundException;
import pro.sky.employeeswithmock.service.EmployeeService;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static pro.sky.employeeswithmock.service.impl.constant.Constants.*;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceImplTest {
    @Mock
    EmployeeService employeeService;

    @InjectMocks
    private DepartmentServiceImpl out;

    @BeforeEach
    void initList() {
        when(employeeService.getEmployees()).thenReturn(EMPLOYEE_LIST);
    }

    @Test
    void shouldReturnEmployeeWithMaxSalary() {
        assertEquals(GOOD_EMPLOYEE3, out.getMaxSalaryEmployee(3));
        assertEquals(GOOD_EMPLOYEE2, out.getMaxSalaryEmployee(1));
    }

    @Test
    void shouldReturnEmployeeWithMinSalary() {
        assertEquals(GOOD_EMPLOYEE1, out.getMinSalaryEmployee(3));
        assertEquals(GOOD_EMPLOYEE2, out.getMinSalaryEmployee(1));
    }

    @Test
    void shouldReturnEmployeeNotFoundExceptionWhenDepartmentIsIncorrect() {
        assertThrows(EmployeeNotFoundException.class, () -> out.getMaxSalaryEmployee(0));
        assertThrows(EmployeeNotFoundException.class, () -> out.getMaxSalaryEmployee(6));
        assertThrows(EmployeeNotFoundException.class, () -> out.getMinSalaryEmployee(2));
    }

    @Test
    void shouldReturnListOfAllEmployeesInDepartmentWhenDepartmentIsCorrect() {
        assertEquals(EMPLOYEE_LIST_DEPARTMENT3, out.getAllEmployees(3));
        assertEquals(EMPLOYEE_LIST_DEPARTMENT1, out.getAllEmployees(1));

    }

    @Test
    void shouldReturnEmptyListWhenDepartmentIsIncorrect() {
        assertEquals(Collections.emptyList(), out.getAllEmployees(0));
        assertEquals(Collections.emptyList(), out.getAllEmployees(2));
    }

    @Test
    void shouldReturnAllEmployeesOrderedByDepartmentWhenDepartmentIsNull() {
        assertEquals(EMPLOYEE_LIST_ORDERED, out.getAllEmployees(null));
    }

}