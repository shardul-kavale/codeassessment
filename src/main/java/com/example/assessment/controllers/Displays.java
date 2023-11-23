package com.example.assessment.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Displays {

    private final String myMessage;

    //Inject environment variable into constructor:
    public Displays(@Value("${MY_MESSAGE:}") String myMessage) {
        this.myMessage = myMessage;
    }

    @GetMapping("/display")
    public ResponseEntity<String> displayMessage(){

        if (myMessage != null && !myMessage.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(myMessage);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Sorry MY_MESSAGE env variable hasn't been set yet. Try again later");
        }
    }
}
