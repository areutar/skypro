package pro.sky.java.course2.examineservice.service.impl;

import org.junit.jupiter.api.Test;
import pro.sky.java.course2.examineservice.domain.Question;
import pro.sky.java.course2.examineservice.exception.DuplicateQuestionException;
import pro.sky.java.course2.examineservice.exception.QuestionNotFoundException;
import pro.sky.java.course2.examineservice.repository.impl.JavaQuestionRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static pro.sky.java.course2.examineservice.constant.Constants.*;

class JavaQuestionRepositoryTest {
    private final JavaQuestionRepository out;

    JavaQuestionRepositoryTest() {
        out = new JavaQuestionRepository();
    }

    @Test
    void shouldCorrectAddQuestion() {
        int size = out.getAll().size();
        assertEquals(J_QUEST1, out.add(J_QUEST1));
        assertEquals(size + 1, out.getAll().size());
    }

    @Test
    void shouldThrowExceptionWhenAddDuplicate() {
        out.add(J_QUEST1);
        assertThrows(DuplicateQuestionException.class, () -> out.add(J_QUEST1));
    }

    @Test
    void shouldCorrectRemoveQuestion() {
        int size = out.getAll().size();
        out.add(J_QUEST2);
        assertEquals(J_QUEST2, out.remove(J_QUEST2));
        assertEquals(size, out.getAll().size());
    }

    @Test
    void shouldThrowExceptionWhenQuestionNotFound() {
        out.add(J_QUEST1);
        assertThrows(QuestionNotFoundException.class, () ->
                out.remove(new Question(JAVA_QUESTION1, JAVA_ANSWER2)));
    }

    @Test
    void shouldReturnCollectionEqualsToTestingSet() {
        out.add(J_QUEST1);
        out.add(J_QUEST2);
        out.add(J_QUEST3);
        out.add(J_QUEST4);
        out.add(J_QUEST5);
        assertEquals(JAVA_QUESTIONS_SET, out.getAll());
    }

}