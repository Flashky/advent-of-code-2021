package com.adventofcode.flashk.day21;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DiracDice {

	private static final Pattern PLAYER_POSITIONS_PATTERN = Pattern.compile("position: ([0-9]*)");
	private static final int MIN_DICE_VALUE = 1;
	private static final int ROLLS_PER_TURN = 3;

	private int maxScore; 
	private int maxDiceValue;
	
	private Deque<Player> turns = new ArrayDeque<>();
	private Player currentPlayer;
	
	// Total number of times the dice it has been rolled
	private int rolledTimes = 0; 
	
	// Rotates from MIN_DICE_VALUE to maxDiceValue and repeats
	private int nextDiceValue = MIN_DICE_VALUE;
	
	// Quantum only
	private Map<Player,Long> wonGames = new HashMap<>();;
	
	public DiracDice(List<String> inputs) {

		Matcher playerMatcher = PLAYER_POSITIONS_PATTERN.matcher(inputs.get(0));
		
		playerMatcher.find();
		Player player = new Player(1, Integer.valueOf(playerMatcher.group(1)));
		turns.add(player);
		wonGames.put(player, 0L);
		
		playerMatcher = PLAYER_POSITIONS_PATTERN.matcher(inputs.get(1));
		
		playerMatcher.find();
		player = new Player(2, Integer.valueOf(playerMatcher.group(1)));
		turns.add(player);
		wonGames.put(player, 0L);

		
	}
	
	public DiracDice(DiracDice other) {
		
		// Copy universe status
		this.maxScore = other.maxScore;
		this.maxDiceValue = other.maxDiceValue;
		this.rolledTimes = other.rolledTimes;
		this.nextDiceValue = other.nextDiceValue;
		this.currentPlayer = new Player(other.currentPlayer);
		this.turns.add(new Player(other.turns.peek()));
		
		this.wonGames.put(this.currentPlayer, other.wonGames.get(other.currentPlayer));
		this.wonGames.put(this.turns.peek(), other.wonGames.get(other.turns.peek()));
		
	}

	public int solveA(final int diceSides, final int maxScore) {

		this.maxScore = maxScore;
		this.maxDiceValue = diceSides;
		
		do {
			
			currentPlayer = turns.poll();
			currentPlayer.resetPendingRolls();
			
			for(int i = 1; i <= ROLLS_PER_TURN; i++) {
				int rollValue = roll();
				currentPlayer.move(rollValue);	
			}

			turns.add(currentPlayer);
			
		} while(currentPlayer.getScore() < this.maxScore);

		// Poll the losing player to calculate score
		currentPlayer = turns.poll();
		return rolledTimes * currentPlayer.getScore();
	}

	public long solveB(final int diceSides, final int maxScore) {
		
		this.maxScore = maxScore;
		this.maxDiceValue = diceSides;
		
		wonGames = new HashMap<>();

		
		this.currentPlayer = turns.poll();
		playQuantumDiracDice();
		
		if(wonGames.get(turns.peekFirst()) > wonGames.get(turns.peekLast())) {
			return wonGames.get(turns.getFirst());
		}
		
		return wonGames.get(turns.getLast());
	}
	
	private void playQuantumDiracDice() {
		
		if(currentPlayer.getScore() >= this.maxScore) {
			long currentPlayerWonGames = wonGames.get(currentPlayer);
			wonGames.put(currentPlayer, currentPlayerWonGames+1);
			System.out.println("End of branch");
			return;
		} else if(!currentPlayer.hasPendingRolls()) {
			turns.add(currentPlayer);
			currentPlayer = turns.poll();
			currentPlayer.resetPendingRolls();
		}
		
		int rollValue = roll();
		currentPlayer.move(rollValue);	

		DiracDice child = new DiracDice(this);
		child.playQuantumDiracDice();
		
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
