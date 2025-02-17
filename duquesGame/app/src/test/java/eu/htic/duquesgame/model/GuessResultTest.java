package eu.htic.duquesgame.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class GuessResultTest {

    private final int kills;
    private final int wounds;
    private final boolean expected;

    public GuessResultTest(int kills, int wounds, boolean expected) {
        this.kills = kills;
        this.wounds = wounds;
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {0, 0, false},
                {0, 1, false},
                {0, 2, false},
                {0, 3, false},
                {0, 4, false},
                {1, 0, false},
                {1, 1, false},
                {1, 2, false},
                {1, 3, false},
                {2, 0, false},
                {2, 1, false},
                {2, 2, false},
                {3, 0, false},
                {3, 1, false},
                {4, 0, true}
        });
    }

    @Test
    public void testPlayerWon() {
        GuessResult result = new GuessResult(kills, wounds);
        assertEquals("Player won check failed for " + kills + " kills and " + wounds + " wounds.",
                expected, result.playerWon());
    }
}
