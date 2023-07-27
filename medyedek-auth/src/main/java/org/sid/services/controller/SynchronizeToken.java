package org.sid.services.controller;

import com.google.gson.Gson;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:4200")
public class SynchronizeToken {

    private static final Gson gson = new Gson();

    @RequestMapping(value="/syncAuth" , method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody   ResponseEntity<String> S() {
        return ResponseEntity.ok(gson.toJson("Synch"));
    }
}