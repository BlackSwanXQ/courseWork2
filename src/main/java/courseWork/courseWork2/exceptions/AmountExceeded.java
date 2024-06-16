package courseWork.courseWork2.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class AmountExceeded extends  RuntimeException{
    public AmountExceeded(String message) {
        super(message);
    }
}
