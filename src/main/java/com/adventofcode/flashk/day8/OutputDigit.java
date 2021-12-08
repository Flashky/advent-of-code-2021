package com.adventofcode.flashk.day8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import lombok.Getter;

@Getter
public class OutputDigit {

	protected List<String> digits = new ArrayList<>();
	
	public void addDigit(String digit) {
		
		String sortedDigit = Arrays.stream(digit.split("|")).sorted().collect(Collectors.joining());
		digits.add(sortedDigit);
		
	}
	
	
	public boolean isComplete() {
		return digits.size() == 4;
	}
	
}
