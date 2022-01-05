package pro.sky.java.course2.examineservice.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static pro.sky.java.course2.examineservice.constant.Constants.*;

@ExtendWith(MockitoExtension.class)
//@MockitoSettings(strictness = Strictness.LENIENT)
class ExaminerServiceImplTest {
    @Mock
    private JavaQuestionService javaQuestionService;

    @Mock
    private MathQuestionService mathQuestionService;

    private ExaminerServiceImpl examinerService;

    @BeforeEach
    void init() {
        examinerService = new ExaminerServiceImpl(List.of(javaQuestionService, mathQuestionService));
        when(javaQuestionService.getRandomQuestion()).
                thenReturn(J_QUEST1).thenReturn(J_QUEST2).thenReturn(J_QUEST3).thenReturn(J_QUEST4);
        when(mathQuestionService.getRandomQuestion()).
                thenReturn(M_QUEST1).thenReturn(M_QUEST2).thenReturn(M_QUEST3).thenReturn(M_QUEST4);
    }

    @Test
    void shouldReturnCorrectAmountOfRandomQuestions() {
        assertEquals(6, examinerService.getQuestions(6).size());
    }

    @Test
    void shouldReturnQuestionsOnlyFromTestingSet() {
        assertTrue(ALL_QUESTIONS_SET.containsAll(examinerService.getQuestions(8)));
    }
}