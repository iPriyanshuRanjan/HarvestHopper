package com.HarvestHopper.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test {
    @GetMapping("/")
    String checkResponse(){
        return "Hi It's Working.";
    }
}
