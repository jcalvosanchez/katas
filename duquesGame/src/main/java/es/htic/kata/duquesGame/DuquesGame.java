package es.htic.kata.duquesGame;

import java.util.*;

public class DuquesGame {

    private final DuquesNumber numberToGuess;
    private int plays;

    public DuquesGame () {
        this(new DuquesNumber());
    }

    public DuquesGame(DuquesNumber numberToGuess) {
        this.numberToGuess = numberToGuess;
        this.plays = 0;
    }

    protected GuessResult guess(String playerGuessAsString) {
        DuquesNumber playerGuess = new DuquesNumber(playerGuessAsString);

        plays++;
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

    public void start() {
        Scanner scanner = new Scanner(System.in);
        boolean guessedCorrectly = false;

        printWelcome();
        while (!guessedCorrectly) {
            System.out.print("Enter your 4-digit guess: ");
            String playerGuess = scanner.nextLine();

            try {
                GuessResult result = guess(playerGuess);
                System.out.println(result);

                if (result.playerWon()) {
                    guessedCorrectly = true;
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid input! " + e.getMessage());
            }
        }

        printWinMessage();
        scanner.close();
    }

    private void printWinMessage() {
        System.out.printf("Congratulations! You guessed the number in %d plays!%n", plays);
    }

    private void printWelcome() {
        System.out.println("Welcome to the Guessing Game!");
        System.out.println("Try to guess the 4-digit number. No digits are repeated.");
        System.out.println("For each guess, you'll get feedback:");
        System.out.println("- 'Killed' means a digit is correct and in the right position.");
        System.out.println("- 'Wounded' means a digit is correct but in the wrong position.");
        System.out.println("The game ends when you guess the number. Good luck!");
    }

}
