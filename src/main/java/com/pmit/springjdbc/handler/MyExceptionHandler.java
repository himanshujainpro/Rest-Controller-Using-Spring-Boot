package com.pmit.springjdbc.handler;


import com.pmit.springjdbc.dto.Response;
import com.pmit.springjdbc.exception.DataNotExistException;
import com.pmit.springjdbc.exception.DataNotValidException;
import com.pmit.springjdbc.exception.DuplicateEntityException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLException;

@ControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(DuplicateEntityException.class)
    public ResponseEntity<Object> handleDuplicateEntity(DuplicateEntityException e) {
        Response<Object> response=Response.duplicateEntity();
        response.addErrorMsgToResponse(e.getMessage(), e);
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<Object> handleSqlException(SQLException e) {
        Response<Object> response=Response.notFound();
        response.addErrorMsgToResponse(e.getMessage(), e);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(DataNotExistException.class)
    public ResponseEntity<Object> handleDataNotExistException(DataNotExistException e) {
        Response<Object> response=Response.notFound();
        response.addErrorMsgToResponse(e.getMessage(), e);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DataNotValidException.class)
    public ResponseEntity<Object> handleDataNotValidException(DataNotValidException e) {
        Response<Object> response=Response.badRequest();
        response.addErrorMsgToResponse(e.getMessage(), e);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception e) {
        Response<Object> response=Response.exception();
        response.addErrorMsgToResponse(e.getMessage(), e);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
