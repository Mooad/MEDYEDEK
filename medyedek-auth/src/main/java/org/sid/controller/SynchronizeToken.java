package org.sid.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SynchronizeToken {
    @RequestMapping({ "/syncAuth" })
    public String firstPage() {


        return "Hello World";
    }
}