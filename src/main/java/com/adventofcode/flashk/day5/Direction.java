package com.adventofcode.flashk.day5;

import com.adventofcode.flashk.common.Vector2;

import lombok.Getter;

@Getter
public class Direction {

	private int x;
	private int y;
	
	public Direction(Vector2 startPos, Vector2 endPos) {
		
		// Calculations to obtain an unitary vector to define the direction
		Integer xSubstraction = endPos.getX() - startPos.getX();
		Integer ySubstraction = endPos.getY() - startPos.getY();
		
		x = (xSubstraction != 0) ? (xSubstraction / Math.abs(xSubstraction)) : 0;
		y = (ySubstraction != 0) ? (ySubstraction / Math.abs(ySubstraction)) : 0;
		
	}
	
	boolean isDiagonal() {
		return (x != 0) && (y != 0);
	}
	
}
