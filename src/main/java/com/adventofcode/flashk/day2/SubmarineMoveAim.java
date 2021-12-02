package com.adventofcode.flashk.day2;

public class SubmarineMoveAim extends AbstractSubmarineMove {

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
