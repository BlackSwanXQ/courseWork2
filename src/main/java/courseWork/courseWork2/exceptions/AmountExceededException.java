package courseWork.courseWork2.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class AmountExceededException extends  RuntimeException{
    public AmountExceededException(String message) {
        super(message);
    }
}
