package org.sid.auth;

import com.google.common.net.HttpHeaders;

public interface IAuth {

    boolean authenticateUser(String email,String pass);
    HttpHeaders createHeaders(String username, String password);

    }

