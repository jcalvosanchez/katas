package com.htic.kata.romannumbers;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FromRomanToNaturalConverterTest extends TestData {

	@ParameterizedTest
	@MethodSource("romanValidTestData")
	void testConvert_validCases(String roman, int expected) {
		assertEquals(expected, FromRomanToNaturalConverter.convert(roman));
	}

	@ParameterizedTest
	@EnumSource(InvalidRomanCase.class)
	void testConvert_invalidCases(InvalidRomanCase invalidCase) {
		assertThrows(IllegalArgumentException.class,
				() -> FromRomanToNaturalConverter.convert(invalidCase.getValue()));
	}

}
