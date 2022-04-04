package ru.hogwarts.school.service;

import ru.hogwarts.school.dto.StudentDto;

import java.util.List;

public interface StudentService {
    StudentDto createStudent(StudentDto studentDto);

    StudentDto findStudent(long id);

    StudentDto editStudent(StudentDto studentDto);

    void deleteStudent(long id);

    List<StudentDto> getAllStudents();

    List<StudentDto> findByAge(int age);

    List<StudentDto> findByAgeBetween(int age1, int age2);
}
