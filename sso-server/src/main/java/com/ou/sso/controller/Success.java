package com.ou.sso.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class Success {
    @RequestMapping("hi")
    public String hi(){
        return "hi";
    }
}
