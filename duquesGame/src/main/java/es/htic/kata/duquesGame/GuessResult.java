package es.htic.kata.duquesGame;

public class GuessResult {
    private final int kills;
    private final int wounds;

    public GuessResult(int kills, int wounds) {
        this.kills = kills;
        this.wounds = wounds;
    }

    public boolean playerWon() {
        return getKills() == 4 && getWounds() == 0;
    }

    public int getKills() {
        return kills;
    }

    public int getWounds() {
        return wounds;
    }

    @Override
    public String toString() {
        return kills + " digit(s) killed, " + wounds + " wounded";
    }
}
