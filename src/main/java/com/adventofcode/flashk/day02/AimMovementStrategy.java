package com.adventofcode.flashk.day02;

public class AimMovementStrategy extends MovementStrategy {

	private Integer aim = 0;

	protected void up(Integer value) {
		this.aim -= value;
	}

	protected void down(Integer value) {
		 this.aim += value;
	}
	
	protected void forward(Integer value) {
		this.horizontalPosition += value;
		this.depth += aim * value;
	}

}
