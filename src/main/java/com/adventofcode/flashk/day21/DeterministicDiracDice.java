package com.adventofcode.flashk.day21;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DeterministicDiracDice {

	private static final Pattern PLAYER_POSITIONS_PATTERN = Pattern.compile("position: ([0-9]*)");
	
	private static final int MAX_SCORE = 1000;
	private static final int MAX_DICE_VALUE = 100;
	private static final int MIN_DICE_VALUE = 1;
	private static final int ROLLS_PER_TURN = 3;

	private Queue<Player> turns = new LinkedList<>();
	
	// Total number of times the dice it has been rolled
	private int rolledTimes = 0; 
	
	// Rotates from MIN_DICE_VALUE to maxDiceValue and repeats
	private int nextDiceValue = MIN_DICE_VALUE;
	
	public DeterministicDiracDice(List<String> inputs) {

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
		
		Player currentPlayer;
		
		do {
			
			// Take a player, play the turn and put it back in the queue
			currentPlayer = turns.poll();
			currentPlayer.move(roll());	
			turns.add(currentPlayer);
			
		} while(currentPlayer.getScore() < MAX_SCORE);

		// Poll the losing player to calculate score
		return rolledTimes *  turns.poll().getScore();
	}

	/**
	 * Rolls the dice the specifiedd amount by {@link #ROLLS_PER_TURN}.
	 */
	private int roll() {
		
		int totalRollValue = 0;
		
		for(int i = 1; i <= ROLLS_PER_TURN; i++) {
			totalRollValue += nextDiceValue;
			
			if(nextDiceValue >= MAX_DICE_VALUE) {
				nextDiceValue = MIN_DICE_VALUE;
			} else {
				nextDiceValue++;
			}
			
			rolledTimes++;
		}
		
		return totalRollValue;
	}
}
