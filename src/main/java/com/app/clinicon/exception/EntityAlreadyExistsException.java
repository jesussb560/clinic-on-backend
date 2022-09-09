package com.app.clinicon.exception;

public class EntityAlreadyExistsException extends Exception {

    public EntityAlreadyExistsException(String message){
        super(message);
    }

    public EntityAlreadyExistsException(String message, Throwable throwable){
        super(message, throwable);
    }
    
}
