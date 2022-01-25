package ru.hogwarts.school.service;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.Collection;

@Service
public class StudentService {
    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public Student createStudent(Student student) {
        return repository.save(student);
    }

    public Student findStudent(long id) {
        return repository.findById(id);
    }

    public Student editStudent(Student student) {
        return repository.save(student);
    }

    public Student deleteStudent(long id) {
        return repository.deleteById(id);
    }

    public Collection<Student> getAllStudents() {
        return repository.findAll();
    }

    public Collection<Student> findByAge(int age) {
        return repository.findByAge(age);
    }
}
