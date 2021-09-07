package com.pmit.springjdbc.exception;

public class DataNotValidException extends RuntimeException{
    public DataNotValidException(String message){
        super(message);
    }
}
