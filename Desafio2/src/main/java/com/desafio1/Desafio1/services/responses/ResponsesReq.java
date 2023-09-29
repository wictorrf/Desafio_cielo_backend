package com.desafio1.Desafio1.services.responses;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponsesReq<T> {
    
    private boolean success;
    private String message;
    private T data;

    public ResponsesReq(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public static <T> ResponseEntity<ResponsesReq<T>> success(String message, T data){
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponsesReq<>(true, message, data));
    }

    public static <T> ResponseEntity<ResponsesReq<T>> error(String message){
        return ResponseEntity.badRequest().body(new ResponsesReq<>(false, message, null));
    }

    public boolean isSuccess(){
        return success;
    }
    public String getMessage(){
        return message;
    }
    public T getData(){
        return data;
    }

    public void setErrors(List<String> validationErrors) {
    }
}
