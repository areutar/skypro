package ru.hogwarts.school.service.impl;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.exception.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.service.FacultyService;

import java.util.Collection;

import static ru.hogwarts.school.exception.ApiException.*;

@Service
public class FacultyServiceImpl implements FacultyService {
    private final FacultyRepository repository;

    public FacultyServiceImpl(FacultyRepository repository) {
        this.repository = repository;
    }

    @Override
    public Faculty createFaculty(Faculty faculty) {
        try {
            return repository.save(faculty);
        } catch (RuntimeException e) {
            throw new UnableToCreateException(UNABLE_TO_CREATE, e);
        }
    }

    @Override
    public Faculty editFaculty(Faculty faculty) {
        try {
            return repository.save(faculty);
        } catch (RuntimeException e) {
            throw new UnableToUpdateException(UNABLE_TO_UPDATE, e);
        }
    }

    @Override
    public void deleteFaculty(long id) {
        try {
            repository.deleteById(id);
        } catch (RuntimeException e) {
            throw new UnableToDeleteException("Faculty", "id", id);
        }
    }

    @Override
    public Faculty findFaculty(long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Faculty", "id", id));
    }

    @Override
    public Collection<Faculty> getAllFaculties() {
        return repository.findAll();
    }

    @Override
    public Collection<Faculty> findByColorOrName(String color, String name) {
        if (color == null && name == null) {
            throw new BadRequestException(ALL_FIELDS_ARE_NULL);
        }
        if (name != null) {
            return repository.findFacultiesByNameIgnoreCase(name);
        }
        return repository.findFacultiesByColorIgnoreCase(color);
    }

}