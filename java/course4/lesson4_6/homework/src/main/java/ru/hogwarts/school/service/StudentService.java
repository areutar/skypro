package ru.hogwarts.school.service;

import ru.hogwarts.school.dto.StudentDto;

import java.util.List;

public interface StudentService {
    StudentDto createStudent(StudentDto studentDto);

    StudentDto findStudentById(long id);

    StudentDto editStudent(StudentDto studentDto);

    void deleteStudentById(long id);

    List<StudentDto> getAllStudents();

    List<StudentDto> findByAge(int age);

    List<StudentDto> findByAgeBetween(int age1, int age2);

    Integer getStudentsCount();

    Float getStudentsAgesAverage();

    double getStudentsAgesAverage2();

    List<StudentDto> getLastStudents(Integer number);

    List<String> getStudentsBeginWithLetter(Character X);

    StudentDto addStudentToFaculty(StudentDto studentDto, Long faculty_id);

    void printStudentsWithThreads1();

    void printStudentsWithThreads2();
}
