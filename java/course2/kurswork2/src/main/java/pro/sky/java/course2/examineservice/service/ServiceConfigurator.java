package pro.sky.java.course2.examineservice.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pro.sky.java.course2.examineservice.domain.Question;
import pro.sky.java.course2.examineservice.repository.QuestionRepository;
import pro.sky.java.course2.examineservice.service.impl.QuestionServiceImpl;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Configuration
public class ServiceConfigurator {
    @Bean
    @Qualifier("javaQuestionService")
    public QuestionServiceImpl javaQuestionService(QuestionRepository javaQuestionRepository) {
        initJavaQuestions(javaQuestionRepository);
        return new QuestionServiceImpl(javaQuestionRepository);
    }

    private void initJavaQuestions(QuestionRepository javaQuestionRepository) {
        Logger logger = Logger.getLogger("logger");
        logger.log(Level.SEVERE, "javaQuestionService init");
        System.out.println("javaQuestionService");
        for (int i = 0; i < 6; i++) {
            javaQuestionRepository.add(new Question("javaQuestion" + i, "javaAnswer" + i));
        }
    }

    @Bean
    @Qualifier("mathQuestionService")
    public QuestionServiceImpl mathQuestionService(QuestionRepository mathQuestionRepository) {
        initMathQuestions(mathQuestionRepository);
        return new QuestionServiceImpl(mathQuestionRepository);
    }

    private void initMathQuestions(QuestionRepository mathQuestionRepository) {
        Logger logger = Logger.getLogger("logger");
        logger.log(Level.SEVERE, "mathQuestionService init");
        System.out.println("mathQuestionService");
        for (int i = 0; i < 6; i++) {
            mathQuestionRepository.add(new Question("mathQuestion" + i, "mathAnswer" + i));
        }
    }
}
