package org.sid.services.exception.api.success;

import org.springframework.http.HttpStatus;

import java.io.Serializable;

public class ApiSuccess implements Serializable {

    private final HttpStatus status;
    private final String message;

    public ApiSuccess(HttpStatus status, String message) {
        super();
        this.status = status;
        this.message = message;
    }

}