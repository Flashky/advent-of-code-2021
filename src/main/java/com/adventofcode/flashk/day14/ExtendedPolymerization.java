package com.adventofcode.flashk.day14;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExtendedPolymerization {

	private final static Pattern INSERTION_RULE_PATTERN = Pattern.compile("([A-Z][A-Z]) -> ([A-Z])");
	
	private String polymer = "";
	private Map<String, Character> insertionRules = new HashMap<>();
	private Deque<Character> polymerizator = new ArrayDeque<>(); 
	private Map<Character, Long> elementCount = new HashMap<>();
	
	private Long mostCommonCount = Long.MIN_VALUE;
	private Long lessCommonCount = Long.MAX_VALUE;
	
	public ExtendedPolymerization(List<String> inputs) {
	
		for(String input : inputs) {
			
			Matcher matcher = INSERTION_RULE_PATTERN.matcher(input);
			
			if(matcher.find()) {
				String adjacentElements = matcher.group(1);
				Character elementToInsert = matcher.group(2).charAt(0);
				insertionRules.put(adjacentElements, elementToInsert);
			} else if(!"".equals(input)){
				polymer = input;
				
				char[] polymerElements = polymer.toCharArray();
				for(char element : polymerElements) {
					polymerizator.add(element);
					updateElementCount(element);
				}
			}
		}
	}
	
	
	public Long solveA(int times) {

		for(int i = 0; i < times; i++) {
			Deque<Character> polymerizatedResult = new ArrayDeque<>();
			while(polymerizator.size() > 1) {
				
				Character first = polymerizator.poll();
				Character second = polymerizator.peek();
				String polymer = String.valueOf(first) + String.valueOf(second);
				Character elementToInsert = insertionRules.get(polymer);
				
				polymerizatedResult.add(first);
				polymerizatedResult.add(elementToInsert);
				
				updateElementCount(elementToInsert);
			}
			
			// Last missing character
			polymerizatedResult.add(polymerizator.poll());
			polymerizator = polymerizatedResult;
		}
		
		Long max = elementCount.values().stream().mapToLong(v -> v).max().orElse(Long.MIN_VALUE);
		Long min = elementCount.values().stream().mapToLong(v -> v).min().orElse(Long.MAX_VALUE);
		
		return max-min;
	}
	
	private void updateElementCount(Character element) {
		if(!elementCount.containsKey(element)) {
			elementCount.put(element, 1L);
		} else {
			Long count = elementCount.get(element) + 1;
			elementCount.put(element, count);
		}
	}
}
