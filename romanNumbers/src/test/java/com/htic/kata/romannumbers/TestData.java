package com.htic.kata.romannumbers;

import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

public class TestData {

	static Stream<Arguments> romanValidTestData() {
		return Stream.of(
				Arguments.of("I", 1),
				Arguments.of("V", 5),
				Arguments.of("X", 10),
				Arguments.of("L", 50),
				Arguments.of("C", 100),
				Arguments.of("D", 500),
				Arguments.of("M", 1000),
				Arguments.of("II", 2),
				Arguments.of("III", 3),
				Arguments.of("IV", 4),
				Arguments.of("VI", 6),
				Arguments.of("VII", 7),
				Arguments.of("VIII", 8),
				Arguments.of("IX", 9),
				Arguments.of("XI", 11),
				Arguments.of("XIV", 14),
				Arguments.of("XVII", 17),
				Arguments.of("XIX", 19),
				Arguments.of("XXIII", 23),
				Arguments.of("XLIX", 49),
				Arguments.of("LVIII", 58),
				Arguments.of("XCV", 95),
				Arguments.of("XCIX", 99),
				Arguments.of("CDXCIX", 499),
				Arguments.of("CMXCIX", 999),
				Arguments.of("MCDXLIX", 1449),
				Arguments.of("MCMXCIV", 1994)
		);
	}

	public enum InvalidNaturalCase {
		ZERO(0),
		NEGATIVE_ROMAN(-1),
		INVALID_CHARACTER(4000);

		private final int value;

		InvalidNaturalCase(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}
	}

	public enum InvalidRomanCase {
		NULL(null),
		EMPTY(""),
		ZERO("0"),
		NEGATIVE_ROMAN("-I"),
		INVALID_CHARACTER("A"),
		LOWERCASE("i"),
		NOT_ROMAN_NUMBER("IIII"),
		MAX_ROMAN_NUMBER("MMMM");

		private final String value;

		InvalidRomanCase(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}
}
