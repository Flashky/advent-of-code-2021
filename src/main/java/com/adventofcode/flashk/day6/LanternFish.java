package com.adventofcode.flashk.day6;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Map;
import java.util.stream.Collectors;

public class LanternFish {

	private final static Integer DEFAULT_MATURE_INTERNAL_TIMER = 8;
	
	private Deque<Long> fishesByDay = new ArrayDeque<>();
	private Long totalFishesCount = 0L;
	
	public LanternFish(String inputs) {
		
		// Order and group the fishes by remaining days to birth
		Map<Long, Long> fishes = Arrays.asList(inputs.split(","))
				.stream()
				.map(Integer::parseInt)
				.sorted()
				.collect(Collectors.groupingBy(Integer::longValue, Collectors.counting()));

		// Add the fishes to a deque
		for(Long i = 0L; i <= DEFAULT_MATURE_INTERNAL_TIMER; i++) {
			Long fishCount = fishes.getOrDefault(i, 0L);
			fishesByDay.add(fishCount);
			totalFishesCount += fishCount;
		}

	}
	
	public Long solve(int totalDays) {
		
		for(int day = 0; day < totalDays; day++) {
			
			Long newBornFishes = fishesByDay.poll();
			Long fishesCdMinusOne = fishesByDay.pollLast();
			Long fishesCdMinusTwo = fishesByDay.pollLast();
			
			fishesByDay.addLast(fishesCdMinusTwo + newBornFishes);
			fishesByDay.addLast(fishesCdMinusOne);
			fishesByDay.addLast(newBornFishes);
			
			totalFishesCount += newBornFishes;

		}
		
		return totalFishesCount;
	}
}
