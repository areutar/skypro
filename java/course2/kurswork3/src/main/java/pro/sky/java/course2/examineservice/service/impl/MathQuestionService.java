package pro.sky.java.course2.examineservice.service.impl;

import org.springframework.stereotype.Service;
import pro.sky.java.course2.examineservice.domain.Question;
import pro.sky.java.course2.examineservice.exception.MethodNotAllowedException;
import pro.sky.java.course2.examineservice.service.QuestionService;

import java.util.Collection;
import java.util.Random;

@Service
public class MathQuestionService implements QuestionService {
    private final Random random = new Random();

    @Override
    public Question add(Question question) {
        throw new MethodNotAllowedException();
    }

    @Override
    public Question remove(Question question) {
        throw new MethodNotAllowedException();
    }

    @Override
    public Collection<Question> getAll() {
        throw new MethodNotAllowedException();
    }

    @Override
    public Question getRandomQuestion() {
        int sum = 1 + random.nextInt(20);
        int term1 = random.nextInt(sum);
        int term2 = sum - term1;
        return new Question(term1 + " + " + term2 + " = ", String.valueOf(sum));
    }
}
