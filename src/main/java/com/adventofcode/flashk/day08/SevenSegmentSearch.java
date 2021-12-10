package com.adventofcode.flashk.day08;

import java.util.ArrayList;
import java.util.List;

public class SevenSegmentSearch {
	
	private List<Decoder> decoders = new ArrayList<>();
	
	public SevenSegmentSearch(List<String> inputs) {
		
		for(String input : inputs) {
			decoders.add(new Decoder(input));
		}
		
	}
	
	public int solveA() {
		
		int result = 0;
		
		for(Decoder decoder : decoders) {
			for(String digit : decoder.getEncodedNumber().getDigits()) {
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
		
		for(Decoder decoder : decoders) {
			result += decoder.decode();
		}
		
		return result;
	}

}
