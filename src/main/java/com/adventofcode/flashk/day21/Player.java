package com.adventofcode.flashk.day21;

import lombok.Getter;

@Getter
public class Player {

	private final int number;
	private int score = 0;
	private int position;
	private int pendingRolls = 3;
	
	public Player(int number, int position) {
		this.number = number;
		this.position = position;
	}
	
	public Player(Player other) {
		this.number = other.number;
		this.score = other.score;
		this.position = other.position;
		this.pendingRolls = other.pendingRolls;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + number;
		result = prime * result + pendingRolls;
		result = prime * result + position;
		result = prime * result + score;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Player other = (Player) obj;
		if (number != other.number)
			return false;
		if (pendingRolls != other.pendingRolls)
			return false;
		if (position != other.position)
			return false;
		if (score != other.score)
			return false;
		return true;
	}
	
	

}
