package pro.sky.java.course2.examineservice.service.impl;

import org.springframework.stereotype.Service;
import pro.sky.java.course2.examineservice.domain.Question;
import pro.sky.java.course2.examineservice.service.ExaminerService;
import pro.sky.java.course2.examineservice.service.QuestionService;

import java.util.*;

@Service
public class ExaminerServiceImpl implements ExaminerService {
    private final Random random = new Random();
    private final List<QuestionService> questionServices;

    public ExaminerServiceImpl(List<QuestionService> questionServices) {
        this.questionServices = questionServices;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        Set<Question> questions = new HashSet<>();
        while (questions.size() < amount) {
            QuestionService randomService = questionServices.get(random.nextInt(questionServices.size()));
            questions.add(randomService.getRandomQuestion());
        }
        return questions;
    }
}
