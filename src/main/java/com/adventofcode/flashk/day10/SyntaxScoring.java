package com.adventofcode.flashk.day10;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class SyntaxScoring {

	private Map<Character, Integer> syntaxErrorScores = new HashMap<>();
	private Map<Character, Integer> incompleteErrorScores = new HashMap<>();
	private Map<Character,Character> chunkMap = new HashMap<>();
	private List<String> lines;
	
	public SyntaxScoring(List<String> inputs) {
		
		this.lines = inputs;
		
		syntaxErrorScores.put(')', 3);
		syntaxErrorScores.put(']', 57);
		syntaxErrorScores.put('}', 1197);
		syntaxErrorScores.put('>', 25137);
		
		incompleteErrorScores.put(')', 1);
		incompleteErrorScores.put(']', 2);
		incompleteErrorScores.put('}', 3);
		incompleteErrorScores.put('>', 4);
		
		chunkMap.put('(',')');
		chunkMap.put('[',']');
		chunkMap.put('{','}');
		chunkMap.put('<','>');
		
		
	}
	
	public int solve() {
		
		int result = 0;
		
		for(String line : lines) {
			
			Stack<Character> openingChunks = new Stack<>();
			Stack<Character> closingChunks = new Stack<>();
			
			result += syntaxCheck(line, openingChunks, closingChunks);

		}
		
		return result;
	}
	
	public long solveB() {
		
		List<Long> incompleteLinesScores = new ArrayList<>();
		
		for(String line : lines) {
			
			Stack<Character> openingChunks = new Stack<>();
			Stack<Character> closingChunks = new Stack<>();
			
			if((syntaxCheck(line, openingChunks, closingChunks) == 0) && (closingChunks.size() != 0)) {

				Long incompleteLineScore = 0L;
				
				while(closingChunks.size() > 0) {
					Character character = closingChunks.pop();
					incompleteLineScore = (incompleteLineScore * 5) + incompleteErrorScores.get(character);
				}
				
				incompleteLinesScores.add(incompleteLineScore);
			}
		}
		
		// Choose the middle score as the result
		Object[] orderedScores = incompleteLinesScores.stream().sorted().toArray();
		int middleIndex = (orderedScores.length / 2);
		
		return (Long) orderedScores[middleIndex];
	}
	
	private int syntaxCheck(String line, Stack<Character> openingChunks, Stack<Character> closingChunks) {
		
		int result = 0;
		
		String[] characters = line.split("|");
		int charIndex = 0;
		
		boolean foundIllegalChar = false;
		
		while(!foundIllegalChar && charIndex < characters.length) {
			
			Character character = characters[charIndex++].charAt(0);
			
			if(isOpeningCharacter(character)) {
				openingChunks.add(character);
				closingChunks.add(chunkMap.get(character));
			} else {
				Character expectedClosingChar = closingChunks.pop();
				if(expectedClosingChar.equals(character)) {						
					// There is match between opening and closing character
					openingChunks.pop();
				} else {			
					// Syntax Error. Increase score based on the wrong closing character
					result += syntaxErrorScores.get(character);
					foundIllegalChar = true;
				}
			}
		}
		
		return result;
	}

	private boolean isOpeningCharacter(Character character) {
		return chunkMap.containsKey(character);
	}
}
