package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.hogwarts.school.model.Student;

import java.util.Collection;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Collection<Student> findByAge(int age);

    Collection<Student> findByAgeBetween(int age1, int age2);

    @Query(value = "select count(*) from student", nativeQuery = true)
    Integer countAllById();

    @Query(value = "select avg(student.age) from student", nativeQuery = true)
    Float averageAgesStudents();

    @Query(value = "select * from student limit 5" +
            " offset (select count(*) from student) - 5", nativeQuery = true)
    Collection<Student> getLastFifeStudents();
}
