package es.htic.kata.duquesGame;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class DuquesNumber {

    private final String numberAsString;

    public DuquesNumber() {
        this.numberAsString = generateRandomValidDuquesNumber();
    }
    public DuquesNumber(String numberAsString) {
        if (!isValidNumber(numberAsString)) {
            throw new IllegalArgumentException("Number must have 4 unique digits.");
        }
        this.numberAsString = numberAsString;
    }

    private String generateRandomValidDuquesNumber() {
        Random random = new Random();
        Set<Integer> uniqueDigits = new HashSet<>();
        StringBuilder number = new StringBuilder();

        while (uniqueDigits.size() < 4) {
            int digit = random.nextInt(10); // Random digit between 0 and 9
            if (uniqueDigits.add(digit)) {
                number.append(digit);
            }
        }

        if (!isValidNumber(number.toString())) {
            throw new IllegalArgumentException("Number must have 4 unique digits.");
        }
        return number.toString();
    }

    private boolean isValidNumber(String number) {
        if (number.length() != 4) {
            return false;
        }
        Set<Character> digits = new HashSet<>();
        for (char c : number.toCharArray()) {
            if (!Character.isDigit(c) || !digits.add(c)) {
                return false;
            }
        }
        return true;
    }

    public String getNumberAsString() {
        return numberAsString;
    }
}
