package es.htic.kata.duquesGame;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DuquesNumberTest {

    @Test
    void testValidNumber() {
        assertDoesNotThrow(() -> new DuquesNumber("0123"));
        assertDoesNotThrow(() -> new DuquesNumber("7890"));
    }

    @Test
    void testInvalidNumberThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> new DuquesNumber("123")); //less than four digits
        assertThrows(IllegalArgumentException.class, () -> new DuquesNumber("1123")); //duplicated digit
        assertThrows(IllegalArgumentException.class, () -> new DuquesNumber("12a4")); //contains non digits
        assertThrows(IllegalArgumentException.class, () -> new DuquesNumber("12.4")); //contains non digits
        assertThrows(IllegalArgumentException.class, () -> new DuquesNumber("12345")); // more than four digits
    }

    @RepeatedTest(50) // Run multiple times to ensure randomness and validity
    void testNewDuquesNumberIsValid() {
        assertDoesNotThrow(() -> new DuquesNumber());
    }

}
