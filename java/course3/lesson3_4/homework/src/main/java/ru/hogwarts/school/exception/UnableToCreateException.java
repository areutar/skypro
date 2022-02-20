package ru.hogwarts.school.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UnableToCreateException extends ApiException{
    public UnableToCreateException(String message) {
        super(message);
    }

    public UnableToCreateException(String message, Throwable cause) {
        super(message, cause);
    }
}
