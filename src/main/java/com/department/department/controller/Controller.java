package com.department.department.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/hello-world")
@RestController

public class Controller {

    @GetMapping

    public String Hello(){
        return "Hello world";
    }
}
