package com.example.assessment.controllers;

import com.example.assessment.models.WordRequestModel;
import com.example.assessment.services.StringReverseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
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
    public ResponseEntity<String> reverseGet(@RequestParam(name = "word", required = false) String input){
        if (input == null ) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Query param 'word' not found. Check request url again");
        }
        if (input.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Word is empty. Please re check request url");
        }

        String reversedWord = stringReverseService.reverseString(input);
        return ResponseEntity.status(HttpStatus.OK).body(reversedWord);
    }

    //Method 2: Receive word through Request body:
    @PostMapping("/reverse")
    public ResponseEntity<String> reversePost(@Valid @RequestBody WordRequestModel request, BindingResult result){
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went wrong: " + result.getFieldError().getDefaultMessage());
        }
        String input = request.getWord();
        String reversedWord = stringReverseService.reverseString(input);
        return ResponseEntity.ok(reversedWord);
    }
}


@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Invalid request body: " + ex.getMessage());
    }
}