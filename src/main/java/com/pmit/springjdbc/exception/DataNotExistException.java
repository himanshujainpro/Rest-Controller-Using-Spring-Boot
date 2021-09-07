package com.pmit.springjdbc.exception;

public class DataNotExistException extends RuntimeException{
    public DataNotExistException(String message){
        super(message);
    }
}
