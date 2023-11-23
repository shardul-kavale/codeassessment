package com.example.assessment.services;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class StringReverseServiceTests {


    private final StringReverseService stringReverseService = new StringReverseService();

    @Test
    public void reverseString_ValidInput() {
        // Arrange
        String input = "hello";
        String expected = "olleh";

        // Act
        String result = stringReverseService.reverseString(input);

        // Assert
        assertEquals(expected, result);
    }

    @Test
    public void reverseString_EmptyInput() {
        // Arrange
        String input = "";
        String expected = "";

        // Act
        String result = stringReverseService.reverseString(input);

        // Assert
        assertEquals(expected, result);
    }

    @Test
    public void reverseString_NullInput_ReturnsNull() {
        // Arrange
        String input = null;

        // Act
        String result = stringReverseService.reverseString(input);

        // Assert
        assertEquals(null, result);
    }

}