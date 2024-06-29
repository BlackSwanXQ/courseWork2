package courseWork.courseWork2.service;

import courseWork.courseWork2.exceptions.AmountExceededException;
import courseWork.courseWork2.exceptions.QuestionAlreadyAddedException;
import courseWork.courseWork2.exceptions.QuestionNotFoundException;
import courseWork.courseWork2.exceptions.QuestionsAreEmptyException;
import courseWork.courseWork2.interfaces.QuestionRepository;
import courseWork.courseWork2.interfaces.QuestionService;
import courseWork.courseWork2.question.Question;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@Service("java")
//@Primary
public class JavaQuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;

    public JavaQuestionServiceImpl(@Qualifier("javaRepository") QuestionRepository questionRepository) {
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
//                .orElseThrow(QuestionsAreEmptyException::new);
    }
}
