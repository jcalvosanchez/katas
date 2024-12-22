# Duque's Game Kata

## Description of the Problem:

This is a game where 1 player has to guess a four digit number.

- The computer generates a random number that has to be guessed by the player.
  - This number has to have exactly four digits
  - This number cannot have digits repeated. Therefore, the number 1123 is not allowed because it contains the digit "1" twice.
- The player introduces one number with four digits
- The computer then replies with the number of digits kills and wounded
  - A digit is killed if it occupies the same place in the number to guess and in the number that the player introduces.
  - A digit is wounded if is contained in both, the number to guess and the number that the player introduces, but it is not in the same position.
- If the number to guess is 1234, and the player introduces 2134
  - There are 2 digit killed, "3", "4"
  - There are 2 digits wounded, "1", "2"
  - The reply from the computer would be "1 digit killed, 3 wounded"
- The player continues to introduce numbers, until guesses the number.
  - The four digits has been killed.

## Description of the Solution:

Each branch contains a solution to this problem.

- **master**. It contains the description of the kata.
- **kata-duquesGame-java8**. Solution from 2024 using Java8.

### kata-duquesGame-java8

#### How to play the game

Run `PlayDuquesGame`.

#### DuquesNumber

`DuquesNumber` represents a valid number for this Game.

- Contains 4 and only 4 digits
- There are only digits
- There are no repeated digits

#### GuessResult

`GuessResult` represents a possible result out of a player's guess, comparing the number introduced with the Duque's Number that has to be guessed.

- Number of digits killed
- Number of digits wounded
- toString method to display the result to the player

#### GuessResult

`GuessResult` contains the rules and game methods.

- New game is created, setting a final `numberToBeGuessed` by the player.
- `start()` will start the game.
- `guess(String playerGuessAsString)` calculates the result of the player guess against the number that has to be guessed.

## License:
This project is licensed under the MIT License.