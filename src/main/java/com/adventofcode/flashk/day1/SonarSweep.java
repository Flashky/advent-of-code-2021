package com.adventofcode.flashk.day1;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class SonarSweep {
	
	/**
	 * Part 1 problem.
	 * @param depthMeasurements
	 * @return
	 */
	public int solve(List<Integer> depthMeasurements) {
		
		if(depthMeasurements.size() < 2) {
			return 0;
		}
		
		int depthIncrements = 0;
		
		Integer previousMeasurement = depthMeasurements.get(0);
		
		for(int i = 1; i < depthMeasurements.size(); i++) {
			
			Integer currentMeasurement = depthMeasurements.get(i);
			
			if(currentMeasurement > previousMeasurement) {
				depthIncrements++;
			}
			
			previousMeasurement = currentMeasurement;
		}
		
		return depthIncrements;
		
	}
	
	/**
	 * Part 2 problem.
	 * <p>Solves using the specified window size to reduce noise</p>
	 * @param depthMeasurements
	 * @param windowSize
	 * @return
	 */
	public int solve(List<Integer> depthMeasurements, int windowSize) {
		
		int depthIncrements = 0;
		int currentWindowMeasurement = 0;
		int previousWindowMeasurement = 0;
		
		Queue<Integer> currentWindow = new LinkedList<>();
		
		for(Integer measurement : depthMeasurements) {
			
			currentWindowMeasurement += measurement;
			currentWindow.add(measurement);
			
			if(currentWindow.size() == windowSize) {			
				
				if((currentWindowMeasurement > previousWindowMeasurement) && (previousWindowMeasurement != 0)) {
					depthIncrements++;
				}
				
				previousWindowMeasurement = currentWindowMeasurement;
				currentWindowMeasurement -= currentWindow.poll();
			}
			
		}
		
		return depthIncrements;
	}
}
