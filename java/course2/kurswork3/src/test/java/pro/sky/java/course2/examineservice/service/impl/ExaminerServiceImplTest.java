package pro.sky.java.course2.examineservice.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static pro.sky.java.course2.examineservice.constant.Constants.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class ExaminerServiceImplTest {
    @Mock
    private JavaQuestionService javaQuestionService;

    @Mock
    private MathQuestionService mathQuestionService;

    @InjectMocks
    private ExaminerServiceImpl examinerService;

    @Test
    void shouldReturnCorrectAmountOfRandomQuestions() {
        when(javaQuestionService.getRandomQuestion()).
                thenReturn(J_QUEST1).thenReturn(J_QUEST2).thenReturn(J_QUEST3).thenReturn(J_QUEST4);
        when(mathQuestionService.getRandomQuestion()).
                thenReturn(M_QUEST1).thenReturn(M_QUEST2).thenReturn(M_QUEST3).thenReturn(M_QUEST4);
        assertEquals(6, examinerService.getQuestions(6).size());
    }
}