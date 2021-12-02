package com.adventofcode.flashk.day2;

import java.util.List;

public class SubmarineMove {

	private Integer depth = 0;
	private Integer horizontalPosition = 0;
	
	public Integer solve(List<String> movements) {
	
		for(String movement : movements) {
			
			String[] commands = movement.split(" ");
			String action = commands[0];
			Integer value = Integer.valueOf(commands[1]);
			
			switch(action) {
				case "forward": horizontalPosition += value; break;
				case "down": depth += value; break;
				case "up": depth -= value; break;
				default: break;
			}
			
		}

		return depth * horizontalPosition;
	}

	public Integer getDepth() {
		return depth;
	}


	public Integer getHorizontalPosition() {
		return horizontalPosition;
	}
	
}
