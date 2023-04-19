package com.ead.authuser.controller;

import javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class Handler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handle(Exception ex,
                                         HttpServletRequest request, HttpServletResponse response) {
        if (ex instanceof NullPointerException) {
            return new ResponseEntity<>("{\"message\":\"Has Data Missing\"}", HttpStatus.BAD_REQUEST);
        }

        if (ex instanceof NotFoundException) {
            return new ResponseEntity<>("{\"message\":\"".concat(ex.getMessage()).concat("\"}"), HttpStatus.NOT_FOUND);
        }

        if (ex instanceof SecurityException) {
            return new ResponseEntity<>("{\"message\":\"".concat(ex.getMessage()).concat("\"}"), HttpStatus.CONFLICT);
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}