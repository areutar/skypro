package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.service.FacultyService;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.Collection;

@RestController
@RequestMapping("faculty")
@Validated
public class FacultyController {
    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @PostMapping
    public Faculty createFaculty(@Valid @RequestBody Faculty faculty) {
        return facultyService.createFaculty(faculty);
    }

    @PutMapping
    public Faculty editFaculty(@Valid @RequestBody Faculty faculty) {
        return facultyService.editFaculty(faculty);
    }

    @GetMapping("/{id}")
    public Faculty getFacultyInfo(@PathVariable Long id) {
        return facultyService.findFaculty(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFaculty(@PathVariable Long id) {
        facultyService.deleteFaculty(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public Collection<Faculty> getAll() {
        return facultyService.getAllFaculties();
    }

    @GetMapping("/find")
    public Collection<Faculty> findFacultyByColorOrName(@Size(min = 2, max = 30)
                                                        @RequestParam(required = false) String color,
                                                        @Size(min = 2, max = 30)
                                                        @RequestParam(required = false) String name) {
        return facultyService.findByColorOrName(color, name);
    }
}
