package com.adventofcode.flashk.day8;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DigitalEntry {

	private InputSignal inputSignal;
	private OutputDigit outputDigit;
	
	public DigitalEntry(String input) {
		
		String[] splittedInput = input.split("\\|");

		this.inputSignal = new InputSignal(splittedInput[0]);
		this.outputDigit = new OutputDigit(splittedInput[1]);
		
	}
}
