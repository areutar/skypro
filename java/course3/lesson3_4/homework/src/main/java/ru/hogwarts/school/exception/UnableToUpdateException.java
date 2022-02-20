package ru.hogwarts.school.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UnableToUpdateException extends ApiException {
    public UnableToUpdateException(String message) {
        super(message);
    }

    public UnableToUpdateException(String message, Throwable cause) {
        super(message, cause);
    }
}
