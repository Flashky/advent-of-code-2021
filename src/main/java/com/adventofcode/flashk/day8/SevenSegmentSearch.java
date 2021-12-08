package com.adventofcode.flashk.day8;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SevenSegmentSearch {

	private final static String DIGIT_PATTERN = "([a-g]*)";
	private final static Pattern PATTERN = Pattern.compile(DIGIT_PATTERN);
	
	private List<DigitalEntry> digitalEntries = new ArrayList<>();
	
	public SevenSegmentSearch(List<String> inputs) {
		
		for(String input : inputs) {
			String[] splittedInput = input.split("\\|");
			
			Matcher matcher = PATTERN.matcher(splittedInput[0]);
			
			InputSignal signal = new InputSignal(splittedInput[0]);

			matcher = PATTERN.matcher(splittedInput[1]);
			
			OutputDigit outputDigit = new OutputDigit();
			while(matcher.find()) {

				String digit = matcher.group(1);
				
				if(outputDigit.isComplete()) {
					digitalEntries.add(new DigitalEntry(signal, outputDigit));
					outputDigit = new OutputDigit();
				} else if(!digit.isEmpty()) {
					outputDigit.addDigit(digit);
				}
			}
			
			
			
			
			
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
					case 7:
						result++;
						break;
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
