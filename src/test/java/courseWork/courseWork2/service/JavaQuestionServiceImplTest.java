package courseWork.courseWork2.service;

import courseWork.courseWork2.exceptions.QuestionAlreadyAddedException;
import courseWork.courseWork2.exceptions.QuestionNotFoundException;
import courseWork.courseWork2.interfaces.QuestionService;
import courseWork.courseWork2.question.Question;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;

class JavaQuestionServiceImplTest {
    private QuestionService service = new JavaQuestionServiceImpl();
    private final List<Question> questions = List.of(
            new Question("question1", "answer1"),
            new Question("question2", "answer2"),
            new Question("question3", "answer3"),
            new Question("question4", "answer4"),
            new Question("question5", "answer5")
    );

    @BeforeEach
    void setUp() {
        questions.forEach(question -> service.add(question.getQuestion(), question.getAnswer()));
    }

    @AfterEach
    void tearDown() {
        service.getAll().forEach(question -> service.remove(question.getQuestion(), question.getAnswer()));
    }

    @Test
    void addQuestionTest() {
        Question expected = new Question("question6", "answer6");
        Question actual = service.add("question6", "answer6");
        assertThat(actual).isEqualTo(expected);
        Assertions.assertThat(actual).isEqualTo(service.find("question6", "answer6"));
        assertThat(actual).isIn(service.getAll());
    }

    @Test
    void addQuestionAlreadyExistTest() {
        assertThatExceptionOfType(QuestionAlreadyAddedException.class)
                .isThrownBy(() -> service.add("question1", "answer1"));

    }

    @Test
    void removeQuestionTest() {
        Question expected = new Question("question1", "answer1");
        Question actual = service.remove("question1", "answer1");
        assertThat(actual).isEqualTo(expected);
        assertThat(actual).isNotIn(service.getAll());
    }
    @Test
    void removeQuestionNotFoundTest() {
        assertThatExceptionOfType(QuestionNotFoundException.class)
                .isThrownBy(() -> service.remove("question8", "answer8"));
    }

    @Test
    void findEmployeeTest() {
        Question expected = new Question("question1", "answer1");
        Assertions.assertThat(service.getAll().contains(expected));
        Question actual = service.find("question1", "answer1");
        Assertions.assertThat(actual).isEqualTo(expected);
    }

    @Test
    void findQuestionNotFoundTest() {
        assertThatExceptionOfType(QuestionNotFoundException.class)
                .isThrownBy(() -> service.find("question10", "answer10"));
    }

    @Test
    void getAllQuestionsTest() {
//        assertThat(service.getAll()).containsExactlyInAnyOrderElementsOf(questions);
        assertThat(service.getAll().containsAll(questions));
    }

    @Test
    void getRandomQuestion() {
    }
}