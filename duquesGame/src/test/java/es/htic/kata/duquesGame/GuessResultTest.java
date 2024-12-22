package es.htic.kata.duquesGame;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class GuessResultTest {

    @ParameterizedTest
    @CsvSource({
            "0, 0, false",
            "0, 1, false",
            "0, 2, false",
            "0, 3, false",
            "0, 4, false",
            "1, 0, false",
            "1, 1, false",
            "1, 2, false",
            "1, 3, false",
            "2, 0, false",
            "2, 1, false",
            "2, 2, false",
            "3, 0, false",
            "3, 1, false",
            "4, 0, true"
    })
    void testPlayerWon(int kills, int wounds, boolean expected) {
        GuessResult result = new GuessResult(kills, wounds);
        assertEquals(expected, result.playerWon(), "Player won check failed for " + kills + " kills and " + wounds + " wounds.");
    }
}