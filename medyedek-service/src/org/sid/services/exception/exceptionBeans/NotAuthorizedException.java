package org.sid.services.exception.exceptionBeans;

public class NotAuthorizedException extends RuntimeException {


    public NotAuthorizedException() {
        super("No Access to the requested ressource");
    }
}