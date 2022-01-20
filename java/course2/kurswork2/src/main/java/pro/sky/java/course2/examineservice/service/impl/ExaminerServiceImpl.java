package pro.sky.java.course2.examineservice.service.impl;

import org.springframework.stereotype.Service;
import pro.sky.java.course2.examineservice.domain.Question;
import pro.sky.java.course2.examineservice.exception.TooManyQuestionsRequestException;
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
        if (amount > questionServices.get(0).getAll().size() +
                questionServices.get(1).getAll().size()) {
            throw new TooManyQuestionsRequestException();
        }

        Set<Question> questions = new HashSet<>();
        while (questions.size() < amount) {
            questions.add(questionServices.get(random.nextInt(2)).getRandomQuestion());
        }
        return questions;
    }
}
