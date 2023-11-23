package com.example.assessment.controllers;

import com.example.assessment.models.ErrorResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Displays {

    public String myMessage;

    //Inject environment variable into constructor:
    public Displays(@Value("${MY_MESSAGE:}") String myMessage) {
        this.myMessage = myMessage;
    }

    @GetMapping("/display")
    public ResponseEntity<?> displayMessage(){

        if (myMessage != null && !myMessage.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(myMessage);
        } else {
            ErrorResponse error = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Sorry MY_MESSAGE env variable hasn't been set yet. Try again later");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(error);
        }
    }
}
