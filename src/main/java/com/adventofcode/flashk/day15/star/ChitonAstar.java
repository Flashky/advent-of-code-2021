package com.adventofcode.flashk.day15.star;

import java.util.List;

import com.adventofcode.flashk.common.Vector2;

public class ChitonAstar {
	
	private Integer[][] riskMap;
	private int maxCols;
	private int maxRows;
	
	public ChitonAstar(List<String> inputs) {
		
		maxCols = inputs.get(0).length();
		maxRows = inputs.size();

		// Initialize heat map values
		riskMap = new Integer[maxRows][maxCols];
		
		Vector2 destination = new Vector2(maxCols-1, maxRows-1);
		
		for(int row = 0; row < inputs.size(); row++) {
			
			String[] numbers = inputs.get(row).split("|");
			
			for(int col = 0; col < maxCols; col++) {
				riskMap[row][col] = Integer.valueOf(numbers[col]);
				
				Node node = new Node(destination);
			}
			
			
			

		}
		
		// Set the destination
		destination = new Vector2(maxCols-1, maxRows-1);
		
	}
}
