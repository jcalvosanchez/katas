package com.htic.idi.codekata.romannumbers.business.impl;

import com.htic.idi.codekata.romannumbers.business.RomanNumberBO;
import com.htic.idi.codekata.romannumbers.core.constants.RomanNumbersConstants;
import com.htic.idi.codekata.romannumbers.model.NaturalNumber;
import com.htic.idi.codekata.romannumbers.model.RomanNumber;

public class RomanNumberBOImpl implements RomanNumberBO {

	public RomanNumber naturalNumber2RomanNumber(NaturalNumber naturalNumber) {
		int naturalNumberTemp	= naturalNumber.getValue();
		RomanNumber result	= new RomanNumber ("");
		int quotient;

		for (int i=RomanNumbersConstants.PAIRS; i>-1 ; i--) {

			quotient			= naturalNumberTemp / RomanNumbersConstants.NATURALNUMBER_VALUES_SHORTEDASC [i];
			naturalNumberTemp	= naturalNumberTemp - ( quotient * RomanNumbersConstants.NATURALNUMBER_VALUES_SHORTEDASC [i] );

			switch (quotient) {
			case 0:
				break;
			case 1:
				result.concat(RomanNumbersConstants.ROMANNUMBER_VALUES_SHORTEDASC [i]);
				break;
			case 2:
				result.concat(RomanNumbersConstants.ROMANNUMBER_VALUES_SHORTEDASC [i]);
				result.concat(RomanNumbersConstants.ROMANNUMBER_VALUES_SHORTEDASC [i]);
				break;
			case 3:
				result.concat(RomanNumbersConstants.ROMANNUMBER_VALUES_SHORTEDASC [i]);
				result.concat(RomanNumbersConstants.ROMANNUMBER_VALUES_SHORTEDASC [i]);
				result.concat(RomanNumbersConstants.ROMANNUMBER_VALUES_SHORTEDASC [i]);
				break;
			case 4:
				if (!result.getValue().endsWith(RomanNumbersConstants.ROMANNUMBER_VALUES_SHORTEDASC [i+1])) {
					//En este caso debemos diferenciar si es un 4. A�adimos el 4: "[i]" + "[i+1]"
					result.concat(RomanNumbersConstants.ROMANNUMBER_VALUES_SHORTEDASC [i]);
					result.concat(RomanNumbersConstants.ROMANNUMBER_VALUES_SHORTEDASC [i+1]);
				} else {
					//o si es un 5 + 4 (la cadena del resultado ya acababa por 5 y le queremos a�adir 4)
					//Eliminamos el 5 previo
					result.setValue(result.getValue().substring(0, result.getValue().length()-1));
					//A�adimos el 9: "[i]" + "[i+2]"
					result.concat(RomanNumbersConstants.ROMANNUMBER_VALUES_SHORTEDASC [i]);
					result.concat(RomanNumbersConstants.ROMANNUMBER_VALUES_SHORTEDASC [i+2]);
				}
				break;
			default:
				throw new RuntimeException ();
			}

		}

		return result;
	}

	public NaturalNumber romanNumber2NaturalNumber(RomanNumber romanNumber) {
		String chain	= romanNumber.getValue();
		int result		= 0;
		int i			= 0;
		int actualIndex;
		int nextIndex;

		while (i<chain.length()) {
			actualIndex	= obtainRomanCipherIndex(chain.substring(i, i+1));
			if (i+1 < chain.length()) {
				nextIndex	= obtainRomanCipherIndex(chain.substring(i+1, i+2));
			} else {
				//No hay m�s cifras que procesar en la siguiente iteraci�n
				nextIndex	= 0;
			}
			
			if (nextIndex > actualIndex) {
				//Se trata de un "4" o un "9", por lo cual procesamos el bloque de dos �ndices
				result	+= RomanNumbersConstants.NATURALNUMBER_VALUES_SHORTEDASC [nextIndex]
						- RomanNumbersConstants.NATURALNUMBER_VALUES_SHORTEDASC [actualIndex];
				//Aumentamos el contador para no procesar la siguiente cifra
				i++;
			} else {
				result	+= RomanNumbersConstants.NATURALNUMBER_VALUES_SHORTEDASC [actualIndex];
			}

			i++;
		}

		return new NaturalNumber(result);
	}

	private int obtainRomanCipherIndex (String romanCipher) {
		int result = -1;
		int index	= 0;

		while (result < 0 && index <= RomanNumbersConstants.PAIRS) {
			if (romanCipher.equals(RomanNumbersConstants.ROMANNUMBER_VALUES_SHORTEDASC [index])) {
				result = index;
			}
			index++;
		}

		return result;
	}
}