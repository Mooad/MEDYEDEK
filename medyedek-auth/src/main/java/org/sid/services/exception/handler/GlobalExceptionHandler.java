package org.sid.services.exception.handler;


import org.sid.services.exception.constants.ErrorCodes;
import org.sid.services.exception.exceptionBeans.NotAuthorizedException;
import org.sid.services.exception.exceptionBeans.UserNotFoundException;
import org.sid.services.exception.api.error.ApiError;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.util.WebUtils;

import java.util.Collections;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {
    /**
     * Provides handling for exceptions throughout this service.
     */
    @ExceptionHandler({UserNotFoundException.class})
    public final ResponseEntity<ApiError> handleException(Exception ex, WebRequest request) {
        HttpHeaders headers = new HttpHeaders();

        if (ex instanceof UserNotFoundException) {
            HttpStatus status = HttpStatus.NOT_FOUND;
            UserNotFoundException unfe = (UserNotFoundException) ex;

            return handleUserNotFoundException(unfe, headers, status, request);
        } else if (ex instanceof NotAuthorizedException) {
            HttpStatus status = HttpStatus.BAD_REQUEST;
            NotAuthorizedException nae = (NotAuthorizedException) ex;

            return handleNotAuthorizedException(nae, headers, status, request);
        } else {
            HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
            return handleExceptionInternal(ex, null, headers, status, request);
        }
    }

    /**
     * Customize the response for UserNotFoundException.
     */
    protected ResponseEntity<ApiError> handleUserNotFoundException(UserNotFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errors = Collections.singletonList(ex.getMessage());
        return handleExceptionInternal(ex, new ApiError(HttpStatus.NOT_FOUND, "the entity is not found", ErrorCodes.ACCOUNT_NOT_FOUND, errors), headers, status, request);
    }

    /**
     * Customize the response for UserNotFoundException.
     */
    protected ResponseEntity<ApiError> handleNotAuthorizedException(NotAuthorizedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errors = Collections.singletonList(ex.getMessage());
        return handleExceptionInternal(ex, new ApiError(HttpStatus.FORBIDDEN, "not authorized exception", ErrorCodes.ACCOUNT_NOT_AUTHORIZED, errors), headers, status, request);
    }


    /**
     * A single place to customize the response body of all Exception types.
     */
    protected ResponseEntity<ApiError> handleAccountAlreadyExistsException(Exception ex, ApiError body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errors = Collections.singletonList(ex.getMessage());
        return handleExceptionInternal(ex, new ApiError(HttpStatus.FORBIDDEN, "Account already exists", ErrorCodes.ACCOUNT_ALREADY_EXISTS, errors), headers, status, request);

    }

    protected ResponseEntity<ApiError> handleExceptionInternal(Exception ex, ApiError body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
            request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);
        }

        return new ResponseEntity<>(body, headers, status);
    }
}

