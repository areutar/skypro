package pro.sky.employeeswithmock.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus (HttpStatus.BAD_REQUEST)
public class
IllegalSalaryException extends IllegalArgumentException {

}
