package com.adventofcode.flashk.day8;

import java.util.ArrayList;
import java.util.List;

public class SevenSegmentSearch {
	
	private List<DigitalEntry> digitalEntries = new ArrayList<>();
	
	public SevenSegmentSearch(List<String> inputs) {
		
		for(String input : inputs) {
			digitalEntries.add(new DigitalEntry(input));
		}
		
	}
	
	public int solveA() {
		
		int result = 0;
		
		for(DigitalEntry digitalEntry : digitalEntries) {
			for(String digit : digitalEntry.getOutputDigit().getDigits()) {
				switch (digit.length()) {
					case 2:
					case 3:
					case 4:
					case 7: result++; break;
					default: break;
				}
			}
		}
		
		return result;
	}
	
	public int solveB() {
		
		int result = 0;
		
		for(DigitalEntry digitalEntry : digitalEntries) {
			StringBuilder sb = new StringBuilder();
			for(String outputDigit : digitalEntry.getOutputDigit().getDigits()) {
				Integer decodedDigit = digitalEntry.getInputSignal().decode(outputDigit);
				sb.append(decodedDigit);
			}
			Integer decodedNumber = Integer.valueOf(sb.toString());
			result += decodedNumber;
		}
		
		
		return result;
	}

}
