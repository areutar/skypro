package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.exception.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.Collection;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public Student createStudent(Student student) {
        try {
            return repository.save(student);
        } catch (RuntimeException e) {
            throw new UnableToCreateException();
        }
    }

    public Student findStudent(long id) {
        Optional<Student> optionalStudent = repository.findById(id);
        if (optionalStudent.isEmpty()) {
            throw new NotFoundException();
        }
        return optionalStudent.get();
    }

    public Student editStudent(Student student) {
        try {
            return repository.save(student);
        } catch (RuntimeException e) {
            throw new UnableToUpdateException();
        }
    }

    public void deleteStudent(long id) {
        try {
            repository.deleteById(id);
        } catch (RuntimeException e) {
            throw new UnableToDeleteException();
        }
    }

    public Collection<Student> getAllStudents() {
        return repository.findAll();
    }

    public Collection<Student> findByAge(int age) {
        return repository.findByAge(age);
    }
    public Collection<Student> findByAgeBetween(int age1, int age2) {
        if (age1 < 1 || age2 < 1 || age2 < age1) {
            throw new BadRequestException();
        }
        Collection<Student> students = repository.findByAgeBetween(age1, age2);
        if (students.isEmpty()) {
            throw new NotFoundException();
        }
        return students;
    }
}
