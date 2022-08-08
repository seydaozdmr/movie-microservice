package com.movie.movieinfo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConfigTest {
    @Value("${app.hello}")
    private String hello;

    @GetMapping("/hello")
    public String getGreetings(){
        return hello;
    }
}
