package com.adventofcode.flashk.day2;

public class SubmarineMove extends AbstractSubmarineMove {

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
