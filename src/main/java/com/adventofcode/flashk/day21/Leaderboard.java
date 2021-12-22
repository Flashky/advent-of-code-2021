package com.adventofcode.flashk.day21;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Leaderboard {

	private long p1Wins;
	private long p2Wins;

	/** 
	 * Adds the result from other leaderboard to the current leaderboard.
	 * @param other
	 */
	public void sum(Leaderboard other) {
		this.p1Wins += other.p1Wins;
		this.p2Wins += other.p2Wins;
	}
	
	/**
	 * Multiply the current leaderboard value N times.
	 * @param times
	 */
	public void multiply(int times) {
		this.p1Wins *= times;
		this.p2Wins *= times;
	}
}
