package courseWork.courseWork2.interfaces;

import courseWork.courseWork2.question.Question;

import java.util.Collection;

public interface QuestionRepository {
    Question add(Question question);
    Question remove(Question question);
    Collection<Question> getAll();
    }
