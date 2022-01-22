package pro.sky.java.course2.examineservice.service.impl;

import pro.sky.java.course2.examineservice.domain.Question;
import pro.sky.java.course2.examineservice.repository.QuestionRepository;
import pro.sky.java.course2.examineservice.service.QuestionService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

public class QuestionServiceImpl implements QuestionService {
    private final QuestionRepository questionRepository;
    private final Random random = new Random();

    public QuestionServiceImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
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
        List<Question> questionList = new ArrayList<>(getAll());
        return questionList.get(random.nextInt(questionList.size()));
    }

}
