package org.sid.services.exception.exceptionBeans;


import org.sid.services.exception.constants.ErrorCodes;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)

public class DataSavingException  extends RuntimeException {
    public DataSavingException(String userId) {
        super(String.format(ErrorCodes.DATA_SAVING_ERROR));
    }
}
