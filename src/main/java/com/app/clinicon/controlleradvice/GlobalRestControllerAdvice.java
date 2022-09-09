package com.app.clinicon.controlleradvice;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.mail.MailException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.app.clinicon.exception.EntityAlreadyExistsException;
import com.app.clinicon.exception.ExceptionMessage;

import io.jsonwebtoken.JwtException;

@RestControllerAdvice(annotations = RestController.class)
public class GlobalRestControllerAdvice {

    @ExceptionHandler(value = EntityNotFoundException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ExceptionMessage entityNotFoundExceptionHandler(EntityNotFoundException exception) {

        return ExceptionMessage.builder()
                .message(exception.getMessage())
                .date(new Date())
                .status(HttpStatus.BAD_REQUEST.value())
                .build();
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ExceptionMessage methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException exception){

        Map<String, String> fieldErrors = new HashMap<>();

        exception.getBindingResult()
        .getFieldErrors()
        .forEach(error -> fieldErrors.put(error.getField(), error.getDefaultMessage()));

        return ExceptionMessage.builder()
                .message(exception.getMessage())
                .fieldErrors(fieldErrors)
                .date(new Date())
                .status(HttpStatus.BAD_REQUEST.value())
                .build();
    }

    @ExceptionHandler(value = MessagingException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionMessage messagingExceptionHandler(MessagingException exception){

        return ExceptionMessage.builder()
                .message(exception.getMessage())
                .date(new Date())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .build();
    }

    @ExceptionHandler(value = MailException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionMessage mailExceptionHandler(MailException exception){

        return ExceptionMessage.builder()
                .message(exception.getMessage())
                .date(new Date())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .build();
    }

    @ExceptionHandler(value = JwtException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ExceptionMessage jwtExceptionHandler(JwtException exception){

        return ExceptionMessage.builder()
                .message(exception.getMessage())
                .date(new Date())
                .status(HttpStatus.BAD_REQUEST.value())
                .build();
    }

    @ExceptionHandler(value = EntityAlreadyExistsException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ExceptionMessage entityAlreadyExistsExceptionHandler(EntityAlreadyExistsException exception){

        return ExceptionMessage.builder()
                .message(exception.getMessage())
                .date(new Date())
                .status(HttpStatus.BAD_REQUEST.value())
                .build();
    }

}
