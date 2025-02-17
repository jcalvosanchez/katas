package eu.htic.duquesgame.model;

import java.util.*;

public class DuquesGame {

    private final DuquesNumber numberToGuess;
    private List<DuquesNumber> numbersTried;

    public DuquesGame () {
        this(new DuquesNumber());
    }

    public DuquesGame(DuquesNumber numberToGuess) {
        this.numberToGuess = numberToGuess;
        this.numbersTried = new ArrayList<>();
    }

    public GuessResult guess(String playerGuessAsString) {
        DuquesNumber playerGuess = new DuquesNumber(playerGuessAsString);

        numbersTried.add(playerGuess);
        int kills = 0;
        int wounds = 0;

        for (int i = 0; i < 4; i++) {
            char guessDigit = playerGuess.getNumberAsString().charAt(i);
            if (guessDigit == numberToGuess.getNumberAsString().charAt(i)) {
                kills++;
            } else if (numberToGuess.getNumberAsString().contains(String.valueOf(guessDigit))) {
                wounds++;
            }
        }

        return new GuessResult(kills, wounds);
    }

    public List<DuquesNumber> getNumbersTried() {
        return numbersTried;
    }
}