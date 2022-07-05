package com.networkedassets.api.handlers;

import com.networkedassets.api.errors.ErrorResponse;
import com.networkedassets.api.ex.StoreException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class StoreFailureHandler {

    @ExceptionHandler(StoreException.class)
    @ResponseBody
    ResponseEntity<ErrorResponse> handleFileUploadException(HttpServletRequest request, StoreException ex) {
        ErrorResponse response = new ErrorResponse("Store issue", ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
