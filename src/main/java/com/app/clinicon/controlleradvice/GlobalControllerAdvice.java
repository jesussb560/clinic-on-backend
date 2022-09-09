package com.app.clinicon.controlleradvice;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(annotations = Controller.class)
public class GlobalControllerAdvice {

    @ExceptionHandler(value = EntityNotFoundException.class)
    public String entityNotFoundExceptionHandler(EntityNotFoundException exception) {
        return "";
    }
    
}
