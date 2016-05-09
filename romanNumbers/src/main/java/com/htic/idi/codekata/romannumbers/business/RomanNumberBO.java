package com.htic.idi.codekata.romannumbers.business;

import com.htic.idi.codekata.romannumbers.model.NaturalNumber;
import com.htic.idi.codekata.romannumbers.model.RomanNumber;

public interface RomanNumberBO {

	public RomanNumber naturalNumber2RomanNumber (NaturalNumber naturalNumber);

	public NaturalNumber romanNumber2NaturalNumber (RomanNumber romanNumber);

}