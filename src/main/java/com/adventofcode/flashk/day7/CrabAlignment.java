package com.adventofcode.flashk.day7;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CrabAlignment {

	private List<Integer> crabsPositions;

	private int averagePosX;
	private int medianPosX;
	
	private Map<Integer, Integer> fuelConsumptionMap = new HashMap<>();
	
	public CrabAlignment(String inputs) {
		
		crabsPositions = Arrays.asList(inputs.split(","))
				.stream()
				.map(Integer::parseInt)
				.sorted()
				.collect(Collectors.toList());
		
		// Obtain average and median
		double avgPosXDouble = crabsPositions.stream().mapToInt(v -> v).average().getAsDouble();

		averagePosX = BigDecimal.valueOf(avgPosXDouble)
								.setScale(1, RoundingMode.HALF_DOWN) // Remove decimals
								.setScale(0, RoundingMode.HALF_DOWN)
								.intValue();
		
		medianPosX =  crabsPositions.get(crabsPositions.size() / 2);

	}
	
	public int solve() {
		return solve(0);
	}
	
	public int solve(final int increasingFuelPerStep) {
		
		int posX; // All crabs will move to this position
		if(increasingFuelPerStep > 0) {
			calculateFuelConsumptionMap(increasingFuelPerStep);
			posX = averagePosX;
		} else {
			posX = medianPosX;
		}
		
		int fuel = 0;

		for(Integer crabPosX : crabsPositions) {
			
			Integer movements = Math.abs(crabPosX - posX);
			fuel += (increasingFuelPerStep == 0) ? movements : fuelConsumptionMap.get(movements);

		}
		
		return fuel;
	}

	private void calculateFuelConsumptionMap(final int increasingFuelPerStep) {
		
		int fuelConsumption = increasingFuelPerStep;
		
		fuelConsumptionMap.put(0, 0);
		
		int maxX = crabsPositions.get(crabsPositions.size()-1);
		for(int i = 1; i <= maxX; i++) {
			fuelConsumptionMap.put(i, fuelConsumptionMap.get(i-1)+fuelConsumption);
			fuelConsumption += increasingFuelPerStep;
		}
	}

}
