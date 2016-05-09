package com.htic.idi.codekata.romannumbers.business;

import org.junit.Test;

import com.htic.idi.codekata.romannumbers.model.NaturalNumber;
import com.htic.idi.codekata.romannumbers.model.RomanNumber;

public class RomanNumbersBOTest extends RomanNumbersBOTestBase {

	@Test
	public void romanNumber2NaturalNumberTest () {
System.out.println("romanNumber2NaturalNumberTest - INICIO");
		initData();
		boolean resultado = true;

		while (resultado == true && romanNumberIterator.hasNext()) { 
			RomanNumber romanNumber			= romanNumberIterator.next();
			NaturalNumber naturalNumber		= naturalNumberIterator.next();
			NaturalNumber naturalNumberTest	= romanNumberBO.romanNumber2NaturalNumber(romanNumber);

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

	@Test
	public void naturalNumber2RomanNumberTest () {
		initData();
		boolean resultado = true;

		while (resultado == true && romanNumberIterator.hasNext()) { 
			RomanNumber romanNumber		= romanNumberIterator.next();
			NaturalNumber naturalNumber	= naturalNumberIterator.next();
			RomanNumber romanNumberTest	= romanNumberBO.naturalNumber2RomanNumber(naturalNumber);

			resultado =  romanNumberTest.getValue().equals(romanNumber.getValue());
System.out.println("\nnaturalNumber=[" + naturalNumber + "]");
System.out.println("romanNumber=[" + romanNumber + "]");
System.out.println("romanNumberTest=[" + romanNumberTest + "]");
System.out.println("resultado=[" + resultado + "]");
		}
System.out.println("\nResultado del m�todo=[" + resultado + "]");
System.out.println("romanNumber2NaturalNumberTest - FIN");
		assert (resultado);
	}
}