package pro.sky.classwork.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.classwork.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

}
