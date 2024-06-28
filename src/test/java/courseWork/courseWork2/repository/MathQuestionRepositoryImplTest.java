package courseWork.courseWork2.repository;

import courseWork.courseWork2.exceptions.QuestionAlreadyAddedException;
import courseWork.courseWork2.exceptions.QuestionNotFoundException;
import courseWork.courseWork2.interfaces.QuestionRepository;
import courseWork.courseWork2.question.Question;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MathQuestionRepositoryImplTest {

    private final QuestionRepository service = new JavaQuestionRepositoryImpl();
    private final List<Question> questions = List.of(
            new Question("1plus1", "2"),
            new Question("2plus", "4"),
            new Question("7minus1", "6"),
            new Question("3multiply3", "9"),
            new Question("9divide3", "3")
    );
    @BeforeEach
    void beforeEach() {
        questions.forEach(question -> service.add(new Question(question.getQuestion(), question.getAnswer())));
    }

    @AfterEach
    void afterEach() {
        Collection<Question> q = new ArrayList<>(service.getAll());
        q.forEach(service::remove);
    }

    @Test
    void addQuestionTest() {
        int was = service.getAll().size();
        Question expected = new Question("6plus6", "12");
        Assertions.assertThat(service.getAll()).doesNotContain(expected);
        Question actual = service.add(new Question("6plus6", "12"));
        assertThat(actual).isEqualTo(expected);
        Assertions.assertThat(service.getAll()).hasSize(was + 1);
        Assertions.assertThat(service.getAll()).contains(expected);
        assertThat(actual).isIn(service.getAll());
    }

    @Test
    void addQuestionAlreadyExistTest() {
        assertThatExceptionOfType(QuestionAlreadyAddedException.class)
                .isThrownBy(() -> service.add(new Question("1plus1", "2")));
    }

    @Test
    void removeQuestionTest() {
        int was = service.getAll().size();
        Question expected = new Question("9divide3", "3");
        Assertions.assertThat(service.getAll()).contains(expected);
        Question actual = service.remove(new Question("9divide3", "3"));
        assertThat(actual).isEqualTo(expected);
        Assertions.assertThat(service.getAll()).hasSize(was - 1);
        Assertions.assertThat(service.getAll()).doesNotContain(expected);
        assertThat(actual).isNotIn(service.getAll());
    }

    @Test
    void removeQuestionNotFoundTest() {
        assertThatExceptionOfType(QuestionNotFoundException.class)
                .isThrownBy(() -> service.remove(new Question("5divide5", "1")));
    }

    @Test
    void getAllQuestionsTest() {
        Assertions.assertThat(service.getAll()).containsExactlyInAnyOrderElementsOf(questions);
    }
}