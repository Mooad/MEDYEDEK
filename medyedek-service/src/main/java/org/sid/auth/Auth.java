package org.sid.auth;

import com.google.common.net.HttpHeaders;


public class Auth implements IAuth{


    @Override
    public boolean authenticateUser(String email, String pass) {
   return false;
    }
    public HttpHeaders createHeaders(String username, String password){
     return null;
    }

}
