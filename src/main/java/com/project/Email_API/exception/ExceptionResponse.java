package com.project.Email_API.exception;

import com.project.Email_API.model.Exception;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Date;

@RestControllerAdvice
public class ExceptionResponse extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseBody
    public ResponseEntity entityNotFoundException() {
       ArrayList details = new ArrayList();
       details.add("User not found.");
       Exception exception = new Exception(new Date(), 404, details);
       return new ResponseEntity(exception, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    public ResponseEntity illegalArgumentException() {
        ArrayList details = new ArrayList();
        details.add("All fields need to be filled.");
        Exception exception = new Exception(new Date(), 400, details);
        return new ResponseEntity(exception, HttpStatus.BAD_REQUEST);
    }
}
