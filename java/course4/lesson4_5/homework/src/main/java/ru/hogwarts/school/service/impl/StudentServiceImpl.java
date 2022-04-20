package ru.hogwarts.school.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
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
import java.util.stream.Collectors;

import static ru.hogwarts.school.exception.ApiException.FIRST_AGE_MORE_THAN_SECOND_ERROR;

@Service
@RequiredArgsConstructor
@Log4j2
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final FacultyRepository facultyRepository;
    private final StudentMapper mapper;
    static final String CREATED = " started!";

    @Override
    public StudentDto createStudent(StudentDto studentDto) {
        log.info(new Object() {
        }.getClass().getEnclosingMethod().getName() + CREATED);

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
        log.info(new Object() {
        }.getClass().getEnclosingMethod().getName() + CREATED);

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
    public StudentDto addStudentToFaculty(StudentDto studentDto, Long facultyId) {
        log.info(new Object() {
        }.getClass().getEnclosingMethod().getName() + CREATED);

        try {
            Faculty faculty = facultyRepository.getById(facultyId);
            Student student = mapper.toEntity(studentDto);
            student.setFaculty(faculty);
            return mapper.toDto(studentRepository.save(student));
        } catch (RuntimeException e) {
            throw new NotFoundException("Faculty", "id", facultyId, e);
        }
    }

    @Override
//    @Transactional
    public void deleteStudentById(long id) {
        log.info(new Object() {
        }.getClass().getEnclosingMethod().getName() + CREATED);

        try {
            studentRepository.deleteById(id);
        } catch (RuntimeException e) {
            throw new UnableToDeleteException("Student", "id", id, e);
        }
    }

    @Override
    public StudentDto findStudentById(long id) {
        log.info(new Object() {
        }.getClass().getEnclosingMethod().getName() + CREATED);

        try {
            Student student = studentRepository.getById(id);
            return mapper.toDto(student);
        } catch (Exception e) {
            throw new NotFoundException("Student", "id", id, e);
        }
    }

    @Override
    public List<StudentDto> getAllStudents() {
        log.info(new Object() {
        }.getClass().getEnclosingMethod().getName() + CREATED);

        return MapperConfiguration.convertList(
                studentRepository.findAll(), mapper::toDto);
    }

    @Override
    public List<StudentDto> findByAge(int age) {
        log.info(new Object() {
        }.getClass().getEnclosingMethod().getName() + CREATED);

        return MapperConfiguration.convertList(
                studentRepository.findByAge(age), mapper::toDto);
    }

    @Override
    public List<StudentDto> findByAgeBetween(int min, int max) {
        log.info(new Object() {
        }.getClass().getEnclosingMethod().getName() + CREATED);

        if (max < min) {
            throw new BadRequestException(FIRST_AGE_MORE_THAN_SECOND_ERROR);
        }
        return MapperConfiguration.convertList(
                studentRepository.findByAgeBetween(min, max), mapper::toDto);
    }

    @Override
    public Integer getStudentsCount() {
        log.info(new Object() {
        }.getClass().getEnclosingMethod().getName() + CREATED);

        return studentRepository.countAllById();
    }

    @Override
    public Float getStudentsAgesAverage() {
        log.info(new Object() {
        }.getClass().getEnclosingMethod().getName() + CREATED);

        return studentRepository.averageAgesStudents();
    }

    @Override
    public double getStudentsAgesAverage2() {
        log.info(new Object() {
        }.getClass().getEnclosingMethod().getName() + CREATED);

        return studentRepository.findAll()
                .stream()
                .mapToInt(Student::getAge)
                .average()
                .orElse(0);
    }

    @Override
    public List<StudentDto> getLastStudents(Integer number) {
        log.info(new Object() {
        }.getClass().getEnclosingMethod().getName() + CREATED);

        return MapperConfiguration.convertList(
                studentRepository.getLastStudents(number), mapper::toDto);
    }

    @Override
    public List<String> getStudentsBeginWithLetter(Character X) {
        log.info(new Object() {
        }.getClass().getEnclosingMethod().getName() + CREATED);

        return studentRepository.findAll()
                .stream()
                .filter(student -> student.getName().startsWith(String.valueOf(X)))
                .map(student -> student.getName().toUpperCase())
                .sorted()
                .collect(Collectors.toList());
    }

}