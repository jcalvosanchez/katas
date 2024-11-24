package com.htic.kata.romannumbers;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FromNaturalToRomanConverterTest extends TestData {

	@ParameterizedTest
	@MethodSource("romanValidTestData")
	void fromNaturalToRoman_validCases(String expected, int natural) {
		assertEquals(expected, FromNaturalToRomanConverter.convert(natural));
	}

	@ParameterizedTest
	@EnumSource(InvalidNaturalCase.class)
	void testConvert_invalidCases(InvalidNaturalCase invalidCase) {
		assertThrows(IllegalArgumentException.class,
				() -> FromNaturalToRomanConverter.convert(invalidCase.getValue()));
	}

}
