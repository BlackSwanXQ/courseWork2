package courseWork.courseWork2.repository;

import courseWork.courseWork2.exceptions.QuestionAlreadyAddedException;
import courseWork.courseWork2.exceptions.QuestionNotFoundException;
import courseWork.courseWork2.interfaces.QuestionRepository;
import courseWork.courseWork2.question.Question;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository("javaRepository")

public class JavaQuestionRepositoryImpl implements QuestionRepository {

    private final Set<Question> javaRepositorySet;

    public JavaQuestionRepositoryImpl() {
        this.javaRepositorySet = new HashSet<>();
    }

    @PostConstruct
    public void init() {
        javaRepositorySet.add(new Question("question1","answer1"));
        javaRepositorySet.add(new Question("question2","answer2"));
        javaRepositorySet.add(new Question("question3","answer3"));
        javaRepositorySet.add(new Question("question4","answer4"));
        javaRepositorySet.add(new Question("question5","answer5"));
    }


    @Override
    public Question add(Question question) {
        if (!javaRepositorySet.add(question)) {
            throw new QuestionAlreadyAddedException("Employee already exist");
        }
        return question;
    }

    @Override
    public Question remove(Question question) {
        if (!javaRepositorySet.remove(question)) {
            throw new QuestionNotFoundException("Employee not found");
        }
        return question;
    }

    @Override
    public Collection<Question> getAll() {
        return Collections.unmodifiableCollection(javaRepositorySet);
    }
}
