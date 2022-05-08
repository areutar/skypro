package ru.hogwarts.school.exception;

import org.apache.logging.log4j.Level;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundException extends DetailedApiException {
    public NotFoundException(String resourceName, String fieldName, Object fieldValue, Exception e) {
        super(UNABLE_TO_FIND, resourceName, fieldName, fieldValue, e, Level.WARN);
    }
}