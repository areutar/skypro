package ru.hogwarts.school.dto;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.hogwarts.school.model.Student;

import javax.annotation.PostConstruct;
import java.util.Objects;

@Component
public class FacultyMapper {
    private final ModelMapper mapper;

    public FacultyMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    @PostConstruct
    private void setupMapper() {
        mapper.createTypeMap(Student.class, StudentDto.class)
                .addMapping(student -> student.getFaculty().getName(), StudentDto::setFaculty)
                .addMapping(student -> student.getAvatar().getId(), StudentDto::setAvatar);
    }

    public Student toEntity(StudentDto dto) {
        return Objects.isNull(dto) ? null : mapper.map(dto, Student.class);
    }

    public StudentDto toDto(Student entity) {
        return Objects.isNull(entity) ? null : mapper.map(entity, StudentDto.class);
    }
}