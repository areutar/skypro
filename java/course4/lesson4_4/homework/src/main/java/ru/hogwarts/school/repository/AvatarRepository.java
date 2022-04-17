package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ru.hogwarts.school.model.Avatar;

public interface AvatarRepository extends JpaRepository<Avatar, Long> {
    @Modifying
    @Query("delete from Avatar a where a.id = ?1")
    void deleteByIdWithJPQL(Long id);
}
