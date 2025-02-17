package eu.htic.duquesgame.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class DuquesGameTest {

    @Test
    public void testValidDuquesNumberGuess() {
        DuquesGame duquesGame = new DuquesGame(new DuquesNumber("1234"));
        assertEquals("0 digit(s) killed, 0 wounded", duquesGame.guess("5678").toString());
        assertEquals("2 digit(s) killed, 2 wounded", duquesGame.guess("2134").toString());
        assertEquals("0 digit(s) killed, 4 wounded", duquesGame.guess("4321").toString());
        assertEquals("4 digit(s) killed, 0 wounded", duquesGame.guess("1234").toString());
    }

    @Test
    public void testInvalidDuquesNumberGuessThrowsException() {
        DuquesGame duquesGame = new DuquesGame(new DuquesNumber("1234"));
        assertThrows(IllegalArgumentException.class, () -> duquesGame.guess("123"));
        assertThrows(IllegalArgumentException.class, () -> duquesGame.guess("1123"));
        assertThrows(IllegalArgumentException.class, () -> duquesGame.guess("1.23"));
        assertThrows(IllegalArgumentException.class, () -> duquesGame.guess("abcd"));
        assertThrows(IllegalArgumentException.class, () -> duquesGame.guess("12345"));
    }

}