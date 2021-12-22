package com.adventofcode.flashk.day21;

import lombok.Getter;

@Getter
public class Player {
	
	private int score = 0;
	private int position;
	
	public Player(int position) {
		this.position = position;
	}
	
	public void move(int totalRollValue) {

		int finalPosition = position + totalRollValue;
		
		if(finalPosition < 10) {
			position = finalPosition;
		} else {
			finalPosition = finalPosition % 10;
			if(finalPosition == 0) {
				position = 10;
			} else {
				position = finalPosition;
			}
		}

		score += position;

	}

}
