package com.adventofcode.flashk.day10;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class SyntaxScoring {

	private List<String> lines;
	private Map<Character, Integer> syntaxErrorScores = new HashMap<>();
	private Map<Character, Integer> incompleteErrorScores = new HashMap<>();
	private Map<Character,Character> chunkMap = new HashMap<>();
	
	// Solution attributes
	private int corruptionScore = 0;
	private List<Long> incompleteLinesScores = new ArrayList<>();
	
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
		
		for(String line : lines) {
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
	
	/**
	 * Performs a syntax check on the input line.
	 * <p>
	 * If the line is corrupted:
	 * - It will return the syntax error score.
	 * </p>
	 * <p>if the line is <b>NOT</b> corrupted but is missing closing brackets:
	 * - It will return a 0
	 * - The stack will not be empty.
	 * </p>
	 * 
	 * @param line
	 * @param closingChunks
	 * @return
	 */
	private void syntaxCheck(String line) {
		
		Stack<Character> closingChunks = new Stack<>();
		
		String[] characters = line.split("|");
		int charIndex = 0;
		
		boolean foundIllegalChar = false;
		
		while(!foundIllegalChar && charIndex < characters.length) {
			
			Character character = characters[charIndex++].charAt(0);
			
			if(isOpeningCharacter(character)) {
				closingChunks.add(chunkMap.get(character));
			} else if (!closingChunks.pop().equals(character)){
				
				// Part 1 check - Corrupted characters
				corruptionScore += syntaxErrorScores.get(character);
				foundIllegalChar = true;
				
			}
		}
		
		// Part 2 check - Missing closing brackets
		if(!foundIllegalChar) {

			Long incompleteScore = 0L;
			
			while(!closingChunks.isEmpty()) {
				incompleteScore = (incompleteScore * 5) + incompleteErrorScores.get(closingChunks.pop());
			}
			
			incompleteLinesScores.add(incompleteScore);

		}
	}

	private boolean isOpeningCharacter(Character character) {
		return chunkMap.containsKey(character);
	}
	
}
