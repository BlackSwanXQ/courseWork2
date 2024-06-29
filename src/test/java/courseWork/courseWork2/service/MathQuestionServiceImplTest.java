package courseWork.courseWork2.service;

import courseWork.courseWork2.exceptions.QuestionsAreEmptyException;
import courseWork.courseWork2.interfaces.QuestionRepository;
import courseWork.courseWork2.question.Question;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MathQuestionServiceImplTest {

    @Mock
    private QuestionRepository mathRepository;
    @InjectMocks
    private MathQuestionServiceImpl mathQuestionService;

    private final List<Question> questions = List.of(
            new Question("1plus1", "2"),
            new Question("2plus2", "4"),
            new Question("5minus4", "1"),
            new Question("2multiply3", "6"),
            new Question("8divide1", "8")
    );


    @BeforeEach
    void beforeEach() {
    }


    @Test
    void addQuestionTest1() {
        when(mathRepository.add(new Question("5plus5", "10")))
                .thenReturn(new Question("5plus5", "10"));
        Question expected = new Question("5plus5", "10");
        Question actual = mathQuestionService.add(new Question("5plus5", "10"));
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void addQuestionTest2() {
        when(mathRepository.add(new Question("5plus5", "10")))
                .thenReturn(new Question("5plus5", "10"));
        Question expected = new Question("5plus5", "10");
        Question actual = mathQuestionService.add("5plus5", "10");
        assertThat(actual).isEqualTo(expected);
    }


    @Test
    void removeQuestionTest() {
        when(mathRepository.remove(new Question("1plus1", "2")))
                .thenReturn(new Question("1plus1", "2"));
        Question expected = new Question("1plus1", "2");
        Question actual = mathQuestionService.remove(new Question("1plus1", "2"));
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void getAllQuestionsTest() {
        when(mathRepository.getAll()).thenReturn(questions);
        Assertions.assertThat(mathQuestionService.getAll())
                .containsExactlyInAnyOrderElementsOf(questions);
    }

    @Test
    void getRandomQuestion() {
        when(mathRepository.getAll()).thenReturn(questions);
        Question actual = mathQuestionService.getRandomQuestion();
        assertThat(actual).isIn(mathRepository.getAll());
    }

    @Test
    void getRandomQuestionException() {
        assertThatExceptionOfType(QuestionsAreEmptyException.class)
                .isThrownBy(() -> mathQuestionService.getRandomQuestion());
    }
}