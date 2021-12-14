package pro.sky.junitdemo;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class UniversityTest {
    Student student1;
    Student student2;
    Student student3;
    University university;

    @Before
    public void setUp() {
        student1 = new Student("Евгений", 35, true);
        student2 = new Student("Марина", 34, false);
        student3 = new Student("Алина", 7, false);

        university = new University();

        university.addStudent(student1);
        university.addStudent(student2);
        university.addStudent(student3);
    }

    @Test
    public void getAllStudents() {
        List<Student> expected = university.getAllStudents();
        List<Student> actual = new ArrayList<>();

        actual.add(student1);
        actual.add(student2);
        actual.add(student3);

        assertEquals(actual, expected);

    }

    @Test
    public void getAllStudentsIsMale() {
        List<Student> expected = university.getAllStudents(true);
        List<Student> actual = new ArrayList<>();

        actual.add(student1);
//        actual.add(student2);
//        actual.add(student3);

        assertEquals(actual, expected);

    }

    @Test
    public void getAllStudentsNotNull() {
        university = new University();
        List<Student> expected = university.getAllStudents();
        assertNotNull(expected);
    }

}
