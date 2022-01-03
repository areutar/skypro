package pro.sky.java.course2.examineservice.service.impl;

import org.springframework.stereotype.Service;
import pro.sky.java.course2.examineservice.domain.Question;
import pro.sky.java.course2.examineservice.repository.QuestionRepository;
import pro.sky.java.course2.examineservice.service.QuestionService;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
public class JavaQuestionService implements QuestionService {
    private final QuestionRepository javaQuestionRepository;
    private final Random random = new Random();

    public JavaQuestionService(QuestionRepository javaQuestionRepository) {
        this.javaQuestionRepository = javaQuestionRepository;
//        initQuestions();
    }

    @PostConstruct
    private void initQuestions() {
        for (int i = 0; i < 6; i++) {
            javaQuestionRepository.add(new Question("javaQuestion" + i, "javaAnswer" + i));
        }
    }

    @Override
    public Question add(Question question) {
       return javaQuestionRepository.add(question);
    }

    @Override
    public Question remove(Question question) {
       return javaQuestionRepository.remove(question);
    }

    @Override
    public Collection<Question> getAll() {
        return javaQuestionRepository.getAll();
    }

    @Override
    public Question getRandomQuestion() {
        List<Question> questionList = new ArrayList<>(getAll());
        return questionList.get(random.nextInt(questionList.size()));
    }

}
