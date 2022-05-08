package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.hogwarts.school.model.Student;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByAge(int age);

    List<Student> findByAgeBetween(int age1, int age2);

    @Query(value = "select count(*) from students", nativeQuery = true)
    Integer countAllById();

    @Query(value = "select avg(students.age) from students", nativeQuery = true)
    Float getAverageAgesStudents();

    @Query(value = "select * from students limit :number" +
            " offset (select count(*) from students) - :number", nativeQuery = true)
    List<Student> getLastStudents(@Param("number") Integer number);
}
