package com.example.assessment.controllers;

import com.example.assessment.services.StringReverseService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;



@WebMvcTest(Transformations.class)
public class TransformationsTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private StringReverseService stringReverseService;

    //Tests for GET revers:
    @Test
    public void reverseGet_ValidInput() throws Exception {

        //Arrange
        String input = "hello";
        String reversed = "olleh";
        //mock the behaviour of reverse string service:
        when(stringReverseService.reverseString(input)).thenReturn(reversed);

        //Act and Assert
        mockMvc.perform(get("/reverse").param("word", input))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.TEXT_PLAIN_VALUE))
                .andExpect(content().string(reversed));

    }

    @Test
    public void reverseGet_EmptyInput() throws Exception {
        // Arrange
        String input = "";

        // Act & Assert
        mockMvc.perform(get("/reverse").param("word", input))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.message").value("Word is empty. Please re check request url"));
    }

    @Test
    public void reverseGet_MissingInput() throws Exception {
        // Act & Assert
        mockMvc.perform(get("/reverse"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.message").exists())
                .andExpect(jsonPath("$.message").value("Query param 'word' not found. Check request url again"));
    }

    //Tests for POST reverse:
    @Test
    public void reversePost_ValidInput() throws Exception {
        // Arrange
        String input = "hello";
        String reversed = "olleh";

        // Mock the behavior of reverse string service:
        when(stringReverseService.reverseString(input)).thenReturn(reversed);

        // Act & Assert
        mockMvc.perform(post("/reverse")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"word\": \"hello\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string(reversed));
    }

    @Test
    public void reversePost_InvalidRequestBody() throws Exception {
        // Act & Assert
        mockMvc.perform(post("/reverse")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"xyz\": \"hello\"}")) // Invalid JSON payload
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.message").exists());
    }


}
