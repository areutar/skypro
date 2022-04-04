package ru.hogwarts.school.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.dto.StudentDto;
import ru.hogwarts.school.service.StudentService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;

    @PostMapping
    public StudentDto createStudent(@Valid @RequestBody StudentDto studentDto) {
        return studentService.createStudent(studentDto);
    }

    @PutMapping
    public StudentDto editStudent(@Valid @RequestBody StudentDto studentDto) {
        return studentService.editStudent(studentDto);
    }

    @GetMapping("/{id}")
    public StudentDto getStudentInfo(@PathVariable Long id) {
        return studentService.findStudent(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping // http://localhost:8080/student
    public List<StudentDto> getAll() {
        return studentService.getAllStudents();
    }

    @GetMapping("/age/{age}")
    public List<StudentDto> findStudentsByAge(@PathVariable int age) {
        return studentService.findByAge(age);
    }

    @GetMapping("/age")
    public List<StudentDto> findStudentsByAge(@RequestParam int min, @RequestParam int max) {
        return studentService.findByAgeBetween(min, max);
    }
}