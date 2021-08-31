package org.sid.controller;

import com.google.gson.Gson;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SynchronizeToken {

    private static final Gson gson = new Gson();

    @RequestMapping(value="/syncAuth" , method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody   ResponseEntity<String> S() {
        return ResponseEntity.ok(gson.toJson("Synch"));
    }
}