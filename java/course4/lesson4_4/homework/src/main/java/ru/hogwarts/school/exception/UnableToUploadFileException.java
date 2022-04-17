package ru.hogwarts.school.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import org.apache.logging.log4j.Level;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UnableToUploadFileException extends ApiException {
    public UnableToUploadFileException(Throwable cause) {
        super(ApiException.UNABLE_TO_UPLOAD, cause, Level.ERROR);
    }
}