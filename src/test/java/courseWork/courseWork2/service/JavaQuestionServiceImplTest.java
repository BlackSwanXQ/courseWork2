package courseWork.courseWork2.service;

import courseWork.courseWork2.exceptions.QuestionAlreadyAddedException;
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

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.mockito.Mockito.when;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class JavaQuestionServiceImplTest {

    @Mock
    private QuestionRepository javaRepository;
    @InjectMocks
    private JavaQuestionServiceImpl javaQuestionService;

    private final List<Question> questions = List.of(
            new Question("question1", "answer1"),
            new Question("question2", "answer2"),
            new Question("question3", "answer3"),
            new Question("question4", "answer4"),
            new Question("question5", "answer5")
    );


    @BeforeEach
    void beforeEach() {
    }


    @Test
    void addQuestionTest1() {
        when(javaRepository.add(new Question("question6","answer6")))
        .thenReturn(new Question("question6","answer6"));
        Question expected = new Question("question6", "answer6");
        Question actual =javaQuestionService.add(new Question("question6", "answer6"));
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void addQuestionTest2() {
        when(javaRepository.add(new Question("question6","answer6")))
                .thenReturn(new Question("question6","answer6"));
        Question expected = new Question("question6", "answer6");
        Question actual = javaQuestionService.add("question6", "answer6");
        assertThat(actual).isEqualTo(expected);
    }


    @Test
    void removeQuestionTest() {
        when(javaRepository.remove(new Question("question2","answer2")))
                .thenReturn(new Question("question2","answer2"));
        Question expected = new Question("question2", "answer2");
        Question actual = javaQuestionService.remove(new Question("question2", "answer2"));
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void getAllQuestionsTest() {
        when(javaRepository.getAll()).thenReturn(questions);
        Assertions.assertThat(javaQuestionService.getAll())
                .containsExactlyInAnyOrderElementsOf(questions);
    }

    @Test
    void getRandomQuestion() {
        when(javaRepository.getAll()).thenReturn(questions);
        Question actual = javaQuestionService.getRandomQuestion();
        assertThat(actual).isIn(javaRepository.getAll());
    }

    @Test
    void getRandomQuestionException() {
        assertThatExceptionOfType(QuestionsAreEmptyException.class)
                .isThrownBy(()-> javaQuestionService.getRandomQuestion());
    }
}