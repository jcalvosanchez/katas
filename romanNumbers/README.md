# Roman Numerals Kata

This is a coding exercise (kata) designed to practice small algorithms like converting Roman numerals to natural digits and vice versa.

## Description of the Problem:

- The Romans wrote numbers using letters : I, V, X, L, C, D, M. (notice these letters have lots of straight lines and are hence easy to hack into stone tablets)
- We have adopted arabic digits (0, 1, 5, 10, 50, 100, 500, 1000) and used base 10.

### Features:
- **Roman numerals to Natural Base 10 Conversion**: Convert Roman numerals (e.g., "XIV") into natural numbers (e.g., 14).
- **Natural Base 10 to Roman numerals Conversion**: Convert natural numbers (e.g., 14) into Roman numerals (e.g., "XIV").

### External help
- https://www.romannumerals.org/converter
- https://codingdojo.org/kata/RomanNumerals/

## Description of the Solution:

Each branch contains a solution to this problem.

- **Master**. At the moment, it contains **kata-romanNumbers-java8SpringTest2.5**.
- **kata-romanNumbers-java8SpringTest2.5**. Solution from 2016 using Java8 with a Spring Test 2.5.
- **kata-romanNumbers-java23jUnit5**. Maven solution from 2024 using Java23 with jUnit5.

### kata-romanNumbers-java23jUnit5:

![Static Badge](https://img.shields.io/badge/date-2024-orange)
![Static Badge](https://img.shields.io/badge/java-23-blue)
![Static Badge](https://img.shields.io/badge/junit-5-blue)

Solution from November 2024 using Java 23 and jUnit 5.

### Test:

To make sure that both convertions, from Roman to Natural, and from Natural to Roman, are bidirectional, the same test data is used.

```bash
mvn clean test
```

## License:
This project is licensed under the MIT License.