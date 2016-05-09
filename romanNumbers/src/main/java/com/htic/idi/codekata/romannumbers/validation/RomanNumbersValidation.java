package com.htic.idi.codekata.romannumbers.validation;

import com.htic.idi.codekata.romannumbers.model.NaturalNumber;
import com.htic.idi.codekata.romannumbers.model.RomanNumber;

public interface RomanNumbersValidation {

	public boolean isRomanNumber (RomanNumber romanNumber);

	public boolean isNaturalNumber (NaturalNumber naturalNumber);

}