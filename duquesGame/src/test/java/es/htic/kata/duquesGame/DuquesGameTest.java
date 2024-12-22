package es.htic.kata.duquesGame;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DuquesGameTest {

    @Test
    void testValidDuquesNumberGuess() {
        DuquesGame duquesGame = new DuquesGame(new DuquesNumber("1234"));
        Assertions.assertEquals("0 digit(s) killed, 0 wounded", duquesGame.guess("5678").toString());
        Assertions.assertEquals("2 digit(s) killed, 2 wounded", duquesGame.guess("2134").toString());
        Assertions.assertEquals("0 digit(s) killed, 4 wounded", duquesGame.guess("4321").toString());
        Assertions.assertEquals("4 digit(s) killed, 0 wounded", duquesGame.guess("1234").toString());
    }

    @Test
    void testInvalidDuquesNumberGuessThrowsException() {
        DuquesGame duquesGame = new DuquesGame(new DuquesNumber("1234"));
        assertThrows(IllegalArgumentException.class, () -> duquesGame.guess("123"));
        assertThrows(IllegalArgumentException.class, () -> duquesGame.guess("1123"));
        assertThrows(IllegalArgumentException.class, () -> duquesGame.guess("1.23"));
        assertThrows(IllegalArgumentException.class, () -> duquesGame.guess("abcd"));
        assertThrows(IllegalArgumentException.class, () -> duquesGame.guess("12345"));
    }

}
