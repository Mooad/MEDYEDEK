package org.sid.exception.exceptionBeans;

import org.sid.exception.constants.ErrorCodes;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class AccountAlreadyExistsException extends RuntimeException {
    public AccountAlreadyExistsException(String userId) {
        super(String.format(ErrorCodes.ACCOUNT_ALREADY_EXISTS));
    }
}