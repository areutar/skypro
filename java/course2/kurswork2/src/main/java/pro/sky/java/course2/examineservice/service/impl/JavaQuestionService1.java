package pro.sky.java.course2.examineservice.service.impl;

import org.springframework.stereotype.Service;
import pro.sky.java.course2.examineservice.domain.Question;
import pro.sky.java.course2.examineservice.repository.QuestionRepository;
import pro.sky.java.course2.examineservice.service.QuestionService;

import javax.annotation.PostConstruct;
import java.util.*;

//@Service
public class JavaQuestionService1 implements QuestionService {
    private final QuestionRepository questionRepository;
    private final List<Question> questionList = new ArrayList<>();
    private final Random random = new Random();

    public JavaQuestionService1(QuestionRepository javaQuestionRepository) {
        this.questionRepository = javaQuestionRepository;
    }

    @PostConstruct
    private void initQuestions() {
        for (int i = 0; i < 6; i++) {
            questionRepository.add(new Question("javaQuestion" + i, "javaAnswer" + i));
        }
    }

    @Override
    public Question add(Question question) {
       return questionRepository.add(question);
    }

    @Override
    public Question remove(Question question) {
       return questionRepository.remove(question);
    }

    @Override
    public Collection<Question> getAll() {
        return questionRepository.getAll();
    }

    @Override
    public Question getRandomQuestion() {
        questionList.clear();
        questionList.addAll(getAll());
        return questionList.get(random.nextInt(questionList.size()));
    }

}
