package com.htic.idi.codekata.romannumbers.model;

public class RomanNumber {

	private String value;


	public RomanNumber (String initValue) {
		this.value = initValue;
	}


	public void concat (String concatValue) {
		this.setValue(this.getValue().concat(concatValue));
	}

	
	public String toString() {
		return this.getValue();
	}


	//Getters && Setters
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}