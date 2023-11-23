package com.example.assessment.controllers;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.env.Environment;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(Displays.class)
public class DisplaysTests {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private Environment environment;

    @Test
    public void displayMessage_WithValidMessage() throws Exception {
        //Arrange
        String message = "Hello how are you?";
        when(environment.getProperty("MY_MESSAGE")).thenReturn(message);

        // Act & Assert
        mockMvc.perform(get("/display"))
                .andExpect(status().isOk())
                .andExpect(content().string(message));
    }

    @Test
    public void displayMessage_WithEmptyMessage() throws Exception {
        // Arrange
        when(environment.getProperty("MY_MESSAGE")).thenReturn("");

        // Act & Assert
        mockMvc.perform(get("/display"))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.status").value(500))
                .andExpect(jsonPath("$.message").exists());
    }
}
