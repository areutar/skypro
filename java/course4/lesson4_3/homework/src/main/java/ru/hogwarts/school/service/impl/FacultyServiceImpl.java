package ru.hogwarts.school.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.exception.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.service.FacultyService;

import java.util.Collection;

import static ru.hogwarts.school.exception.ApiException.ALL_FIELDS_ARE_NULL;

@Service
@RequiredArgsConstructor
public class FacultyServiceImpl implements FacultyService {
    private final FacultyRepository facultyRepository;

    @Override
    public Faculty createFaculty(Faculty faculty) {
        try {
            return facultyRepository.save(faculty);
        } catch (RuntimeException e) {
            throw new UnableToCreateException(e);
        }
    }

    @Override
    public Faculty editFaculty(Faculty faculty) {
        try {
            return facultyRepository.save(faculty);
        } catch (RuntimeException e) {
            throw new UnableToUpdateException(e);
        }
    }

    @Override
    public void deleteFaculty(long id) {
        try {
            facultyRepository.deleteById(id);
        } catch (RuntimeException e) {
            throw new UnableToDeleteException("Faculty", "id", id, e);
        }
    }

    @Override
    public Faculty findFacultyById(long id) {
        try {
            return facultyRepository.getById(id);
        } catch (Exception e) {
            throw new NotFoundException("Faculty", "id", id, e);
        }
    }

    @Override
    public Collection<Faculty> getAllFaculties() {
        return facultyRepository.findAll();
    }

    @Override
    public Collection<Faculty> findByColorOrName(String color, String name) {
        if (color == null && name == null) {
            throw new BadRequestException(ALL_FIELDS_ARE_NULL);
        }
        if (name != null) {
            return facultyRepository.findFacultiesByNameIgnoreCase(name);
        }
        return facultyRepository.findFacultiesByColorIgnoreCase(color);
    }

}