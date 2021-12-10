package com.adventofcode.flashk.day05;

import com.adventofcode.flashk.common.Vector2;

import lombok.Getter;

@Getter
public class Vent {

	private Vector2 startPos;
	private Vector2 endPos;
	private Direction direction;
	
	public Vent(Vector2 startPos, Vector2 endPos) {
		
		this.startPos = startPos;
		this.endPos = endPos;
		this.direction = new Direction(startPos, endPos);
		
	}
	
	
}
