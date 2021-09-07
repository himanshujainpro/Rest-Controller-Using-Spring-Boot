package com.pmit.springjdbc.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class Response<T> {
    private Status status;
    private T payload;
    private ResponseError errors;
    private String message;


    public static <T> Response<T> badRequest() {
        Response<T> response = new Response<>();
        response.setStatus(Status.BAD_REQUEST);
        return response;
    }

    public static <T> Response<T> ok() {
        Response<T> response = new Response<>();
        response.setStatus(Status.OK);
        return response;
    }


    public static <T> Response<T> exception() {
        Response<T> response = new Response<>();
        response.setStatus(Status.EXCEPTION);
        return response;
    }

    public static <T> Response<T> notFound() {
        Response<T> response = new Response<>();
        response.setStatus(Status.NOT_FOUND);
        return response;
    }

    public static <T> Response<T> duplicateEntity() {
        Response<T> response = new Response<>();
        response.setStatus(Status.DUPLICATE_ENTITY);
        return response;
    }

    public void addErrorMsgToResponse(String errorMsg, Exception ex) {
        ResponseError error = new ResponseError();
        error.setMessage(ex.getMessage());
        error.setDetails(errorMsg);
        error.setTimestamp(new Date());
        setErrors(error);
    }

    public enum Status {
        OK, BAD_REQUEST,EXCEPTION, NOT_FOUND, DUPLICATE_ENTITY
    }
}
