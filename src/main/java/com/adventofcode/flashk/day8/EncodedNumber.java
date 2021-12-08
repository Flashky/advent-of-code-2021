package com.adventofcode.flashk.day8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import lombok.Getter;

@Getter
public class EncodedNumber {

	private final static String DIGIT_PATTERN = "([a-g]*)";
	private final static Pattern PATTERN = Pattern.compile(DIGIT_PATTERN);
	
	private List<String> digits = new ArrayList<>();
	
	public EncodedNumber(String input) {
		
		Matcher matcher = PATTERN.matcher(input);
		
		while(matcher.find()) {

			String digit = matcher.group(1);
			
			if(!digit.isEmpty()) {
				String sortedDigit = Arrays.stream(digit.split("|")).sorted().collect(Collectors.joining());
				digits.add(sortedDigit);
			}
		}
		
	}
		
}
