package pro.sky.java.course2.examineservice.repository.impl;

import org.springframework.stereotype.Repository;
import pro.sky.java.course2.examineservice.domain.Question;
import pro.sky.java.course2.examineservice.exception.DuplicateQuestionException;
import pro.sky.java.course2.examineservice.exception.QuestionNotFoundException;
import pro.sky.java.course2.examineservice.repository.QuestionRepository;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Repository
public class JavaQuestionRepository implements QuestionRepository {
    private final Set<Question> questions;

    public JavaQuestionRepository() {
        questions = new HashSet<>();
    }

//    @PostConstruct
//    private void initQuestions() {
//        for (int i = 0; i < 6; i++) {
//            add(new Question("javaQuestion" + i, "javaAnswer" + i));
//        }
//    }

    @Override
    public Question add(Question question) {
        if (questions.add(question)) {
            return question;
        }
        throw new DuplicateQuestionException();
    }

    @Override
    public Question remove(Question question) {
        if (questions.remove(question)) {
            return question;
        }
        throw new QuestionNotFoundException();
    }

    @Override
    public Collection<Question> getAll() {
        return Collections.unmodifiableSet(questions);
    }
}
