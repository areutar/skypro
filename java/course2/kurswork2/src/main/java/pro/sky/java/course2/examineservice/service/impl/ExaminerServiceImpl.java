package pro.sky.java.course2.examineservice.service.impl;

import org.springframework.stereotype.Service;
import pro.sky.java.course2.examineservice.domain.Question;
import pro.sky.java.course2.examineservice.exception.TooManyQuestionsRequestException;
import pro.sky.java.course2.examineservice.service.ExaminerService;
import pro.sky.java.course2.examineservice.service.QuestionService;

import java.util.Collection;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Service
public class ExaminerServiceImpl implements ExaminerService {
    private final Random random = new Random();
    private final QuestionService javaQuestionService;
    private final QuestionService mathQuestionService;

    public ExaminerServiceImpl(QuestionService javaQuestionService, QuestionService mathQuestionService) {
        this.javaQuestionService = javaQuestionService;
        this.mathQuestionService = mathQuestionService;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        if (amount > javaQuestionService.getAll().size() + mathQuestionService.getAll().size()) {
            throw new TooManyQuestionsRequestException();
        }
        Set<Question> questions = new HashSet<>();
        while (questions.size() < amount) {
            questions.add(random.nextBoolean() ?
                    mathQuestionService.getRandomQuestion() :
                    javaQuestionService.getRandomQuestion());
        }
        return questions;
    }
}
