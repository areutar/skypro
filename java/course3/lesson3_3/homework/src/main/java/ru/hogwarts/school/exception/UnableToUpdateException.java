package ru.hogwarts.school.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UnableToUpdateException extends RuntimeException{
    public UnableToUpdateException(Throwable cause) {
        super(cause);
    }
}
