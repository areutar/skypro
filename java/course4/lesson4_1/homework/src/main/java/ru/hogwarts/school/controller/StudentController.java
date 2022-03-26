package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public Student createStudent(@Valid @RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @PutMapping
    public Student editStudent(@Valid @RequestBody Student student) {
        return studentService.editStudent(student);
    }

    @GetMapping("/{id}")
    public Student getStudentInfo(@PathVariable Long id) {
        return studentService.findStudent(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping // http://localhost:8080/student
    public Collection<Student> getAll() {
        return studentService.getAllStudents();
    }

    @GetMapping("/age/{age}")
    public Collection<Student> findStudentsByAge(@PathVariable int age) {
        return studentService.findByAge(age);
    }

    @GetMapping("/age")
    public Collection<Student> findStudentsByAge(@RequestParam int min, @RequestParam int max) {
        return studentService.findByAgeBetween(min, max);
    }

    @GetMapping("/students_count")
    public Integer getStudentsCount() {
        return studentService.getStudentsCount();
    }

    @GetMapping("/students_average_ages")
    public Float getAverageAgesStudents() {
        return studentService.getStudentsAgesAverage();
    }

    @GetMapping("/students_last_fife")
    public Collection<Student> getLastFifeStudents() {
        return studentService.getLastFifeStudents();
    }
}