package courseWork.courseWork2.service;

import courseWork.courseWork2.exceptions.AmountExceededException;
import courseWork.courseWork2.interfaces.ExaminerService;
import courseWork.courseWork2.interfaces.QuestionService;
import courseWork.courseWork2.question.Question;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("javaExam")
public class JavaExaminerServiceImpl implements ExaminerService {
    public final QuestionService javaQuestionService;

    public JavaExaminerServiceImpl(@Qualifier("java") QuestionService questionService) {
        this.javaQuestionService = questionService;
    }


    @Override
    public Collection<Question> getQuestions(int amount) {
        if (javaQuestionService.getAll().size() < amount || amount <= 0) {
            throw new AmountExceededException("Amount exceeded");
        }
        Set<Question> questions = new HashSet<>();
        while (questions.size() < amount) {
            questions.add(javaQuestionService.getRandomQuestion());
        }
        return questions;
    }
}
