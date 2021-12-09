package com.adventofcode.flashk.day9;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.adventofcode.flashk.day5.Position;

public class SmokeBasin {

	private Integer[][] heightMap;
	
	private int maxCols;
	private int maxRows;
	
	public SmokeBasin(List<String> inputs) {
		
		maxCols = inputs.get(0).length();
		maxRows = inputs.size();
		
		heightMap = new Integer[maxRows][maxCols];
		
		int row = 0;
		for(String input : inputs) {
				
			// Obtain row numbers
			String[] numbers = input.split("|");
			
			for(int col = 0; col < maxCols; col++) {
				heightMap[row][col] = Integer.valueOf(numbers[col]);
			}
			
			row++;
		}
	}

	public int solveA() {
		
		int result = 0;
		
		for(int row = 0; row < heightMap.length; row++) {
			for(int col = 0; col < heightMap[row].length; col++) {
				
				int lowerPointValue = calculateLowerPointValue(row,col);
				if(lowerPointValue != -1) {
					result += lowerPointValue;
				}
			}
		}
		
		return result;
	}
	
	public int solveB() {
		
		
		List<Integer> basinSizes = new ArrayList<>();
		
		for(int row = 0; row < heightMap.length; row++) {
			for(int col = 0; col < heightMap[row].length; col++) {
				
				int lowerPointValue = calculateLowerPointValue(row,col);
				if(lowerPointValue != -1) {
					
					Set<Position> visitedPositions = new HashSet<>();
					visitedPositions.add(new Position(row, col));
					
					int result = 1;
					result += searchBasin(row, col+1, visitedPositions); // Search right
					result += searchBasin(row, col-1, visitedPositions); // Search left
					result += searchBasin(row+1, col, visitedPositions); // Search down
					result += searchBasin(row-1, col, visitedPositions); // Search up
					
					basinSizes.add(result);
					
				}
			}
		}
		
		List<Integer> orderedBasinSizes = basinSizes.stream().sorted(Comparator.reverseOrder()).limit(3).collect(Collectors.toList());
		
		int result = 1;
		for(Integer basinSize : orderedBasinSizes) {
			result *= basinSize;
		}
		return result;
	}
	
	private int searchBasin(int row, int col, Set<Position> visitedPositions) {
		
		if(col >= maxCols || col < 0) {
			return 0;
		}
		
		if(row >= maxRows || row < 0) {
			return 0;
		}
		
		if(heightMap[row][col] == 9) {
			return 0;
		}
		
		if(visitedPositions.contains(new Position(row,col))) {
			return 0;
		}
		
		int result = 1;
		visitedPositions.add(new Position(row, col));
		
		result += searchBasin(row, col+1, visitedPositions); // Search right
		result += searchBasin(row, col-1, visitedPositions); // Search left
		result += searchBasin(row+1, col, visitedPositions); // Search down
		result += searchBasin(row-1, col, visitedPositions); // Search up
		
		return result;
		
	}
	/**
	 * Calculates if the specified point is a lower point and its value
	 * @param col
	 * @param row
	 * @return the value of the lower point. Returns <code>-1</code` if the point is not a lower value.
	 */
	private int calculateLowerPointValue(int row, int col) {
		
		int right = col+1;
		int left = col-1;
		int up = row-1;
		int down = row+1;
		
		if((right < maxCols) && (heightMap[row][col] >= heightMap[row][right])) {
			return -1;
		}	
		
		if((left >= 0) && (heightMap[row][col] >= heightMap[row][left])) {
			return -1;
		}	
		
		if((up >= 0) && (heightMap[row][col] >= heightMap[up][col])) {
			return -1;
		}	
		
		if((down < maxRows) && (heightMap[row][col] >= heightMap[down][col])) {
			return -1;
		}	
		
		return heightMap[row][col] + 1;
		
	}
	
	 
	
	

}
