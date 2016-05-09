package com.htic.idi.codekata.romannumbers.business;

import java.util.ArrayList;
import java.util.ListIterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.htic.idi.codekata.romannumbers.business.RomanNumberBO;
import com.htic.idi.codekata.romannumbers.core.constants.RomanNumbersConstants;
import com.htic.idi.codekata.romannumbers.model.NaturalNumber;
import com.htic.idi.codekata.romannumbers.model.RomanNumber;

@ContextConfiguration(locations={"classpath:spring/applicationContext_root.xml", "classpath:spring/applicationContext_test.xml"})
public class RomanNumbersMain {

	//TODO: log4j?
	//TODO: Extraer a un proyecto stand-alone

	@Autowired protected static RomanNumberBO romanNumbersBO;


	protected static ArrayList<RomanNumber> romanNumberList;
	protected static ArrayList<NaturalNumber> naturalNumberList;
	protected static ListIterator<RomanNumber> romanNumberIterator;
	protected static ListIterator<NaturalNumber> naturalNumberIterator;


	protected static void initData () {

		romanNumberList		= new ArrayList<RomanNumber> ();
		naturalNumberList	= new ArrayList<NaturalNumber> ();

		for (int i=0; i<RomanNumbersConstants.PAIRS; i++) {
			romanNumberList.add(new RomanNumber (RomanNumbersConstants.ROMANNUMBER_VALUES_SHORTEDASC [i]));
			naturalNumberList.add(new NaturalNumber(RomanNumbersConstants.NATURALNUMBER_VALUES_SHORTEDASC [i]));
		}

		romanNumberList.add(new RomanNumber ("II"));
		naturalNumberList.add(new NaturalNumber(2));

		romanNumberList.add(new RomanNumber ("III"));
		naturalNumberList.add(new NaturalNumber(3));

		romanNumberList.add(new RomanNumber ("IV"));
		naturalNumberList.add(new NaturalNumber(4));

		romanNumberList.add(new RomanNumber ("VI"));
		naturalNumberList.add(new NaturalNumber(6));

		romanNumberList.add(new RomanNumber ("VII"));
		naturalNumberList.add(new NaturalNumber(7));

		romanNumberList.add(new RomanNumber ("VIII"));
		naturalNumberList.add(new NaturalNumber(8));

		romanNumberList.add(new RomanNumber ("IX"));
		naturalNumberList.add(new NaturalNumber(9));

		romanNumberList.add(new RomanNumber ("CCLVI"));
		naturalNumberList.add(new NaturalNumber(256));

		romanNumberList.add(new RomanNumber ("DCCCXXXVII"));
		naturalNumberList.add(new NaturalNumber(837));

		romanNumberList.add(new RomanNumber ("CMXLIX"));
		naturalNumberList.add(new NaturalNumber(949));

		romanNumberList.add(new RomanNumber ("CMXCIX"));
		naturalNumberList.add(new NaturalNumber(999));

		romanNumberList.add(new RomanNumber ("MDXLI"));
		naturalNumberList.add(new NaturalNumber(1541));

		romanNumberList.add(new RomanNumber ("MMMCMXCIX"));
		naturalNumberList.add(new NaturalNumber(3999));

		romanNumberIterator		= romanNumberList.listIterator();
		naturalNumberIterator	= naturalNumberList.listIterator();
	}

	private static RomanNumber naturalNumber2RomanNumber(NaturalNumber naturalNumber) {
		int naturalNumberTemp	= naturalNumber.getValue();
		RomanNumber resultado	= new RomanNumber ("");
		int quotient;

		for (int i=RomanNumbersConstants.PAIRS; i>-1 ; i--) {

			quotient			= naturalNumberTemp / RomanNumbersConstants.NATURALNUMBER_VALUES_SHORTEDASC [i];
			naturalNumberTemp	= naturalNumberTemp - ( quotient * RomanNumbersConstants.NATURALNUMBER_VALUES_SHORTEDASC [i] );

			switch (quotient) {
			case 0:
				break;
			case 1:
				resultado.concat(RomanNumbersConstants.ROMANNUMBER_VALUES_SHORTEDASC [i]);
				break;
			case 2:
				resultado.concat(RomanNumbersConstants.ROMANNUMBER_VALUES_SHORTEDASC [i]);
				resultado.concat(RomanNumbersConstants.ROMANNUMBER_VALUES_SHORTEDASC [i]);
				break;
			case 3:
				resultado.concat(RomanNumbersConstants.ROMANNUMBER_VALUES_SHORTEDASC [i]);
				resultado.concat(RomanNumbersConstants.ROMANNUMBER_VALUES_SHORTEDASC [i]);
				resultado.concat(RomanNumbersConstants.ROMANNUMBER_VALUES_SHORTEDASC [i]);
				break;
			case 4:
				if (!resultado.getValue().endsWith(RomanNumbersConstants.ROMANNUMBER_VALUES_SHORTEDASC [i+1])) {
					//En este caso debemos diferenciar si es un 4. A�adimos el 4: "[i]" + "[i+1]"
					resultado.concat(RomanNumbersConstants.ROMANNUMBER_VALUES_SHORTEDASC [i]);
					resultado.concat(RomanNumbersConstants.ROMANNUMBER_VALUES_SHORTEDASC [i+1]);
				} else {
					//o si es un 5 + 4 (la cadena del resultado ya acababa por 5 y le queremos a�adir 4)
					//Eliminamos el 5 previo
					resultado.setValue(resultado.getValue().substring(0, resultado.getValue().length()-1));
					//A�adimos el 9: "[i]" + "[i+2]"
					resultado.concat(RomanNumbersConstants.ROMANNUMBER_VALUES_SHORTEDASC [i]);
					resultado.concat(RomanNumbersConstants.ROMANNUMBER_VALUES_SHORTEDASC [i+2]);
				}
				break;
			default:
				throw new RuntimeException ();
			}

		}

		return resultado;
	}

	public static NaturalNumber romanNumber2NaturalNumber(RomanNumber romanNumber) {
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

	private static int obtainRomanCipherIndex (String romanCipher) {
		int result = -1;
		int index	= 0;

		while (result < 0 && index <= RomanNumbersConstants.PAIRS) {
			if (romanCipher.equals(RomanNumbersConstants.ROMANNUMBER_VALUES_SHORTEDASC [index])) {
				result = index;;
			}
			index++;
		}

		return result;
	}

	public static void main(String args[]) {
System.out.println("romanNumber2NaturalNumberTest - INICIO");
		initData();
		boolean resultado = true;

		while (resultado == true && romanNumberIterator.hasNext()) { 
			RomanNumber romanNumber			= romanNumberIterator.next();
			NaturalNumber naturalNumber		= naturalNumberIterator.next();
			NaturalNumber naturalNumberTest	= romanNumber2NaturalNumber(romanNumber);

			resultado = naturalNumberTest.getValue() == naturalNumber.getValue();
System.out.println("\nnaturalNumber=[" + naturalNumber + "]");
System.out.println("romanNumber=[" + romanNumber + "]");
System.out.println("naturalNumberTest=[" + naturalNumberTest + "]");
System.out.println("resultado=[" + resultado + "]");
		}
System.out.println("\nResultado del m�todo=[" + resultado + "]");
System.out.println("romanNumber2NaturalNumberTest - FIN");
		assert (resultado);
	}


	//Getters && Setters
	public static RomanNumberBO getRomanNumbersBO() {
		return romanNumbersBO;
	}
	public static void setRomanNumbersBO(RomanNumberBO romanNumbersBOARG) {
		romanNumbersBO = romanNumbersBOARG;
	}
}