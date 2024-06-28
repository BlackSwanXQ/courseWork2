package courseWork.courseWork2.repository;

import courseWork.courseWork2.exceptions.QuestionAlreadyAddedException;
import courseWork.courseWork2.exceptions.QuestionNotFoundException;
import courseWork.courseWork2.interfaces.QuestionRepository;
import courseWork.courseWork2.question.Question;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository("mathRepository")
public class MathQuestionRepositoryImpl implements QuestionRepository {

    private final Set<Question> mathRepositorySet;

    public MathQuestionRepositoryImpl() {
        this.mathRepositorySet = new HashSet<>();
    }

    @PostConstruct
    public void init() {
        mathRepositorySet.add(new Question("1plus1","2"));
        mathRepositorySet.add(new Question("2plus2","4"));
        mathRepositorySet.add(new Question("5minus3","2"));
        mathRepositorySet.add(new Question("6multiply6","36"));
        mathRepositorySet.add(new Question("10divide2","5"));
    }


    @Override
    public Question add(Question question) {
        if (!mathRepositorySet.add(question)) {
            throw new QuestionAlreadyAddedException("Employee already exist");
        }
        return question;
    }

    @Override
    public Question remove(Question question) {
        if (!mathRepositorySet.remove(question)) {
            throw new QuestionNotFoundException("Employee not found");
        }
        return question;
    }

    @Override
    public Collection<Question> getAll() {
        return Collections.unmodifiableCollection(mathRepositorySet);
    }
}
