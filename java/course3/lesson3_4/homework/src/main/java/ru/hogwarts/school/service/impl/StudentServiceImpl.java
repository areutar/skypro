package ru.hogwarts.school.service.impl;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.exception.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import static ru.hogwarts.school.exception.ApiException.UNABLE_TO_CREATE;
import static ru.hogwarts.school.exception.ApiException.UNABLE_TO_UPDATE;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository repository;
    private final Logger logger = Logger.getLogger(String.valueOf(StudentServiceImpl.class));

    public StudentServiceImpl(StudentRepository repository) {
        this.repository = repository;
    }

    @Override
    public Student createStudent(Student student) {
        try {
            return repository.save(student);
        } catch (RuntimeException e) {
            logger.log(Level.SEVERE, e.getMessage());
            throw new UnableToCreateException(UNABLE_TO_CREATE, e);
        }
    }

    @Override
    public Student editStudent(Student student) {
        try {
            return repository.save(student);
        } catch (RuntimeException e) {
            logger.log(Level.SEVERE, e.getMessage());
            throw new UnableToUpdateException(UNABLE_TO_UPDATE);
        }
    }

    @Override
    public void deleteStudent(long id) {
        try {
            repository.deleteById(id);
        } catch (RuntimeException e) {
            logger.log(Level.SEVERE, e.getMessage());
            throw new UnableToDeleteException(String.format("Unable to delete student with id %d!", id));
        }
    }

    @Override
    public Student findStudent(long id) {
        Optional<Student> optionalStudent = repository.findById(id);
        if (optionalStudent.isEmpty()) {
            throw new NotFoundException(String.format("Not found student with id %d!", id));
        }
        return optionalStudent.get();
    }

    @Override
    public Collection<Student> getAllStudents() {
        return repository.findAll();
    }

    @Override
    public Collection<Student> findByAge(int age) {
        return repository.findByAge(age);
    }

    @Override
    public Collection<Student> findByAgeBetween(int age1, int age2) {
        if (age2 < age1) {
            throw new BadRequestException("The second age value must be no less than the first!");
        }
        return repository.findByAgeBetween(age1, age2);
    }
}
