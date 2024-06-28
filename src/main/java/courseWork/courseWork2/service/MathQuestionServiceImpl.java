package courseWork.courseWork2.service;

import courseWork.courseWork2.exceptions.QuestionAlreadyAddedException;
import courseWork.courseWork2.exceptions.QuestionNotFoundException;
import courseWork.courseWork2.exceptions.QuestionsAreEmptyException;
import courseWork.courseWork2.interfaces.QuestionRepository;
import courseWork.courseWork2.interfaces.QuestionService;
import courseWork.courseWork2.question.Question;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

@Service("math")
public class MathQuestionServiceImpl implements QuestionService {
    private final QuestionRepository questionRepository;

    public MathQuestionServiceImpl(@Qualifier("mathRepository") QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public Question add(String question, String answer) {
        return add(new Question(question, answer));
    }

    @Override
    public Question add(Question question) {
        return questionRepository.add(question);
    }

    @Override
    public Question remove(Question question) {
        return questionRepository.remove(question);
    }


    @Override
    public Collection<Question> getAll() {
        return questionRepository.getAll();
    }

    @Override
    public Question getRandomQuestion() {
        if (getAll().isEmpty()) {
            throw new QuestionsAreEmptyException("No questions were added");
        }
        int randomQuestion = ThreadLocalRandom.current().nextInt(getAll().size());
        return getAll().stream()
                .skip(randomQuestion)
                .findFirst()
                .orElseThrow();
    }
}
