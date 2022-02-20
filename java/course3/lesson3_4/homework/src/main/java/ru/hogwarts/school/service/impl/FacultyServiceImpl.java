package ru.hogwarts.school.service.impl;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.exception.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.service.FacultyService;

import java.util.Collection;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import static ru.hogwarts.school.exception.ApiException.*;

@Service
public class FacultyServiceImpl implements FacultyService {

    private final FacultyRepository repository;
    private final Logger logger = Logger.getLogger(String.valueOf(FacultyServiceImpl.class));

    public FacultyServiceImpl(FacultyRepository repository) {
        this.repository = repository;
    }

    @Override
    public Faculty createFaculty(Faculty faculty) {
        try {
            return repository.save(faculty);
        } catch (RuntimeException e) {
            logger.log(Level.SEVERE, e.getMessage());
            throw new UnableToCreateException(UNABLE_TO_CREATE, e);
        }
    }

    @Override
    public Faculty editFaculty(Faculty faculty) {
        try {
            return repository.save(faculty);
        } catch (RuntimeException e) {
            logger.log(Level.SEVERE, e.getMessage());
            throw new UnableToUpdateException(UNABLE_TO_UPDATE, e);
        }
    }

    @Override
    public void deleteFaculty(long id) {
        try {
            repository.deleteById(id);
        } catch (RuntimeException e) {
            logger.log(Level.SEVERE, e.getMessage());
            throw new UnableToDeleteException(UNABLE_TO_DELETE, e);
        }
    }

    @Override
    public Faculty findFaculty(long id) {
        Optional<Faculty> optionalFaculty = repository.findById(id);
        if (optionalFaculty.isEmpty()) {
            throw new NotFoundException(NOT_FOUND);
        }
        return optionalFaculty.get();
    }

    @Override
    public Collection<Faculty> getAllFaculties() {
        return repository.findAll();
    }

    @Override
    public Collection<Faculty> findByColorOrName(String color, String name) {
        if (color == null && name == null) {
            throw new BadRequestException("At least one field must not be null!");
        }

        Collection<Faculty> faculties;
        if (name != null) {
            faculties = repository.findFacultiesByNameIgnoreCase(name);
        } else {
            faculties = repository.findFacultiesByColorIgnoreCase(color);
        }
        return faculties;
    }

}
