package pro.sky.employeeswithstream.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class DuplicateEmployeeException extends RuntimeException {
    public DuplicateEmployeeException(String s) {
        super(s);
    }
}
