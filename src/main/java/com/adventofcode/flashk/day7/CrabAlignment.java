package com.adventofcode.flashk.day7;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CrabAlignment {

	private List<Integer> initialCrabsPosition;
	private int minX = Integer.MAX_VALUE;
	private int maxX = Integer.MIN_VALUE;
	private Map<Integer, Integer> fuelConsumptionMap = new HashMap<>();
	
	public CrabAlignment(String inputs) {
		
		initialCrabsPosition = Arrays.asList(inputs.split(","))
				.stream()
				.map(Integer::parseInt).
				collect(Collectors.toList());
		
		for(Integer crabPosX : initialCrabsPosition) {
			minX = Math.min(minX, crabPosX);
			maxX = Math.max(maxX, crabPosX);
		}
		
	}
	
	public int solve() {
		
		return solve(0);
	}
	
	public int solve(final int increasingFuelPerStep) {
		
		int minFuelConsumption = Integer.MAX_VALUE;
		
		for(int posX = minX; posX <= maxX; posX++) {
			
			int totalFuelConsumption = 0;
			for(Integer crabPosX : initialCrabsPosition) {
				totalFuelConsumption += calculateFuelConsumption(crabPosX, posX, increasingFuelPerStep);
			}
			
			minFuelConsumption = Math.min(minFuelConsumption, totalFuelConsumption);
		}
		
		return minFuelConsumption;
	}
	
	private int calculateFuelConsumption(int crabPosX, int posX, int increasingFuelConsumption) {
		
		if(increasingFuelConsumption ==  0) {
			return Math.abs(crabPosX - posX);
		}
		
		int totalFuelConsumption = 0;
		Integer stepX = (crabPosX > posX) ? crabPosX-posX : posX-crabPosX;
		
		
		if(fuelConsumptionMap.containsKey(stepX)) {
			totalFuelConsumption += fuelConsumptionMap.get(stepX);
		} else {
			for(int i = 0; i < stepX; i++) {
				Integer fuelConsumption = i+1;
				totalFuelConsumption += fuelConsumption;
				fuelConsumptionMap.put(fuelConsumption, totalFuelConsumption);
			}	
		}
		
		
		
		return totalFuelConsumption;
	}
}
