package com.adventofcode.flashk.day02;

public class BasicMovementStrategy extends MovementStrategy {

	protected void up(Integer value) {
		this.depth -= value;
	}

	protected void down(Integer value) {
		this.depth += value;
	}

	protected void forward(Integer value) {
		this.horizontalPosition += value;
	}
	
}
