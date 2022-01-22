package pro.sky.java.course2.examineservice.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.java.course2.examineservice.domain.Question;
import pro.sky.java.course2.examineservice.exception.DuplicateQuestionException;
import pro.sky.java.course2.examineservice.exception.QuestionNotFoundException;
import pro.sky.java.course2.examineservice.repository.impl.JavaQuestionRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static pro.sky.java.course2.examineservice.constant.Constants.*;

@ExtendWith(MockitoExtension.class)
class JavaQuestionServiceTest {

    @Mock
    private JavaQuestionRepository javaQuestionRepository;

    @InjectMocks
    private JavaQuestionService out;

    @Test
    void shouldCorrectAddQuestion() {
        when(javaQuestionRepository.add(J_QUEST1)).thenReturn(J_QUEST1);
        assertEquals(J_QUEST1, out.add(J_QUEST1));
    }

    @Test
    void shouldThrowExceptionWhenAddDuplicate() {
        out.add(J_QUEST1);
        when(javaQuestionRepository.add(J_QUEST1)).thenThrow(DuplicateQuestionException.class);
        assertThrows(DuplicateQuestionException.class, () -> out.add(J_QUEST1));
    }

    @Test
    void shouldCorrectRemoveQuestion() {
        when(javaQuestionRepository.remove(J_QUEST2)).thenReturn(J_QUEST2);
        assertEquals(J_QUEST2, out.remove(J_QUEST2));
    }

    @Test
    void shouldThrowExceptionWhenQuestionNotFound() {
        out.add(J_QUEST1);
        Question notFoundQuestion = new Question(JAVA_QUESTION1, JAVA_ANSWER2);
        when(javaQuestionRepository.remove(notFoundQuestion)).thenThrow(QuestionNotFoundException.class);
        assertThrows(QuestionNotFoundException.class, () -> out.remove(notFoundQuestion));
    }

    @Test
    void shouldReturnCollectionEqualsToTestingSet() {
        when(javaQuestionRepository.getAll()).thenReturn(JAVA_QUESTIONS_SET);
        assertEquals(JAVA_QUESTIONS_SET, out.getAll());
    }

}