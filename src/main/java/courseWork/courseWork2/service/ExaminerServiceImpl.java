package courseWork.courseWork2.service;

import courseWork.courseWork2.exceptions.AmountExceededException;
import courseWork.courseWork2.interfaces.ExaminerService;
import courseWork.courseWork2.interfaces.QuestionService;
import courseWork.courseWork2.question.Question;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ExaminerServiceImpl implements ExaminerService {
    public final QuestionService questionService;

    public ExaminerServiceImpl(QuestionService questionService) {
        this.questionService = questionService;
    }


    @Override
    public Collection<Question> getQuestions(int amount) {
        if (questionService.getAll().size() < amount || amount < 0) {
            throw new AmountExceededException("Amount exceeded");
        }
        Set<Question> questions = new HashSet<>();
        while (questions.size() < amount) {
            questions.add(questionService.getRandomQuestion());
        }
        return questions;
    }
}
