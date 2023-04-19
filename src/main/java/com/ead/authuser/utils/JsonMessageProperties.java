package com.ead.authuser.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class JsonMessageProperties {
    private String message;
    private HttpStatus httpStatus;

}
