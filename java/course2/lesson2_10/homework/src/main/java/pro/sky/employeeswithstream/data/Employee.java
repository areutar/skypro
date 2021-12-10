package pro.sky.employeeswithstream.data;

import java.util.Objects;

public class Employee {
    private FullName fullName;
    private int department;
    private int salary;

    public Employee(FullName fullName) {
        this.fullName = fullName;
    }

    public Employee(FullName fullName, int department, int salary) {
        this.fullName = fullName;
        this.department = department;
        this.salary = salary;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getDepartment() {
        return department;
    }

    public void setDepartment(int department) {
        this.department = department;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return fullName.equals(employee.fullName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fullName);
    }

    public FullName getFullName() {
        return fullName;
    }

    public void setFullName(FullName fullName) {
        this.fullName = fullName;
    }
}
