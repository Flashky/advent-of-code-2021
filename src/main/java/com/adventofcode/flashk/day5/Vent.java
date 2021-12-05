package com.adventofcode.flashk.day5;

import lombok.Getter;

@Getter
public class Vent {

	private Position startPos;
	private Position endPos;
	private Position direction;
	
	public Vent(Position startPos, Position endPos) {
		
		this.startPos = startPos;
		this.endPos = endPos;

		// Calculations to obtain an unitary vector to define the direction
		Integer xSubstraction = endPos.getX() - startPos.getX();
		Integer ySubstraction = endPos.getY() - startPos.getY();
		
		Integer xDir = (xSubstraction != 0) ? (xSubstraction / Math.abs(xSubstraction)) : 0;
		Integer yDir = (ySubstraction != 0) ? (ySubstraction / Math.abs(ySubstraction)) : 0;
		
		this.direction = new Position(xDir, yDir);
	}
	
	
}
