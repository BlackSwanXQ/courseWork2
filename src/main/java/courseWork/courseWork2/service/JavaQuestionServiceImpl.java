package courseWork.courseWork2.service;

import courseWork.courseWork2.exceptions.AmountExceededException;
import courseWork.courseWork2.exceptions.QuestionAlreadyAddedException;
import courseWork.courseWork2.exceptions.QuestionNotFoundException;
import courseWork.courseWork2.interfaces.QuestionService;
import courseWork.courseWork2.question.Question;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class JavaQuestionServiceImpl implements QuestionService {

    private final Set<Question> questionsSet;

    public JavaQuestionServiceImpl() {
        this.questionsSet = new HashSet<>();
    }

    @Override
    public Question add(String question, String answer) {
        Question quest = new Question(question, answer);
        if (questionsSet.contains(quest)) {
            throw new QuestionAlreadyAddedException("Employee already exist");
        } else {
            questionsSet.add(quest);
        }
        return quest;
    }

    @Override
    public Question add(Question question) {
        if (questionsSet.contains(question)) {
            throw new QuestionAlreadyAddedException("Employee already exist");
        } else {
            questionsSet.add(question);
        }
        questionsSet.add(question);
        return question;
    }

    @Override
    public Question remove(String question, String answer) {
        Question quest = new Question(question, answer);
        if (!questionsSet.contains(quest)) {
            throw new QuestionNotFoundException("Employee not found");
        } else {
            questionsSet.remove(quest);
        }
        return quest;
    }

    @Override
    public Question find(String question, String answer) {
        Question quest = new Question(question, answer);
        if (!questionsSet.contains(quest)) {
            throw new QuestionNotFoundException("Employee not found");
        }
        return quest;
    }

    @Override
    public Collection<Question> getAll() {
        return new ArrayList<>(questionsSet);
    }

    @Override
    public Question getRandomQuestion() {
        int random = ThreadLocalRandom.current().nextInt(0, questionsSet.size());
        return questionsSet.stream().skip(random).findFirst().get();
    }
}
