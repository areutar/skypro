package ru.hogwarts.school.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.exception.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.service.FacultyService;

import java.util.Collection;
import java.util.Comparator;

import static ru.hogwarts.school.exception.ApiException.ALL_FIELDS_ARE_NULL;
import static ru.hogwarts.school.service.impl.StudentServiceImpl.CREATED;

@Service
@RequiredArgsConstructor
@Log4j2
public class FacultyServiceImpl implements FacultyService {
    private final FacultyRepository facultyRepository;

    @Override
    public Faculty createFaculty(Faculty faculty) {
        log.info(new Object() {
        }.getClass().getEnclosingMethod().getName() + CREATED);

        try {
            return facultyRepository.save(faculty);
        } catch (RuntimeException e) {
            throw new UnableToCreateException(e);
        }
    }

    @Override
    public Faculty editFaculty(Faculty faculty) {
        log.info(new Object() {
        }.getClass().getEnclosingMethod().getName() + CREATED);

        try {
            return facultyRepository.save(faculty);
        } catch (RuntimeException e) {
            throw new UnableToUpdateException(e);
        }
    }

    @Override
    public void deleteFaculty(long id) {
        log.info(new Object() {
        }.getClass().getEnclosingMethod().getName() + CREATED);

        try {
            facultyRepository.deleteById(id);
        } catch (RuntimeException e) {
            throw new UnableToDeleteException("Faculty", "id", id, e);
        }
    }

    @Override
    public Faculty findFacultyById(long id) {
        log.info(new Object() {
        }.getClass().getEnclosingMethod().getName() + CREATED);

        try {
            return facultyRepository.getById(id);
        } catch (Exception e) {
            throw new NotFoundException("Faculty", "id", id, e);
        }
    }

    @Override
    public Collection<Faculty> getAllFaculties() {
        log.info(new Object() {
        }.getClass().getEnclosingMethod().getName() + CREATED);

        return facultyRepository.findAll();
    }

    @Override
    public Collection<Faculty> findByColorOrName(String color, String name) {
        log.info(new Object() {
        }.getClass().getEnclosingMethod().getName() + CREATED);

        if (color == null && name == null) {
            throw new BadRequestException(ALL_FIELDS_ARE_NULL);
        }
        if (name != null) {
            return facultyRepository.findFacultiesByNameIgnoreCase(name);
        }
        return facultyRepository.findFacultiesByColorIgnoreCase(color);
    }

    @Override
    public String maxLongFacultyName() {
        log.info(new Object() {
        }.getClass().getEnclosingMethod().getName() + CREATED);

        return facultyRepository.findAll()
                .stream()
                .map(Faculty::getName)
                .max(Comparator.comparing(String::length))
                .orElse("");
    }

}