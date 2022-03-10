package pro.sky.classwork;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import pro.sky.classwork.controller.BookController;
import pro.sky.classwork.model.Book;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ClassworkApplicationTest {
    @LocalServerPort
    private int port;

    @Autowired
    private BookController bookController;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void contextLoads() {
        Assertions.assertThat(bookController).isNotNull();
    }

    @Test
    void testDefaultMessage() {
        Assertions
                .assertThat(restTemplate.getForObject("http://localhost:" + port + "/", String.class))
                .isEqualTo("WebApp is working!");
    }

    @Test
    void testGetBooks() {
        Assertions
                .assertThat(restTemplate.getForObject("http://localhost:" + port + "/books", String.class))
                .isNotNull();
    }

    @Test
    void testPostBooks() {
        Book book = new Book();
        book.setName("name");
        book.setAuthor("author");
        Assertions
                .assertThat(restTemplate.postForObject("http://localhost:" + port + "/books",
                        book, String.class)).isNotNull();
        restTemplate.delete("http://localhost:" + port + "/books/" + book.getId());

        Assertions
                .assertThat(restTemplate.getForObject("http://localhost:" + port + "/books", String.class))
                .doesNotContain(book.getAuthor(), book.getName());

    }

}