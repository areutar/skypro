package pro.sky.java.course2.examineservice.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import pro.sky.java.course2.examineservice.exception.TooManyQuestionsRequestException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static pro.sky.java.course2.examineservice.constant.Constants.*;

@ExtendWith(MockitoExtension.class)
//@MockitoSettings(strictness = Strictness.LENIENT)
class ExaminerServiceImplTest {
    @Mock
    private QuestionServiceImpl javaQuestionService;

    @Mock
    private QuestionServiceImpl mathQuestionService;

    private ExaminerServiceImpl examinerService;

    @BeforeEach
    void init() {
        examinerService = new ExaminerServiceImpl(javaQuestionService, mathQuestionService);
        when(javaQuestionService.getAll()).thenReturn(JAVA_QUESTIONS_SET);
        when(mathQuestionService.getAll()).thenReturn(MATH_QUESTIONS_SET);
    }

    @Test
    void shouldThrowTooManyQuestionsRequestException() {
        assertThrows(TooManyQuestionsRequestException.class,
                () -> examinerService.getQuestions(11));
    }

    @Test
    void shouldReturnQuestionsFromTestingSet() {
        when(javaQuestionService.getRandomQuestion()).
                thenReturn(J_QUEST1).thenReturn(J_QUEST2).thenReturn(J_QUEST3).thenReturn(J_QUEST4);
        when(mathQuestionService.getRandomQuestion()).
                thenReturn(M_QUEST1).thenReturn(M_QUEST2).thenReturn(M_QUEST3).thenReturn(M_QUEST4);
        assertTrue(ALL_QUESTIONS_SET.containsAll(examinerService.getQuestions(4)));
    }

    @Test
    void shouldReturnCorrectAmountOfRandomQuestions() {
        when(javaQuestionService.getRandomQuestion()).
                thenReturn(J_QUEST1).thenReturn(J_QUEST2).thenReturn(J_QUEST3).thenReturn(J_QUEST4);
        when(mathQuestionService.getRandomQuestion()).
                thenReturn(M_QUEST1).thenReturn(M_QUEST2).thenReturn(M_QUEST3).thenReturn(M_QUEST4);
        assertEquals(3, examinerService.getQuestions(3).size());
    }
}