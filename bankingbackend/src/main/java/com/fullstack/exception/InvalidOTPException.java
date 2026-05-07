package com.fullstack.exception;

public class InvalidOTPException extends RuntimeException{

    public InvalidOTPException(String msg){
        super(msg);
    }
}
