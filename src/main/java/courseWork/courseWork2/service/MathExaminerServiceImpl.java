package courseWork.courseWork2.service;

import courseWork.courseWork2.exceptions.AmountExceededException;
import courseWork.courseWork2.interfaces.ExaminerService;
import courseWork.courseWork2.interfaces.QuestionService;
import courseWork.courseWork2.question.Question;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("mathExam")
public class MathExaminerServiceImpl implements ExaminerService {
    public final QuestionService mathQuestionService;

    public MathExaminerServiceImpl(@Qualifier("math") QuestionService questionService) {
        this.mathQuestionService = questionService;
    }


    @Override
    public Collection<Question> getQuestions(int amount) {
        if (mathQuestionService.getAll().size() < amount || amount <= 0) {
            throw new AmountExceededException("Amount exceeded");
        }
        Set<Question> questions = new HashSet<>();
        while (questions.size() < amount) {
            questions.add(mathQuestionService.getRandomQuestion());
        }
        return questions;
    }
}
