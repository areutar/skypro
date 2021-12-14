package pro.sky.employeeswithstream.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus (HttpStatus.BAD_REQUEST)
public class IllegalNameException extends IllegalArgumentException {

}
