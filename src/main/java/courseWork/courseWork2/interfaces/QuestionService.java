package courseWork.courseWork2.interfaces;

import courseWork.courseWork2.question.Question;

import java.util.Collection;

public interface QuestionService {
    Question add(String question, String answer);
    Question add(Question question);
    Question remove(String question, String answer);
    Question find(String question, String answer);
    Collection<Question> getAll();
    Question getRandomQuestion();
}
