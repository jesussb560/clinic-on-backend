package com.app.clinicon.exception;

import java.util.Date;
import java.util.Map;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ExceptionMessage {

    private String message;
    private Date date;
    private int status;

    private Map<String, String> fieldErrors;
    
}
