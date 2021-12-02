package com.adventofcode.flashk.day2;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SubmarineMoveAim {

	private Integer depth = 0;
	private Integer horizontalPosition = 0;
	private Integer aim = 0;
	
	private final static String COMMAND_PATTERN = "(forward|down|up) ([0-9]*)";
	private Pattern pattern = Pattern.compile(COMMAND_PATTERN);
	
	public Integer solve(List<String> movements) {
		
		for(String movement : movements) {
			
			Matcher matcher = pattern.matcher(movement);
			matcher.find();

			String action = matcher.group(1);
			Integer value = Integer.valueOf(matcher.group(2));
			
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
