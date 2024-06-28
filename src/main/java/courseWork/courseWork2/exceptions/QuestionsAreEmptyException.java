package courseWork.courseWork2.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class QuestionsAreEmptyException extends RuntimeException {
    public QuestionsAreEmptyException(String message) {
        super(message);
    }
}
