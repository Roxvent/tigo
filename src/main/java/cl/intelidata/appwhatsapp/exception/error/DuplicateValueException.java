package cl.intelidata.appwhatsapp.exception.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class DuplicateValueException extends RuntimeException {

    public DuplicateValueException(String message) {
        super(message);
    }
}
