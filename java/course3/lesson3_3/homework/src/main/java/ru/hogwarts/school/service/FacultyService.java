package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.Collection;
import java.util.List;

@Service
public class FacultyService {
    private final FacultyRepository repository;

    public FacultyService(FacultyRepository repository) {
        this.repository = repository;
    }

    public Faculty createFaculty(Faculty faculty) {
        return repository.save(faculty);
    }

    public Faculty findFaculty(long id) {
        return repository.findById(id);
    }

    public Faculty editFaculty(Faculty faculty) {
        return repository.save(faculty);
    }

    public Faculty deleteFaculty(long id) {
        return repository.deleteById(id);
    }

    public Collection<Faculty> getAllFaculties() {
        return repository.findAll();
    }

    public List<Faculty> findByColor(String color) {
        return repository.findByColor(color);
    }
}
