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
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MathExaminerServiceImplTest {

    @Mock
    private QuestionService questionService;
    @InjectMocks
    private MathExaminerServiceImpl examinerService;

    private final List<Question> questions = List.of(
            new Question("1plus1", "2"),
            new Question("2plus2", "4"),
            new Question("8minus3", "5"),
            new Question("3multiply3", "9"),
            new Question("4divide1", "4")
    );

    @BeforeEach
    void setUp() {
        when(questionService.getAll()).thenReturn(questions);
    }

    @Test
    void getQuestionsTest() {
        when(questionService.getRandomQuestion()).thenReturn(
                new Question("1plus1", "2"),
                new Question("1plus1", "2"),
                new Question("8minus3", "5"),
                new Question("8minus3", "5"),
                new Question("4divide1", "4"),
                new Question("9divide9", "1")
        );

        Assertions.assertThat(examinerService.getQuestions(4)).containsExactlyInAnyOrder(
                new Question("1plus1", "2"),
                new Question("8minus3", "5"),
                new Question("4divide1", "4"),
                new Question("9divide9", "1")
        );
    }

    @ParameterizedTest
    @ValueSource(ints = {6, -1})
    void getQuestionsThrowException(int amount) {
        assertThatExceptionOfType(AmountExceededException.class)
                .isThrownBy(() -> examinerService.getQuestions(amount));
    }
}