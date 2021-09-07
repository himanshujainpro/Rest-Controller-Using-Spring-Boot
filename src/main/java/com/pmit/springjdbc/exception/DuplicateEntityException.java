package com.pmit.springjdbc.exception;

public class DuplicateEntityException extends RuntimeException{
    public DuplicateEntityException(String message){
        super(message);
    }
}
