package ru.hogwarts.school.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.logging.Level;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UnableToUpdateException extends ApiException {
    public UnableToUpdateException(Throwable cause) {
        super(UNABLE_TO_UPDATE, cause, Level.SEVERE);
    }
}
