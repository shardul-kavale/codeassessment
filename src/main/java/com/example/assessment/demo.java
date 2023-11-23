package com.example.assessment;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class demo {
    @GetMapping("/hello")
    public String HelloWorld(){
        return "Hello World";
    }
}
