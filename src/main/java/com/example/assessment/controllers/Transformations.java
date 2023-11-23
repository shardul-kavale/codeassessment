package com.example.assessment.controllers;

import com.example.assessment.models.ErrorResponse;
import com.example.assessment.models.WordRequestModel;
import com.example.assessment.services.StringReverseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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
    public ResponseEntity<?> reverseGet(@RequestParam(name = "word", required = false) String input){
        if (input == null ) {
            ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Query param 'word' not found. Check request url again");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
        if (input.isEmpty()) {
            ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Word is empty. Please re check request url");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }

        String reversedWord = stringReverseService.reverseString(input);
        return ResponseEntity.status(HttpStatus.OK).body(reversedWord);
    }

    //Method 2: Receive word through Request body:
    @PostMapping("/reverse")
    public ResponseEntity<?> reversePost(@Valid @RequestBody WordRequestModel request, BindingResult result){
        if (result.hasErrors()) {
            ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Invalid request body: " + result.getFieldError().getDefaultMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
        String input = request.getWord();
        String reversedWord = stringReverseService.reverseString(input);
        return ResponseEntity.ok(reversedWord);
    }
}

