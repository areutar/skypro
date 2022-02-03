package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.exception.NotFoundException;
import ru.hogwarts.school.exception.UnableToCreateException;
import ru.hogwarts.school.exception.UnableToDeleteException;
import ru.hogwarts.school.exception.UnableToUpdateException;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class FacultyService {
    private final FacultyRepository repository;

    public FacultyService(FacultyRepository repository) {
        this.repository = repository;
    }

    public Faculty createFaculty(Faculty faculty) {
        try {
            return repository.save(faculty);
        } catch (RuntimeException e) {
            throw new UnableToCreateException();
        }
    }

    public Faculty findFaculty(long id) {
        Optional<Faculty> optionalFaculty = repository.findById(id);
        if (optionalFaculty.isEmpty()) {
            throw new NotFoundException();
        }
        return optionalFaculty.get();
    }

    public Faculty editFaculty(Faculty faculty) {
        try {
            return repository.save(faculty);
        } catch (RuntimeException e) {
            throw new UnableToUpdateException();
        }
    }

    public void deleteFaculty(long id) {
        try {
            repository.deleteById(id);
        } catch (RuntimeException e) {
            throw new UnableToDeleteException();
        }
    }

    public Collection<Faculty> getAllFaculties() {
        return repository.findAll();
    }

    public List<Faculty> findByColor(String color) {
        return repository.findByColor(color);
    }
}
