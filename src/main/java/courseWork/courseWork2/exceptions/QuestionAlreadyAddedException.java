package courseWork.courseWork2.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class QuestionAlreadyAddedException extends RuntimeException {
    public QuestionAlreadyAddedException(String message) {
        super(message);
    }
}
