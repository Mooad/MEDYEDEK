package org.sid.exception.api.success;

import org.springframework.http.HttpStatus;

import java.io.Serializable;

public class ApiSuccess implements Serializable {

    private HttpStatus status;
    private String message;

    public ApiSuccess(HttpStatus status, String message) {
        super();
        this.status = status;
        this.message = message;
    }

}
