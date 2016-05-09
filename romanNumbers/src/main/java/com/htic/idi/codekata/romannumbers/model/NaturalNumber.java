package com.htic.idi.codekata.romannumbers.model;

public class NaturalNumber {

	private int value;


	public NaturalNumber (int intValue) {
		this.value = intValue;
	}


	public String toString () {
		return "" + getValue();
	}


	//Getters && Setters
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
}