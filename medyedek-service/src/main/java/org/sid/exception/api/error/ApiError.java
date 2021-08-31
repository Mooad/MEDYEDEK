package org.sid.exception.api.error;

import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;

public class ApiError {

    private HttpStatus status;
    private String message;
    private String code;
    private List<String> errors;

    public ApiError(HttpStatus status, String message, String code,List<String> errors) {
        super();
        this.status = status;
        this.message = message;
        this.code = code;
        this.errors = errors;
    }

    public ApiError(HttpStatus status, String message, String error) {
        super();
        this.status = status;
        this.message = message;
        errors = Arrays.asList(error);
    }
}