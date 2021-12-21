package com.adventofcode.flashk.day21;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lombok.Getter;

public class QuantumDiracDice {

	private static final Pattern PLAYER_POSITIONS_PATTERN = Pattern.compile("position: ([0-9]*)");
	
	private static final int MAX_SCORE = 21;
	private static final int MAX_DICE_VALUE = 3;
	private static final int MIN_DICE_VALUE = 1;
	private static final int ROLLS_PER_TURN = 3;

	private int initialP1Position;
	private int initialP2Position;
	
	public QuantumDiracDice(List<String> inputs) {
		
		Matcher playerMatcher = PLAYER_POSITIONS_PATTERN.matcher(inputs.get(0));
		
		playerMatcher.find();
		//Player player = new Player(Integer.valueOf(playerMatcher.group(1)));
		initialP1Position = Integer.valueOf(playerMatcher.group(1));
		
		playerMatcher = PLAYER_POSITIONS_PATTERN.matcher(inputs.get(1));
		
		playerMatcher.find();
		//player = new Player(Integer.valueOf(playerMatcher.group(1)));
		initialP2Position = Integer.valueOf(playerMatcher.group(1));
	}

	
	public long solveB() {
		
		long[] scoreboard = new long[2];
		scoreboard[0] = 0L;
		scoreboard[1] = 0L;
	
		roll(initialP1Position, 0, initialP2Position, 0, 0, 3, scoreboard);
		
		if(scoreboard[0] > scoreboard[1]) {
			return scoreboard[0];
		} 
		
		return scoreboard[1];
	}
	
	private void roll(int p1Position, int p1Score, 
						int p2Position, int p2Score, 
						int turn, int pendingRolls,
						long[] scoreboard) {

		if(p1Score >= MAX_SCORE) {
			scoreboard[0]++;
		} else if(p2Score >= MAX_SCORE) {
			scoreboard[1]++; 
		} else if(pendingRolls == 0) {
			
			if(turn == 0) {
				int nextP1Score = p1Score + p1Position;
				roll(p1Position, nextP1Score, p2Position, p2Score, 1, 3, scoreboard);
			} else {
				int nextP2Score = p2Score + p2Position;
				roll(p1Position, p1Score, p2Position, nextP2Score, 0, 3, scoreboard);
			}
			
		} else {
			
			// One rolled dice, different universe posibilities
			for(int diceValue = MIN_DICE_VALUE; diceValue <= MAX_DICE_VALUE; diceValue++) {
				
				if(turn == 0) {

					// Update player position, score and turn if applies
					int nextP1Position = move(diceValue, p1Position);
					roll(nextP1Position, p1Score, p2Position, p2Score, turn, pendingRolls-1, scoreboard);
					
				} else {
					
					// Update player position, score and turn if applies
					int nextP2Position = move(diceValue, p2Position);
					roll(p1Position, p1Score, nextP2Position, p2Score, turn, pendingRolls-1, scoreboard);
				}
				
			}
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
