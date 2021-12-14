package com.adventofcode.flashk.day14;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExtendedPolymerization {

	private final static Pattern INSERTION_RULE_PATTERN = Pattern.compile("([A-Z][A-Z]) -> ([A-Z])");

	private Map<String, Character> formulas = new HashMap<>();
	private Map<Character, Long> elementCount = new HashMap<>();
	private Map<String, Long> pairsCount = new HashMap<>();
	
	public ExtendedPolymerization(List<String> inputs) {
	
		String polymer;
		for(String input : inputs) {
			
			Matcher matcher = INSERTION_RULE_PATTERN.matcher(input);
			
			if(matcher.find()) {
				String adjacentElements = matcher.group(1);
				Character elementToInsert = matcher.group(2).charAt(0);
				formulas.put(adjacentElements, elementToInsert);
				
			} else if(!"".equals(input)){
				polymer = input;
				
				// Count initial pair occurrences
				String polymerSplitter = polymer;
				while(polymerSplitter.length() > 1) {
					String pair = polymerSplitter.substring(0, 2);
					polymerSplitter = polymerSplitter.substring(1, polymerSplitter.length());
					
					Long pairCount = pairsCount.getOrDefault(pair, 0L) + 1L;
					pairsCount.put(pair, pairCount);
				}
				
				// Count initial character occurrences
				char[] polymerElements = polymer.toCharArray();
				for(char element : polymerElements) {
					updateElementCount(element);
				}
			}
		}
	}
	
	public Long solveA(int times) {

		for(int i = 0; i < times; i++) {
			
			Map<String, Long> newPairsCount = new HashMap<>();
			for(Entry<String, Long> entryPair : pairsCount.entrySet()) {
				
				// Get data
				String pair = entryPair.getKey();
				Long occurrences = entryPair.getValue();
				
				// Update element count
				Character element = formulas.get(pair);
				updateElementCount(element, occurrences);
				
				// Create two new pairs and add it to the next iteration map
				String newPair = pair.substring(0,1) + element;
				Long pairCount = newPairsCount.getOrDefault(newPair, 0L) + occurrences;
				newPairsCount.put(newPair, pairCount);
				
				newPair = element + pair.substring(1,2);
				pairCount = newPairsCount.getOrDefault(newPair, 0L) + occurrences;
				newPairsCount.put(newPair, pairCount);
			}
			pairsCount = newPairsCount;
		}
		
		Long max = elementCount.values().stream().mapToLong(v -> v).max().orElse(Long.MIN_VALUE);
		Long min = elementCount.values().stream().mapToLong(v -> v).min().orElse(Long.MAX_VALUE);
		
		return max-min;
	}
	
	private void updateElementCount(Character element, Long times) {
		Long elementCount = times + this.elementCount.getOrDefault(element, 0L);
		this.elementCount.put(element, elementCount);
	}
	
	private void updateElementCount(Character element) {
		updateElementCount(element,1L);
	}
}
