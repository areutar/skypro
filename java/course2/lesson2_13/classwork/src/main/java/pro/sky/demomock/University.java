package pro.sky.demomock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class University {
    private final StudentValueGenerator studentValueGenerator;
    private Map<Integer, Student> allStudents = new HashMap<>();
    private int countId = 1;

    public University(StudentValueGenerator studentValueGenerator) {
        this.studentValueGenerator = studentValueGenerator;
    }

    public void addStudent(Student student) {
        if (allStudents == null) {
            allStudents = new HashMap<>();
        }

        student.setId(countId);
        studentValueGenerator.generateAge();
        student.setAge(studentValueGenerator.generateAge());
        allStudents.put(countId, student);
        countId++;
    }

    public void addStudentInRange(Student student, int minYear, int maxYear) {
        if (allStudents == null) {
            allStudents = new HashMap<>();
        }

        studentValueGenerator.generateAge();
        studentValueGenerator.generateAge();

        student.setId(countId);
        student.setAge(studentValueGenerator.generateAgeInRange(minYear, maxYear));
        allStudents.put(countId, student);
        countId++;
    }

    public List<Student> getAllStudents() {
        return new ArrayList<>(allStudents.values());
    }

    public List<Student> getAllStudents(boolean isMale) {
        List<Student> listAllStudents = new ArrayList<>();
        for (Student student : allStudents.values()) {
            if (student.isMale() == isMale) {
                listAllStudents.add(student);
            }
        }
        return listAllStudents;
    }


}
