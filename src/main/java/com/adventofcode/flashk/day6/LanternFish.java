package com.adventofcode.flashk.day6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LanternFish {

	private final static Integer DEFAULT_MATURE_INTERNAL_TIMER = 8;
	private final static Integer BIRTH_COOLDOWN_DAYS = 7;
	
	private List<Fish> fishes = new ArrayList<>();
	private Long totalFishesCount = 0L;
	
	public LanternFish(String inputs) {
		
		List<String> fishesStr = Arrays.asList(inputs.split(","));
		
		for(String fish : fishesStr) {
			this.fishes.add(new Fish(Integer.valueOf(fish), BIRTH_COOLDOWN_DAYS));
			totalFishesCount++;
		}

	}
	
	public Long solve(int totalDays) {
		
		for(int day = 0; day < totalDays; day++) {
			
			int newFishes = 0;
			for(Fish fish : fishes) {
				if(fish.isBirthTime()) {
					newFishes++;
					totalFishesCount++;
				}
			}
			
			for(int i = 0; i < newFishes; i++) {
				fishes.add(new Fish(DEFAULT_MATURE_INTERNAL_TIMER, BIRTH_COOLDOWN_DAYS));
			}
		}
		
		return totalFishesCount;
	}
}
