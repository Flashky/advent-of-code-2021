package com.adventofcode.flashk.day6;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Fish {

	private int remainingDays;
	private int birthCooldown;
	
	public boolean isBirthTime() {
		
		boolean isBirthTime = false;
		
		if(remainingDays == 0) {
			isBirthTime = true;
			remainingDays = birthCooldown;
		}
		
		remainingDays --;

		return isBirthTime;
	}
}
