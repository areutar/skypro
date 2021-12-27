package pro.sky.java.course2.examineservice.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.java.course2.examineservice.exception.TooManyQuestionsRequestException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static pro.sky.java.course2.examineservice.constant.Constants.*;

@ExtendWith(MockitoExtension.class)
class ExaminerServiceImplTest {
    @Mock
    private JavaQuestionService javaQuestionService;

    @InjectMocks
    private ExaminerServiceImpl examinerService;

    @Test
    void shouldThrowTooManyQuestionsRequestException() {
        assertThrows(TooManyQuestionsRequestException.class,
                () -> examinerService.getQuestions(10));
    }

    @Test
    void shouldReturnQuestionsFromTestingSet() {
        when(javaQuestionService.getAll()).thenReturn(ALL_QUESTIONS_SET);
        when(javaQuestionService.getRandomQuestion()).
                thenReturn(QUEST1).thenReturn(QUEST2).thenReturn(QUEST3).thenReturn(QUEST4);
        assertTrue(ALL_QUESTIONS_SET.containsAll(examinerService.getQuestions(4)));
    }

    @Test
    void shouldReturnCorrectAmountOfQuestions() {
        when(javaQuestionService.getAll()).thenReturn(ALL_QUESTIONS_SET);
        when(javaQuestionService.getRandomQuestion()).
                thenReturn(QUEST1).thenReturn(QUEST2).thenReturn(QUEST3).thenReturn(QUEST4);
        assertEquals(examinerService.getQuestions(3).size(), 3);
    }

}