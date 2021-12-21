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

	private static final int ROLLS_PER_TURN = 3;

	private Deque<Player> turns = new ArrayDeque<>();
	private Player currentPlayer;
	
	private int maxDiceValue;
	
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
	
	public int solveA(int diceSides) {

		this.maxDiceValue = diceSides;
		
		do {
			
			currentPlayer = turns.poll();
			
			for(int i = 1; i <= ROLLS_PER_TURN; i++) {
				int rollValue = roll();
				currentPlayer.move(rollValue, i);	
			}

			turns.add(currentPlayer);
			
		} while(currentPlayer.getScore() < MAX_SCORE);

		// Poll the losing player to calculate score
		currentPlayer = turns.poll();
		return rolledTimes * currentPlayer.getScore();
	}

	/**
	 * Rolls the dice the specified number of rolls.
	 * @param numberOfRolls number of rolls
	 * @return the number of positions to move
	 */
	public int roll() {
		
		int roll = nextDiceValue;
		
		if(nextDiceValue >= maxDiceValue) {
			nextDiceValue = MIN_DICE_VALUE;
		} else {
			nextDiceValue++;
		}
		
		rolledTimes++;;
		
		return roll;
	}
}
