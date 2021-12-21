package com.adventofcode.flashk.day21;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DiracDice {

	private static final Pattern PLAYER_POSITIONS_PATTERN = Pattern.compile("position: ([0-9]*)");
	
	private static final int MAX_SCORE = 1000;
	private static final int MIN_DICE_VALUE = 1;
	private static final int MAX_DICE_VALUE = 100;
	private static final int ROLLS_PER_TURN = 3;

	private Deque<Player> turns = new ArrayDeque<>();
	
	// Total number of times the dice it has been rolled
	private int rolledTimes = 0; 
	
	// Rotates from MIN_DICE_VALUE to MAX_DICE_VALUE and repeats
	private int nextDiceValue = MIN_DICE_VALUE; 
	
	public DiracDice(List<String> inputs) {

		Matcher playerMatcher = PLAYER_POSITIONS_PATTERN.matcher(inputs.get(0));
		playerMatcher.find();
		Player player = new Player(Integer.valueOf(playerMatcher.group(1)));
		turns.add(player);
		
		playerMatcher = PLAYER_POSITIONS_PATTERN.matcher(inputs.get(1));
		playerMatcher.find();
		
		player = new Player(Integer.valueOf(playerMatcher.group(1)));
		turns.add(player);
		
	}
	
	public int solveA() {

		Player playerTurn;
		
		do {
			
			playerTurn = turns.poll();
			int rollValue = roll(ROLLS_PER_TURN);
			playerTurn.move(rollValue);
			turns.add(playerTurn);
			
		} while(playerTurn.getScore() < MAX_SCORE);

		// Poll the losing player to calculate score
		playerTurn = turns.poll();
		return rolledTimes * playerTurn.getScore();
	}

	/**
	 * Rolls the dice the specified number of rolls.
	 * @param numberOfRolls number of rolls
	 * @return the number of positions to move
	 */
	public int roll(int numberOfRolls) {
		
		int totalRoll = 0;
		
		for(int i = 1; i <= numberOfRolls; i++) {
			
			totalRoll += nextDiceValue;
			
			if(nextDiceValue >= MAX_DICE_VALUE) {
				nextDiceValue = MIN_DICE_VALUE;
			} else {
				nextDiceValue++;
			}
			
		}
		
		rolledTimes += numberOfRolls;
		
		return totalRoll;
	}
}
