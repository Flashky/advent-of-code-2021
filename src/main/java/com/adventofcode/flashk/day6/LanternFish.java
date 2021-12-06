package com.adventofcode.flashk.day6;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.stream.Collectors;

public class LanternFish {

	private final static Integer DEFAULT_MATURE_INTERNAL_TIMER = 8;
	
	private Deque<Long> fishesByDay = new ArrayDeque<>();
	private Long totalFishesCount = 0L;
	
	public LanternFish(String inputs) {
	
		List<Integer> fishes = Arrays.asList(inputs.split(","))
									.stream()
									.map(Integer::parseInt)
									.collect(Collectors.toList());	

		Long[] fishesByDayAux = new Long[DEFAULT_MATURE_INTERNAL_TIMER+1];
		
		for(Integer fish : fishes) {
			
			totalFishesCount++;
			
			if(fishesByDayAux[fish] == null) {
				fishesByDayAux[fish] = 1L;
			} else {
				fishesByDayAux[fish]++;
			}
		}
		
		for(int i = 0; i < fishesByDayAux.length; i++) {
			
			if(fishesByDayAux[i] == null) {
				fishesByDay.add(0L);
			} else {
				fishesByDay.add(fishesByDayAux[i]);
			}
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
