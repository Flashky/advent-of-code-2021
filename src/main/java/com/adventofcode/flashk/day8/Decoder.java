package com.adventofcode.flashk.day8;

import lombok.Getter;

@Getter
public class Decoder {

	// Codec is the input signal, the words at the left of "|".
	// Encoded number is the four digit output value at the right of "|".
	// Each encoded number can only be translated by its codec as values change on every line.
	
	private Codec codec;
	private EncodedNumber encodedNumber;
	
	public Decoder(String input) {
		
		String[] splittedInput = input.split("\\|");

		this.codec = new Codec(splittedInput[0]);
		this.encodedNumber = new EncodedNumber(splittedInput[1]);
		
	}

	public Integer decode() {

		StringBuilder sb = new StringBuilder();
		
		for(String digit : encodedNumber.getDigits() ) {
			sb.append(codec.decode(digit));
		}
		
		return Integer.valueOf(sb.toString());
	}
}
