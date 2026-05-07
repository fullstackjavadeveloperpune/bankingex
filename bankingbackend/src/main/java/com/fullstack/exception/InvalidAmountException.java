package com.fullstack.exception;

public class InvalidAmountException extends RuntimeException{

    public InvalidAmountException(String msg){
        super(msg);
    }
}
