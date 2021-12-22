package com.adventofcode.flashk.day21;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class QuantumDiracDice {

	private static final Pattern PLAYER_POSITIONS_PATTERN = Pattern.compile("position: ([0-9]*)");
	
	private static final int MAX_SCORE = 21;

	private int initialP1Position;
	private int initialP2Position;
	
	// Any single roll has 3 possible values: {1,2,3}
	// There are 3 rolls per turn = 3^3 = 27 total roll value combinations
	// Some roll values yield the same result. Frequency analysis:
	// - A total roll of 3 happens 1 times.
	// - A total roll of 4 happens 3 times.
	// - A total roll of 5 happens 6 times.
	// - A total roll of 6 happens 7 times.
	// - A total roll of 8 happens 3 times.
	// - A total roll of 9 happens 1 times.
	
	// This map contains the relationship between total roll value and frequency:
	private Map<Integer, Integer> rollFrequency = new HashMap<>();
	
	public QuantumDiracDice(List<String> inputs) {
		
		Matcher playerMatcher = PLAYER_POSITIONS_PATTERN.matcher(inputs.get(0));
		
		playerMatcher.find();
		initialP1Position = Integer.valueOf(playerMatcher.group(1));
		
		playerMatcher = PLAYER_POSITIONS_PATTERN.matcher(inputs.get(1));
		
		playerMatcher.find();
		initialP2Position = Integer.valueOf(playerMatcher.group(1));
		
		// Frequency map
		rollFrequency.put(3, 1);
		rollFrequency.put(4, 3);
		rollFrequency.put(5, 6);
		rollFrequency.put(6, 7);
		rollFrequency.put(7, 6);
		rollFrequency.put(8, 3);
		rollFrequency.put(9, 1);
		
	}

	
	public long solveB() {

		Scoreboard totalScoreboard = rollPartial(initialP1Position, 0, initialP2Position, 0, 0);
		
		if(totalScoreboard.getP1Score() > totalScoreboard.getP2Score()) {
			return totalScoreboard.getP1Score();
		}
		
		return totalScoreboard.getP2Score();
	}

	private Scoreboard rollPartial(int p1Position, int p1Score, int p2Position, int p2Score, int turn) {

		if(p1Score >= MAX_SCORE) {
			return new Scoreboard(1,0);
		} else if(p2Score >= MAX_SCORE) {
			return new Scoreboard(0,1);
		} else {
			
			Scoreboard totalScoreboard = new Scoreboard(0,0);
			
			// Iterates through all possible total roll results
			for(int totalRollValue = 3; totalRollValue <= 9; totalRollValue++) {
			
				Scoreboard childScoreboard;
				if(turn == 0) {
					int nextP1Position = move(totalRollValue, p1Position);
					int nextP1Score = p1Score + nextP1Position;
					childScoreboard = rollPartial(nextP1Position, nextP1Score, p2Position, p2Score, 1);
				} else {
					int nextP2Position = move(totalRollValue, p2Position);
					int nextP2Score = p2Score + nextP2Position;
					childScoreboard = rollPartial(p1Position, p1Score, nextP2Position, nextP2Score, 0);
				}
				
				// Multiply the scoreboard by its frequency and add to the total scoreboard
				childScoreboard.multiply(rollFrequency.get(totalRollValue));
				totalScoreboard.sum(childScoreboard);
			}
			
			return totalScoreboard;
			
		}
			
		
	}
	
	private int move(int rollValue, int currentPosition) {

		int estimatedPosition = currentPosition + rollValue;
		if(estimatedPosition < 10) {
			currentPosition = estimatedPosition;
		} else {
				
			estimatedPosition = estimatedPosition % 10;
			if(estimatedPosition == 0) {
				currentPosition = 10;
			} else {
				currentPosition = estimatedPosition;
			}
		}
		return currentPosition;

	}
	
}
