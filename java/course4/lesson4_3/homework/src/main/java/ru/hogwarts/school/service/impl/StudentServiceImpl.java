package ru.hogwarts.school.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.configuration.MapperConfiguration;
import ru.hogwarts.school.dto.StudentDto;
import ru.hogwarts.school.dto.StudentMapper;
import ru.hogwarts.school.exception.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.repository.StudentRepository;
import ru.hogwarts.school.service.StudentService;

import java.util.List;

import static ru.hogwarts.school.exception.ApiException.FIRST_AGE_MORE_THAN_SECOND_ERROR;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final FacultyRepository facultyRepository;
    private final StudentMapper mapper;

    @Override
    public StudentDto createStudent(StudentDto studentDto) {
        try {
            Faculty faculty = facultyRepository.findFacultyByName(studentDto.getFaculty());
            Student student = mapper.toEntity(studentDto);
            student.setFaculty(faculty);
            /* todo когда заполняется поле аватара в json, то либо: создаётся дефолтный нулевой аватар,
            на "true", "false", числа.. либо аватар не добавляется на null, или при "затирке поля"
            либо бросается исключение. Запретить добавление в поле! */
            return mapper.toDto(studentRepository.save(student));
        } catch (RuntimeException e) {
            throw new UnableToCreateException(e);
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
            throw new UnableToUpdateException(e);
        }
    }

    @Override
    public StudentDto addStudentToFaculty(StudentDto studentDto, Long faculty_id) {
        try {
            Faculty faculty = facultyRepository.getById(faculty_id);
            Student student = mapper.toEntity(studentDto);
            student.setFaculty(faculty);
            return mapper.toDto(studentRepository.save(student));
        } catch (RuntimeException e) {
            throw new NotFoundException("Faculty", "id", faculty_id, e);
        }
    }

    @Override
//    @Transactional
    public void deleteStudentById(long id) {
        try {
            studentRepository.deleteById(id);
        } catch (RuntimeException e) {
            throw new UnableToDeleteException("Student", "id", id, e);
        }
    }

    @Override
    public StudentDto findStudentById(long id) {
        try {
            Student student = studentRepository.getById(id);
            return mapper.toDto(student);
        } catch (Exception e) {
            throw new NotFoundException("Student", "id", id, e);
        }
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

    @Override
    public Integer getStudentsCount() {
        return studentRepository.countAllById();
    }

    @Override
    public Float getStudentsAgesAverage() {
        return studentRepository.averageAgesStudents();
    }

    @Override
    public List<StudentDto> getLastStudents(Integer number) {
        return MapperConfiguration.convertList(
                studentRepository.getLastStudents(number), mapper::toDto);
    }
}