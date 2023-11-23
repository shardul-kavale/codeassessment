package com.example.assessment.services;

import org.springframework.stereotype.Service;

@Service
public class StringReverseService {

    public String reverseString(String input) {
        char[] charsArray = input.toCharArray();
        int left = 0;
        int right = charsArray.length - 1;

        while (left < right) {
            char temp = charsArray[left];
            charsArray[left] = charsArray[right];
            charsArray[right] = temp;
            left++;
            right--;
        }

        return new String(charsArray);

        // Alternatively:
        // return new StringBuilder(input).reverse().toString();
    }
}
