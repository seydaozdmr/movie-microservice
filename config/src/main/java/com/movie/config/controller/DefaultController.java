package com.movie.config.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class DefaultController {
    @Value("${app.hello}")
    private String hello;

    @Value("${app.list}")
    private List<String> list;

//    @Value("#{${db}}")
//    private Map<String,String> db;

    @Autowired
    private AppProperties appProperties;

    @GetMapping("/")
    public String getGreetings(){
        return hello;
    }

    @GetMapping("/values")
    public List<String> getValues(){
        return list;
    }

//    @GetMapping("/dbvalues")
//    public Map<String,String> getDbValues(){
//        return db;
//    }

    @GetMapping("/app")
    public String appValues(){
        return appProperties.toString();
    }
}
