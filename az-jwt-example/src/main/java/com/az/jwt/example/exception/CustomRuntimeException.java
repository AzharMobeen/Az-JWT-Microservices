package com.az.jwt.example.exception;

import lombok.Getter;

@Getter
public class CustomRuntimeException extends RuntimeException{

    private String message;
    private String detail;

    public CustomRuntimeException(String message, String detail){
        super(message);
        this.message = message;
        this.detail = detail;
    }
}
