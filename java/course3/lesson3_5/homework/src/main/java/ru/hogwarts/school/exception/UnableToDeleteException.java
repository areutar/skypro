package ru.hogwarts.school.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.logging.Level;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UnableToDeleteException extends DetailedApiException {
    public UnableToDeleteException(String resourceName, String fieldName, Object fieldValue, Exception e) {
        super(UNABLE_TO_DELETE, resourceName, fieldName, fieldValue, e, Level.SEVERE);
    }
}