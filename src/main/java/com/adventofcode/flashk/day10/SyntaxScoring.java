package com.adventofcode.flashk.day10;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class SyntaxScoring {

	private Map<Character, Integer> corruptionScores = new HashMap<>();
	private Map<Character, Integer> incompleteScores = new HashMap<>();
	private Map<Character,Character> closingBracketMatcher = new HashMap<>();
	
	// Solution attributes
	private int corruptionScore = 0;
	private List<Long> incompleteLinesScores = new ArrayList<>();
	
	public SyntaxScoring(List<String> inputs) {

		corruptionScores.put(')', 3);
		corruptionScores.put(']', 57);
		corruptionScores.put('}', 1197);
		corruptionScores.put('>', 25137);
		
		incompleteScores.put(')', 1);
		incompleteScores.put(']', 2);
		incompleteScores.put('}', 3);
		incompleteScores.put('>', 4);
		
		closingBracketMatcher.put('(',')');
		closingBracketMatcher.put('[',']');
		closingBracketMatcher.put('{','}');
		closingBracketMatcher.put('<','>');
		
		for(String line : inputs) {
			syntaxCheck(line);
		}
		
	}
	
	public int solveA() {		
		return corruptionScore;		
	}
	
	public long solveB() {

		// Choose the middle score as the result
		Object[] orderedScores = incompleteLinesScores.stream().sorted().toArray();
		int middleIndex = (orderedScores.length / 2);
		
		return (Long) orderedScores[middleIndex];
	}
	
	private void syntaxCheck(String line) {
		
		Stack<Character> closingChunks = new Stack<>();
		
		String[] characters = line.split("|");
		int charIndex = 0;
		
		boolean foundIllegalChar = false;
		
		while(!foundIllegalChar && charIndex < characters.length) {
			
			Character character = characters[charIndex++].charAt(0);
			
			if(isOpeningCharacter(character)) {
				closingChunks.add(closingBracketMatcher.get(character));
			} else if (!closingChunks.pop().equals(character)){
				
				// Part 1 check - Corrupted characters
				corruptionScore += corruptionScores.get(character);
				foundIllegalChar = true;
				
			}
		}
		
		// Part 2 check - Missing closing brackets
		if(!foundIllegalChar) {

			Long incompleteScore = 0L;
			
			while(!closingChunks.isEmpty()) {
				incompleteScore = (incompleteScore * 5) + incompleteScores.get(closingChunks.pop());
			}
			
			incompleteLinesScores.add(incompleteScore);

		}
	}

	private boolean isOpeningCharacter(Character character) {
		return closingBracketMatcher.containsKey(character);
	}
	
}
