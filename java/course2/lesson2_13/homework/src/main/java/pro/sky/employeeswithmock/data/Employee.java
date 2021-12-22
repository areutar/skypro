package pro.sky.employeeswithmock.data;

import java.util.Objects;

public class Employee {
    private final String firstName;
    private final String secondName;
    private final String lastName;
    private final int department;
    private final int salary;
    public static final int MAX_SALARY = Integer.MAX_VALUE / 2;

    public Employee(String firstName, String secondName, String lastName, int department, int salary) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.lastName = lastName;
        this.department = department;
        this.salary = salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return department == employee.department && salary == employee.salary &&
                firstName.equalsIgnoreCase(employee.firstName) &&
                secondName.equalsIgnoreCase(employee.secondName) &&
                lastName.equalsIgnoreCase(employee.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, secondName, lastName, department, salary);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getDepartment() {
        return department;
    }

    public int getSalary() {
        return salary;
    }
}