package courseWork.courseWork2.service;

import courseWork.courseWork2.exceptions.AmountExceededException;
import courseWork.courseWork2.interfaces.QuestionService;
import courseWork.courseWork2.question.Question;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class JavaExaminerServiceImplTest {

    @Mock
    private QuestionService questionService;
    @InjectMocks
    private JavaExaminerServiceImpl examinerService;

    private final List<Question> questions = List.of(
            new Question("question1", "answer1"),
            new Question("question2", "answer2"),
            new Question("question3", "answer3"),
            new Question("question4", "answer4"),
            new Question("question5", "answer5")
    );

    @BeforeEach
    void setUp() {
        when(questionService.getAll()).thenReturn(questions);
    }

    @Test
    void getQuestionsTest() {
        when(questionService.getRandomQuestion()).thenReturn(
                new Question("question2", "answer2"),
                new Question("question2", "answer2"),
                new Question("question4", "answer4"),
                new Question("question1", "answer1"),
                new Question("question4", "answer4"),
                new Question("question5", "answer5")
        );

        Assertions.assertThat(examinerService.getQuestions(4)).containsExactlyInAnyOrder(
                new Question("question1", "answer1"),
                new Question("question2", "answer2"),
                new Question("question4", "answer4"),
                new Question("question5", "answer5")
        );
    }

    @ParameterizedTest
    @ValueSource(ints = {6, -1})
    void getQuestionsThrowException(int amount) {
        assertThatExceptionOfType(AmountExceededException.class)
                .isThrownBy(() -> examinerService.getQuestions(amount));
    }
}