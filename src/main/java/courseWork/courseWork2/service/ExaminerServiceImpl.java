package courseWork.courseWork2.service;

import courseWork.courseWork2.exceptions.AmountExceeded;
import courseWork.courseWork2.interfaces.ExaminerService;
import courseWork.courseWork2.interfaces.QuestionService;
import courseWork.courseWork2.question.Question;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ExaminerServiceImpl implements ExaminerService {
    public final QuestionService questionService;
    private final List<Question> randomQuestionList;

    public ExaminerServiceImpl(QuestionService questionService) {
        this.questionService = questionService;
        this.randomQuestionList = new ArrayList<>();
    }


    @Override
    public Collection<Question> getQuestions(int amount) {
        if (questionService.getAll().size() < amount) {
            throw new AmountExceeded("Amount exceeded");
        }
        randomQuestionList.clear();
        int counter = 0;
        while (counter < amount) {
            Question question = questionService.getRandomQuestion();
            if (!randomQuestionList.contains(question)) {
                randomQuestionList.add(question);
                counter++;
            }
        }
        return randomQuestionList;
    }
}
