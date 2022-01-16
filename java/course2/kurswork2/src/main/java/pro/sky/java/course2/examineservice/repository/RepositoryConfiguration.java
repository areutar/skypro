package pro.sky.java.course2.examineservice.repository;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pro.sky.java.course2.examineservice.repository.impl.QuestionRepositoryImpl;

@Configuration
public class RepositoryConfiguration {

    @Bean()
    public QuestionRepository javaQuestionRepository() {
        return new QuestionRepositoryImpl();
    }

    @Bean
    public QuestionRepository mathQuestionRepository() {
        return new QuestionRepositoryImpl();
    }
}
