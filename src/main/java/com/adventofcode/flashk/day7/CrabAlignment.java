package com.adventofcode.flashk.day7;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CrabAlignment {

	private List<Integer> crabsPositions;
	private int minX = Integer.MAX_VALUE;
	private int maxX = Integer.MIN_VALUE;
	private Map<Integer, Integer> fuelConsumptionMap = new HashMap<>();
	
	public CrabAlignment(String inputs) {
		
		crabsPositions = Arrays.asList(inputs.split(","))
				.stream()
				.map(Integer::parseInt)
				//.sorted()
				.collect(Collectors.toList());
		
		for(Integer crabPosX : crabsPositions) {
			minX = Math.min(minX, crabPosX);
			maxX = Math.max(maxX, crabPosX);
		}
		
	}
	
	public int solve() {
		return solve(0);
	}
	
	public int solve(final int increasingFuelPerStep) {
		
		int minFuelConsumption = Integer.MAX_VALUE;
		
		if(increasingFuelPerStep > 0) {
			calculateFuelConsumptionMap(increasingFuelPerStep);
		}
		
		for(int posX = minX; posX <= maxX; posX++) {
			
			// Check all positions while its fuel consumption is below current minimum fuel consumption
			int currentFuelConsumption = 0;
			Integer crabIndex = 0;
			
			while((crabIndex < crabsPositions.size()) && (currentFuelConsumption < minFuelConsumption)) {
				
				Integer movements = Math.abs(crabsPositions.get(crabIndex) - posX);
				
				if(increasingFuelPerStep == 0) {
					currentFuelConsumption += movements;
				} else {
					currentFuelConsumption += fuelConsumptionMap.get(movements);
				}
				
				crabIndex ++; 
			}
			
			// Selection of most optimal solution
			if(currentFuelConsumption < minFuelConsumption) {
				minFuelConsumption = Math.min(minFuelConsumption, currentFuelConsumption);
			}
			
		}
		
		return minFuelConsumption;
	}

	private void calculateFuelConsumptionMap(final int increasingFuelPerStep) {
		
		int fuelConsumption = increasingFuelPerStep;
		
		fuelConsumptionMap.put(0, 0);
		
		for(int i = 1; i <= maxX; i++) {
			fuelConsumptionMap.put(i, fuelConsumptionMap.get(i-1)+fuelConsumption);
			fuelConsumption += increasingFuelPerStep;
		}
	}

}
