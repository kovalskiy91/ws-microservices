package ws.microservices.insurance.controllers.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ws.microservices.insurance.services.ModelDoesNotExistException;
import ws.microservices.insurance.services.ModelNotFoundException;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(ModelNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public String handle(ModelNotFoundException e) {
        return e.getMessage();
    }

    @ExceptionHandler(ModelDoesNotExistException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public String handle(ModelDoesNotExistException e) {
        return e.getMessage();
    }
}
