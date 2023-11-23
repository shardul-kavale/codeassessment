package com.example.assessment.controllers;

import com.example.assessment.models.WordRequestModel;
import com.example.assessment.services.StringReverseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class Transformations {

    private final StringReverseService stringReverseService;

    //Inject StringReverse Service into this class
    @Autowired
    public Transformations(StringReverseService stringReverseService) {
        this.stringReverseService = stringReverseService;
    }

    //Method 1: Receive word through Query Parameters:
    @GetMapping("/reverse")
    public String ReverseGet(@RequestParam("word") String input){
        return stringReverseService.reverseString(input);
    }

    //Method 2: Receive word through Request body:
    @PostMapping("/reverse")
    public String ReversePost(@RequestBody WordRequestModel request){
        String input = request.getWord();
        return stringReverseService.reverseString(input);
    }
}

