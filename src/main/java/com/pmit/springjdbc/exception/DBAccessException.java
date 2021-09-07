package com.pmit.springjdbc.exception;

public class DBAccessException extends RuntimeException {
    public DBAccessException(String message) {
        super(message);
    }
}
