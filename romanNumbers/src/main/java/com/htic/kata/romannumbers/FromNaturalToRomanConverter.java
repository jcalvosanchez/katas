package com.htic.kata.romannumbers;

public class FromNaturalToRomanConverter {

	public static final int MAX_NATURAL_NUMBER = 3999;
	public static final String[] ROMANNUMBER_VALUES_SHORTEDASC	= {"I", "V", "X", "L", "C", "D", "M"};
	public static final int[] NATURALNUMBER_VALUES_SHORTEDASC	= {1, 5, 10, 50, 100, 500, 1000};
	//Number of pairs of translations between RomanNumbers and NaturalNumbers
	public static final int PAIRS								= 6;

	public static String convert(int number) {
		if (number <= 0 || number > MAX_NATURAL_NUMBER) {
			throw new IllegalArgumentException("Number must be between 1 and 3999");
		}
		int naturalNumberTemp	= number;
		String result	= "";
		int quotient;

		for (int i=PAIRS; i>-1 ; i--) {
			quotient			= naturalNumberTemp / NATURALNUMBER_VALUES_SHORTEDASC [i];
			naturalNumberTemp	= naturalNumberTemp - ( quotient * NATURALNUMBER_VALUES_SHORTEDASC [i] );

			switch (quotient) {
				case 0:
					break;
				case 1:
					result = result.concat(ROMANNUMBER_VALUES_SHORTEDASC [i]);
					break;
				case 2:
					result = result.concat(ROMANNUMBER_VALUES_SHORTEDASC [i]);
					result = result.concat(ROMANNUMBER_VALUES_SHORTEDASC [i]);
					break;
				case 3:
					result = result.concat(ROMANNUMBER_VALUES_SHORTEDASC [i]);
					result = result.concat(ROMANNUMBER_VALUES_SHORTEDASC [i]);
					result = result.concat(ROMANNUMBER_VALUES_SHORTEDASC [i]);
					break;
				case 4:
					if (!result.endsWith(ROMANNUMBER_VALUES_SHORTEDASC [i+1])) {
						//En este caso debemos diferenciar si es un 4. A�adimos el 4: "[i]" + "[i+1]"
						result = result.concat(ROMANNUMBER_VALUES_SHORTEDASC [i]);
						result = result.concat(ROMANNUMBER_VALUES_SHORTEDASC [i+1]);
					} else {
						//o si es un 5 + 4 (la cadena del resultado ya acababa por 5 y le queremos a�adir 4)
						//Eliminamos el 5 previo
						result = result.substring(0, result.length()-1);
						//Annadimos el 9: "[i]" + "[i+2]"
						result = result.concat(ROMANNUMBER_VALUES_SHORTEDASC [i]);
						result = result.concat(ROMANNUMBER_VALUES_SHORTEDASC [i+2]);
					}
					break;
				default:
					throw new RuntimeException ();
			}

		}

		return result;
	}
}
