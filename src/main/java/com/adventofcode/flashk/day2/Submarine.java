package com.adventofcode.flashk.day2;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Submarine {

	private MovementStrategy movementStrategy;
	
	private final static String COMMAND_PATTERN = "(forward|down|up) ([0-9]*)";
	private Pattern pattern = Pattern.compile(COMMAND_PATTERN);
	
	
	public Submarine(MovementStrategy movementStrategy) {
		this.movementStrategy = movementStrategy;
	}
	
	public Integer solve(List<String> movements) {
	
		for(String movement : movements) {
			
			Matcher matcher = pattern.matcher(movement);
			matcher.find();

			String action = matcher.group(1);
			Integer value = Integer.valueOf(matcher.group(2));
			
			switch(action) {
				case "forward": movementStrategy.forward(value); break;
				case "down": movementStrategy.down(value); break;
				case "up": movementStrategy.up(value); break;
				default: break;
			}
			
		}

		return movementStrategy.getDepth() * movementStrategy.getHorizontalPosition();
	}

	public MovementStrategy getMovementStrategy() {
		return movementStrategy;
	}
	
}
