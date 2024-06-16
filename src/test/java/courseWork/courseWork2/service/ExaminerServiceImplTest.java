package courseWork.courseWork2.service;


import courseWork.courseWork2.exceptions.AmountExceeded;
import courseWork.courseWork2.interfaces.QuestionService;
import courseWork.courseWork2.question.Question;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExaminerServiceImplTest {

    @Mock
    private JavaQuestionServiceImpl questionService;
    @InjectMocks
    private ExaminerServiceImpl examinerService;

    private final List<Question> questions = List.of(
            new Question("question1", "answer1"),
            new Question("question2", "answer2"),
            new Question("question3", "answer3"),
            new Question("question4", "answer4"),
            new Question("question5", "answer5")
    );

    private final Question question1 = new Question("question1", "answer1");


    @Test
    void getQuestionsTest() {
        when(questionService.getAll()).thenReturn(questions);
        when(questionService.getRandomQuestion()).thenReturn(questions.get(4));
        Assertions.assertThat(examinerService.getQuestions(1)).contains(questions.get(4));
    }
    @Test
    void getQuestionsThrowException() {
        when(questionService.getAll()).thenReturn(questions);
        assertThatExceptionOfType(AmountExceeded.class)
                .isThrownBy(() -> examinerService.getQuestions(6));
    }
}