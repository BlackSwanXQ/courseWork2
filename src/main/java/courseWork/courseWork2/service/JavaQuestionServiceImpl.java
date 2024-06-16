package courseWork.courseWork2.service;

import courseWork.courseWork2.exceptions.QuestionAlreadyAddedException;
import courseWork.courseWork2.exceptions.QuestionNotFoundException;
import courseWork.courseWork2.interfaces.QuestionService;
import courseWork.courseWork2.question.Question;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class JavaQuestionServiceImpl implements QuestionService {

    private final List<Question> questionsList;

    public JavaQuestionServiceImpl() {
        this.questionsList = new ArrayList<>();
    }

    @Override
    public Question add(String question, String answer) {
        Question quest = new Question(question, answer);
        if (questionsList.contains(quest)) {
            throw new QuestionAlreadyAddedException("Employee already exist");
        } else {
            questionsList.add(quest);
        }

        return quest;
    }

    @Override
    public Question remove(String question, String answer) {
        Question quest = new Question(question, answer);
        if (!questionsList.contains(quest)) {
            throw new QuestionNotFoundException("Employee not found");
        } else {
            questionsList.remove(quest);
        }
        return quest;
    }
    @Override
    public Question find(String question, String answer) {
        Question quest = new Question(question, answer);
        if (!questionsList.contains(quest)) {
            throw new QuestionNotFoundException("Employee not found");
        } else {
            questionsList.contains(quest);
        }
        return quest;
    }

    @Override
    public Collection<Question> getAll() {
        return new ArrayList<>(questionsList);
    }

    @Override
    public Question getRandomQuestion() {
        int random = ThreadLocalRandom.current().nextInt(0, questionsList.size());
        return questionsList.get(random);
    }
}
