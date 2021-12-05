package com.adventofcode.flashk.day5;

import lombok.Getter;

@Getter
public class Vent {

	private Position startPos;
	private Position endPos;
	private Direction direction;
	
	public Vent(Position startPos, Position endPos) {
		
		this.startPos = startPos;
		this.endPos = endPos;
		this.direction = new Direction(startPos, endPos);
		
	}
	
	
}
