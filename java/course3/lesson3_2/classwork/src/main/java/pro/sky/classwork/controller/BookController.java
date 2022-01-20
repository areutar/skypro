package pro.sky.classwork.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.classwork.model.Book;
import pro.sky.classwork.service.BookService;

import java.util.Collection;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping({"/{id}"}) // GET https://localhost:8080/books/23
    public ResponseEntity<Book> getBookInfo(@PathVariable long id) {
        Book book = bookService.findBook(id);
        if (book == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(book);
    }

    @PostMapping // POST https://localhost:8080/books
    public Book createBook(@RequestBody Book book) {
        return bookService.createBook(book);
    }

    @PutMapping // PUT https://localhost:8080/books
    public ResponseEntity<Book> editBook(@RequestBody Book book) {
        Book foundBook = bookService.editBook(book);
        if (foundBook == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(foundBook);
    }

    @DeleteMapping("/{id}")  // DELETE https://localhost:8080/books/23
    public ResponseEntity<Book> deleteBook(@PathVariable long id) {
        Book book = bookService.deleteBook(id);
        if (book == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(book);
    }

    @GetMapping // GET https://localhost:8080/books
    public ResponseEntity<Collection<Book>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }
}
