package com.adventofcode.flashk.day21;

import lombok.Getter;
import lombok.Setter;

@Getter
public class Player {
	
	private int score = 0;
	private int position;
	private int pendingRolls = 3;
	
	@Setter
	private int wonGames = 0;
	
	public Player(int position) {
		this.position = position;
	}
	
	public Player(Player other) {
		this.score = other.score;
		this.position = other.position;
		this.pendingRolls = other.pendingRolls;
		this.wonGames = other.wonGames;
	}
	
	public void move(int rollValue) {

		if(hasPendingRolls() ) {
			pendingRolls--;
			
			int estimatedPosition = position + rollValue;
			if(estimatedPosition < 10) {
				position = estimatedPosition;
			} else {
				
				estimatedPosition = estimatedPosition % 10;
				if(estimatedPosition == 0) {
					position = 10;
				} else {
					position = estimatedPosition;
				}
			}
			
			if(pendingRolls == 0) {
				score += position;
			}
		}

	}
	
	public void resetPendingRolls() {
		pendingRolls = 3;
	}
	
	public boolean hasPendingRolls() {
		return pendingRolls > 0;
	}
	
	public void increaseWonGameCount() {
		wonGames++;
	}

}
