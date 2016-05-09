package com.htic.idi.codekata.romannumbers.business;

import java.util.ArrayList;
import java.util.ListIterator;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.htic.idi.codekata.romannumbers.BaseTest;
import com.htic.idi.codekata.romannumbers.business.RomanNumberBO;
import com.htic.idi.codekata.romannumbers.core.constants.RomanNumbersConstants;
import com.htic.idi.codekata.romannumbers.model.NaturalNumber;
import com.htic.idi.codekata.romannumbers.model.RomanNumber;

public class RomanNumbersBOTestBase extends BaseTest {

	@Autowired protected RomanNumberBO romanNumberBO;

	protected ArrayList<RomanNumber> romanNumberList;
	protected ArrayList<NaturalNumber> naturalNumberList;
	protected ListIterator<RomanNumber> romanNumberIterator;
	protected ListIterator<NaturalNumber> naturalNumberIterator;


	@Test
	public void romanNumber2NaturalNumberTest () {
		Assert.assertNotNull(romanNumberBO);
	}


	protected void initData () {

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

		romanNumberList.add(new RomanNumber ("MI"));
		naturalNumberList.add(new NaturalNumber(1001));

		romanNumberList.add(new RomanNumber ("MDXLI"));
		naturalNumberList.add(new NaturalNumber(1541));

		romanNumberList.add(new RomanNumber ("MMMCMXCIX"));
		naturalNumberList.add(new NaturalNumber(3999));

		romanNumberIterator		= romanNumberList.listIterator();
		naturalNumberIterator	= naturalNumberList.listIterator();
	}


	//Getters && Setters
	public RomanNumberBO getRomanNumberBO() {
		return romanNumberBO;
	}
	public void setRomanNumberBO(RomanNumberBO romanNumberBO) {
		this.romanNumberBO = romanNumberBO;
	}
}