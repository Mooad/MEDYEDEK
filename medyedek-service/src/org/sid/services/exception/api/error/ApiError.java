package org.sid.services.exception.api.error;

import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.List;

public class ApiError {

    private final HttpStatus status;
    private final String message;
    private String code;
    private final List<String> errors;

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
        errors = Collections.singletonList(error);
    }
}