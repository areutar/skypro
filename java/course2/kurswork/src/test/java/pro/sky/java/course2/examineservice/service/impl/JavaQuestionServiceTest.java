package pro.sky.java.course2.examineservice.service.impl;

import org.junit.jupiter.api.Test;
import pro.sky.java.course2.examineservice.domain.Question;
import pro.sky.java.course2.examineservice.exception.DuplicateQuestionException;
import pro.sky.java.course2.examineservice.exception.QuestionNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static pro.sky.java.course2.examineservice.constant.Constants.*;

class JavaQuestionServiceTest {
    private final JavaQuestionService out;

    JavaQuestionServiceTest() {
        out = new JavaQuestionService();
    }

    @Test
    void shouldCorrectAddQuestion() {
        int size = out.getAll().size();
        assertEquals(QUEST1, out.add(QUEST1));
        assertEquals(size + 1, out.getAll().size());
    }

    @Test
    void shouldThrowExceptionWhenAddDuplicate() {
        out.add(QUEST1);
        assertThrows(DuplicateQuestionException.class, () -> out.add(QUEST1));
    }

    @Test
    void shouldCorrectRemoveQuestion() {
        int size = out.getAll().size();
        out.add(QUEST2);
        assertEquals(QUEST2, out.remove(QUEST2));
        assertEquals(size, out.getAll().size());
    }

    @Test
    void shouldThrowExceptionWhenQuestionNotFound() {
        out.add(QUEST1);
        Question notFoundQuestion = new Question(QUESTION1, ANSWER2);
        assertThrows(QuestionNotFoundException.class, () -> out.remove(notFoundQuestion));
    }

    @Test
    void shouldReturnTestingSetWhenGetAll() {
        out.add(QUEST1);
        out.add(QUEST2);
        out.add(QUEST3);
        out.add(QUEST4);
        out.add(QUEST5);
        assertEquals(ALL_QUESTIONS_SET, out.getAll());
    }

}