package com.grandprixhub.grandprixhub.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    // Change this mapping from "/" to "/hello"
    @GetMapping("/hello")
    public String helloGrandPrixHub() {
        return "Hello, Grand Prix Hub! Welcome F1 Fans! (DevTools Test)";
    }
}