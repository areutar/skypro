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
import static ru.hogwarts.school.utility.Utilities.getCurrentMethodName;

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
        log.info(getCurrentMethodName() + CREATED);

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
        log.info(getCurrentMethodName() + CREATED);

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
        log.info(getCurrentMethodName() + CREATED);

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
        log.info(getCurrentMethodName() + CREATED);

        try {
            studentRepository.deleteById(id);
        } catch (RuntimeException e) {
            throw new UnableToDeleteException("Student", "id", id, e);
        }
    }

    @Override
    public StudentDto findStudentById(long id) {
        log.info(getCurrentMethodName() + CREATED);

        try {
            Student student = studentRepository.getById(id);
            return mapper.toDto(student);
        } catch (Exception e) {
            throw new NotFoundException("Student", "id", id, e);
        }
    }

    @Override
    public List<StudentDto> getAllStudents() {
        log.info(getCurrentMethodName() + CREATED);

        return MapperConfiguration.convertList(studentRepository.findAll(), mapper::toDto);
    }

    @Override
    public List<StudentDto> findByAge(int age) {
        log.info(getCurrentMethodName() + CREATED);

        return MapperConfiguration.convertList(studentRepository.findByAge(age), mapper::toDto);
    }

    @Override
    public List<StudentDto> findByAgeBetween(int min, int max) {
        log.info(getCurrentMethodName() + CREATED);

        if (max < min) {
            throw new BadRequestException(FIRST_AGE_MORE_THAN_SECOND_ERROR);
        }
        return MapperConfiguration.convertList(studentRepository.findByAgeBetween(min, max), mapper::toDto);
    }

    @Override
    public Integer getStudentsCount() {
        log.info(getCurrentMethodName() + CREATED);

        return studentRepository.countAllById();
    }

    @Override
    public Float getStudentsAgesAverage() {
        log.info(getCurrentMethodName() + CREATED);

        return studentRepository.getAverageAgesStudents();
    }

    @Override
    public double getStudentsAgesAverage2() {
        log.info(getCurrentMethodName() + CREATED);

        return studentRepository.findAll().stream().mapToInt(Student::getAge).average().orElse(0);
    }

    @Override
    public List<StudentDto> getLastStudents(Integer number) {
        log.info(getCurrentMethodName() + CREATED);

        return MapperConfiguration.convertList(studentRepository.getLastStudents(number), mapper::toDto);
    }

    @Override
    public List<String> getStudentsBeginWithLetter(Character firstChar) {
        log.info(getCurrentMethodName() + CREATED);

        return studentRepository.findAll().stream().filter(student -> student.getName().startsWith(String.valueOf(firstChar))).map(student -> student.getName().toUpperCase()).sorted().collect(Collectors.toList());
    }

    /* если убрать засыпание потоков, то, практически, всегда список будет напечатан правильно, что
     "слегка противоречит требованиям задания"*/
    @Override
    public void printStudentsWithThreads1() {
        List<Student> students = studentRepository.findAll();
        printTwoStudentsNames(students.get(0), students.get(1));

        Thread thread1 = new Thread(() -> {
            try {
                Thread.sleep(1000);
                printTwoStudentsNames(students.get(2), students.get(3));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        thread1.start();

        Thread thread2 = new Thread(() -> {
            try {
                Thread.sleep(1000);
                printTwoStudentsNames(students.get(4), students.get(5));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        thread2.start();
    }

    @Override
    public void printStudentsWithThreads2() {
        List<Student> students = studentRepository.findAll();
        printStudent(students.get(0));
        printStudent(students.get(1));

        Thread thread1 = new Thread(() -> {
            printStudent(students.get(2));
            printStudent(students.get(3));
        });
        thread1.start();
        try {
            thread1.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        Thread thread2 = new Thread(() -> {
            printStudent(students.get(4));
            printStudent(students.get(5));
        });
        thread2.start();
    }

    private synchronized void printStudent(Student student) {
        System.out.println(student.getName());
    }

    private void printTwoStudentsNames(Student student1, Student student2) {
        System.out.println(student1.getName());
        System.out.println(student2.getName());
    }
}