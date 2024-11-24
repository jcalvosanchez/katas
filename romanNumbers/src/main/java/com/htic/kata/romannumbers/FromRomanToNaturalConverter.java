package com.htic.kata.romannumbers;

import java.util.Map;

/**
 * Java 9 - Map.of as a concise and immutable way to create small maps
 */
public class FromRomanToNaturalConverter {

    private static final Map<Character, Integer> ROMAN_TO_INT = Map.of(
            'I', 1, 'V', 5, 'X', 10, 'L', 50, 'C', 100, 'D', 500, 'M', 1000
    );

    public static int convert(String roman) {
        validateRomanNumeral(roman);

        int result = 0;
        int prevValue = 0;

        // Loop through the Roman numeral string from right to left
        for (int i = roman.length() - 1; i >= 0; i--) {
            char currentChar = roman.charAt(i);
            int currentValue = ROMAN_TO_INT.getOrDefault(currentChar, -1);

            if (currentValue == -1) {
                throw new IllegalArgumentException("Invalid Roman numeral: " + currentChar);
            }

            // If the current value is smaller than the previous value, subtract it; otherwise, add it
            result += currentValue < prevValue ? -currentValue : currentValue;

            prevValue = currentValue;
        }

        return result;
    }

    private static void validateRomanNumeral(String roman) {
        if (roman == null || roman.isEmpty()) {
            throw new IllegalArgumentException("Invalid input");
        }
        hasOnlyValidRomanCharacters(roman);
        hasOnlyValidSequences(roman);
    }

    private static void hasOnlyValidSequences(String roman) {
        String[] invalidSequences = {"IIII", "VV", "XXXX", "IIII", "LL", "CCCC", "MMMM"};
        for (String seq : invalidSequences) {
            if (roman.contains(seq)) {
                throw new IllegalArgumentException("Invalid Roman sequence: " + seq);
            }
        }
    }

    private static void hasOnlyValidRomanCharacters(String roman) {
        for (char ch : roman.toCharArray()) {
            if (!ROMAN_TO_INT.containsKey(ch)) {
                throw new IllegalArgumentException("Invalid Roman numeral: " + ch);
            }
        }
    }

}
