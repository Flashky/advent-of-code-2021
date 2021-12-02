package com.adventofcode.flashk.day2;

import java.util.List;

public class SubmarineMoveAim {

	private Integer depth = 0;
	private Integer horizontalPosition = 0;
	private Integer aim = 0;
	
	public Integer solve(List<String> movements) {
		
		for(String movement : movements) {
			
			String[] commands = movement.split(" ");
			String action = commands[0];
			Integer value = Integer.valueOf(commands[1]);
			
			switch(action) {
				case "forward": {
					horizontalPosition += value;
					depth += aim * value;
				} break;
				case "down": aim += value; break;
				case "up": aim	 -= value; break;
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
	
	public Integer getAim() {
		return aim;
	}
}
