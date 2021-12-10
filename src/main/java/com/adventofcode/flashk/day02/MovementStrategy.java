package com.adventofcode.flashk.day02;

public abstract class MovementStrategy {

	protected Integer depth = 0;
	protected Integer horizontalPosition = 0;

	public Integer getDepth() {
		return depth;
	}

	public Integer getHorizontalPosition() {
		return horizontalPosition;
	}
	
	abstract void up(Integer value);
	abstract void down(Integer value);
	abstract void forward(Integer value);
	
}
