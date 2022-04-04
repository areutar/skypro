package ru.hogwarts.school.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.configuration.MapperConfiguration;
import ru.hogwarts.school.dto.FacultyMapper;
import ru.hogwarts.school.dto.StudentDto;
import ru.hogwarts.school.exception.*;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.AvatarRepository;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.repository.StudentRepository;
import ru.hogwarts.school.service.StudentService;

import java.util.List;
import java.util.Optional;

import static ru.hogwarts.school.exception.ApiException.*;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final FacultyRepository facultyRepository;
    private final AvatarRepository avatarRepository;
    private final FacultyMapper mapper;

    @Override
    public StudentDto createStudent(StudentDto studentDto) {
        try {
            Faculty faculty = facultyRepository.findFacultyByName(studentDto.getFaculty());
            Student student = mapper.toEntity(studentDto);
            student.setFaculty(faculty);
            return mapper.toDto(studentRepository.save(student));
        } catch (RuntimeException e) {
            throw new UnableToCreateException(UNABLE_TO_CREATE, e);
        }
    }

    @Override
    public StudentDto editStudent(StudentDto studentDto) {
        try {
            Faculty faculty = facultyRepository.findFacultyByName(studentDto.getFaculty());
            Student student = mapper.toEntity(studentDto);
            student.setFaculty(faculty);
            return mapper.toDto(studentRepository.save(student));
        } catch (RuntimeException e) {
            throw new UnableToUpdateException(UNABLE_TO_UPDATE, e);
        }
    }

    @Override
    public void deleteStudent(long id) {
        try {
            Optional<Avatar> avatar = avatarRepository.findById(id);
            if (avatar.isPresent()) {
                avatarRepository.deleteById(id);
            }
            studentRepository.deleteById(id);
        } catch (RuntimeException e) {
            throw new UnableToDeleteException("Student", "id", id);
        }
    }

    @Override
    public StudentDto findStudent(long id) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isEmpty()) {
            throw new NotFoundException("Student", "id", id);
        }
        return mapper.toDto(optionalStudent.get());
    }

    @Override
    public List<StudentDto> getAllStudents() {
        return MapperConfiguration.convertList(
                studentRepository.findAll(), mapper::toDto);

    }

    @Override
    public List<StudentDto> findByAge(int age) {
        return MapperConfiguration.convertList(
                studentRepository.findByAge(age), mapper::toDto);
    }

    @Override
    public List<StudentDto> findByAgeBetween(int min, int max) {
        if (max < min) {
            throw new BadRequestException(FIRST_AGE_MORE_THAN_SECOND_ERROR);
        }
        return MapperConfiguration.convertList(
                studentRepository.findByAgeBetween(min, max), mapper::toDto);
    }
}