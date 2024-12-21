# Duque's Game Kata

## Description of the Problem:

This is a game where 1 player has to guess a four digit number.

- This number cannot have digits repeated. Therefore, the number 1123 is not allowed because it contains the digit "1" twice.
- The player introduces one number with four digits
- The computer then replies with the number of digits kills and wounded
  - A digit is killed if it occupies the same place in the number to guess and in the number that the player introduces.
  - A digit is wounded if is contained in both, the number to guess and the number that the player introduces, but it is not in the same position.
- If the number to guess is 1234, and the player introduces 2134
  - There are 1 digit killed, "3"
  - There are 3 digits wounded, "1", "2", "4"
  - The reply from the computer would be "1 digit killed, 3 wounded"
- The game ends when the player introduces a number that is the same than the number to guess.
  - The four digits has been killed.

## Description of the Solution:

Each branch contains a solution to this problem.

- **master**. It contains the description of the kata.
- **kata-duquesGame-java8**. Solution from 2024 using Java8.

## License:
This project is licensed under the MIT License.