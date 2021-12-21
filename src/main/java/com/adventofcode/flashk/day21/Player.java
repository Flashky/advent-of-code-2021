package com.adventofcode.flashk.day21;

import lombok.Getter;

@Getter
public class Player {

	private int score = 0;
	private int position;
	
	public Player(int position) {
		this.position = position;
	}
	
	public void move(int rollValue) {

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
		
		score += position;

	}
}
