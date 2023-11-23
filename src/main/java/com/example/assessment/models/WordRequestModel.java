package com.example.assessment.models;

import jakarta.validation.constraints.NotBlank;

public class WordRequestModel {
    @NotBlank(message = "Word must not be null or blank. Please ensure you give a valid word")
    private String word;

    // Getters and setters
    public String getWord() {
        return word;
    }
    public void setWord(String word) {
        this.word = word;
    }

}
