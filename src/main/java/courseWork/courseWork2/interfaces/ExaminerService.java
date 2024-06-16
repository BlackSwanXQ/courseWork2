package courseWork.courseWork2.interfaces;

import courseWork.courseWork2.question.Question;

import java.util.Collection;

public interface ExaminerService {
Collection<Question> getQuestions(int amount);
}
