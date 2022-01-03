package pro.sky.java.course2.examineservice.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pro.sky.java.course2.examineservice.domain.Question;
import pro.sky.java.course2.examineservice.repository.QuestionRepository;
import pro.sky.java.course2.examineservice.service.impl.QuestionServiceImpl;

@Configuration
public class ServiceConfigurator {
    @Bean
    public QuestionServiceImpl javaQuestionService(QuestionRepository javaQuestionRepository) {
        initJavaQuestions(javaQuestionRepository);
        return new QuestionServiceImpl(javaQuestionRepository);
    }

    private void initJavaQuestions(QuestionRepository javaQuestionRepository) {
        for (int i = 0; i < 6; i++) {
            javaQuestionRepository.add(new Question("javaQuestion" + i, "javaAnswer" + i));
        }
    }

    @Bean
    public QuestionServiceImpl mathQuestionService(QuestionRepository mathQuestionRepository) {
        initMathQuestions(mathQuestionRepository);
        return new QuestionServiceImpl(mathQuestionRepository);
    }

    private void initMathQuestions(QuestionRepository mathQuestionRepository) {
        for (int i = 0; i < 6; i++) {
            mathQuestionRepository.add(new Question("mathQuestion" + i, "mathAnswer" + i));
        }
    }
}
