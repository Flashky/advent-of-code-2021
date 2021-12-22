package com.adventofcode.flashk.day21;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Scoreboard {

	private long p1Score;
	private long p2Score;

	/** 
	 * Adds the result from other scoreboard to the current scoreboard.
	 * @param other
	 */
	public void sum(Scoreboard other) {
		this.p1Score += other.p1Score;
		this.p2Score += other.p2Score;
	}
	
	/**
	 * Multiply the current scoreboard value N times.
	 * @param times
	 */
	public void multiply(int times) {
		this.p1Score *= times;
		this.p2Score *= times;
	}
}
