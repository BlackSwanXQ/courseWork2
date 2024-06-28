package courseWork.courseWork2.repository;

import courseWork.courseWork2.exceptions.QuestionAlreadyAddedException;
import courseWork.courseWork2.exceptions.QuestionNotFoundException;
import courseWork.courseWork2.exceptions.QuestionsAreEmptyException;
import courseWork.courseWork2.interfaces.QuestionRepository;
import courseWork.courseWork2.interfaces.QuestionService;
import courseWork.courseWork2.question.Question;
import courseWork.courseWork2.service.JavaQuestionServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

class JavaQuestionRepositoryImplTest {
    private final QuestionRepository service = new JavaQuestionRepositoryImpl();
    private final List<Question> questions = List.of(
            new Question("question1", "answer1"),
            new Question("question2", "answer2"),
            new Question("question3", "answer3"),
            new Question("question4", "answer4"),
            new Question("question5", "answer5")
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
        Question expected = new Question("question6", "answer6");
        Assertions.assertThat(service.getAll()).doesNotContain(expected);
        Question actual = service.add(new Question("question6", "answer6"));
        assertThat(actual).isEqualTo(expected);
        Assertions.assertThat(service.getAll()).hasSize(was + 1);
        Assertions.assertThat(service.getAll()).contains(expected);
        assertThat(actual).isIn(service.getAll());
    }


    @Test
    void addQuestionAlreadyExistTest() {
        assertThatExceptionOfType(QuestionAlreadyAddedException.class)
                .isThrownBy(() -> service.add(new Question("question1", "answer1")));
    }

    @Test
    void removeQuestionTest() {
        int was = service.getAll().size();
        Question expected = new Question("question2", "answer2");
        Assertions.assertThat(service.getAll()).contains(expected);
        Question actual = service.remove(new Question("question2", "answer2"));
        assertThat(actual).isEqualTo(expected);
        Assertions.assertThat(service.getAll()).hasSize(was - 1);
        Assertions.assertThat(service.getAll()).doesNotContain(expected);
        assertThat(actual).isNotIn(service.getAll());
    }

    @Test
    void removeQuestionNotFoundTest() {
        assertThatExceptionOfType(QuestionNotFoundException.class)
                .isThrownBy(() -> service.remove(new Question("question8", "answer8")));
    }

    @Test
    void getAllQuestionsTest() {
        Assertions.assertThat(service.getAll()).containsExactlyInAnyOrderElementsOf(questions);
    }

}