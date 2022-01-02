package pro.sky.java.course2.examineservice.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.beans.factory.annotation.Qualifier;
import pro.sky.java.course2.examineservice.exception.TooManyQuestionsRequestException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static pro.sky.java.course2.examineservice.constant.Constants.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class ExaminerServiceImplTest {
    @Mock
    @Qualifier("javaQuestionService")
    private QuestionServiceImpl javaQuestionService;

    @Mock
    @Qualifier("mathQuestionService")
    private QuestionServiceImpl mathQuestionService;

    @InjectMocks
    private ExaminerServiceImpl examinerService;

    @BeforeEach
    void init() {
//        examinerService = new ExaminerServiceImpl(javaQuestionService, mathQuestionService);
        when(javaQuestionService.getAll()).thenReturn(JAVA_QUESTIONS_SET);
        when(mathQuestionService.getAll()).thenReturn(MATH_QUESTIONS_SET);
        when(javaQuestionService.getRandomQuestion()).
                thenReturn(J_QUEST1).thenReturn(J_QUEST2).thenReturn(J_QUEST3).thenReturn(J_QUEST4);
        when(mathQuestionService.getRandomQuestion()).
                thenReturn(M_QUEST1).thenReturn(M_QUEST2).thenReturn(M_QUEST3).thenReturn(M_QUEST4);
    }

    @Test
    void shouldThrowTooManyQuestionsRequestException() {
        assertThrows(TooManyQuestionsRequestException.class,
                () -> examinerService.getQuestions(9));
    }

    @Test
    void shouldReturnQuestionsFromTestingSet() {
        assertTrue(ALL_QUESTIONS_SET.containsAll(examinerService.getQuestions(4)));
    }

    @Test
    void shouldReturnCorrectAmountOfRandomQuestions() {
        assertEquals(examinerService.getQuestions(3).size(), 3);
    }

}