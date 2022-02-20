package ru.hogwarts.school.exception;

public class ApiException extends RuntimeException {
    public static final String UNABLE_TO_UPDATE = "Unable to update exception";
    public static final String UNABLE_TO_CREATE = "Unable to create exception";
    public static final String UNABLE_TO_DELETE = "Unable to delete exception";
    public static final String BAD_REQUEST = "Bad request exception";
    public static final String NOT_FOUND = "Not found exception";

    public ApiException(String message) {
        super(message);
    }

    public ApiException(String message, Throwable cause) {
        super(message, cause);
    }
}
