package com.adventofcode.flashk.day9;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.adventofcode.flashk.common.Vector2;

public class SmokeBasin {

	private Integer[][] heightMap;
	private List<Vector2> lowerPoints = new ArrayList<>();
	private int lowerPointsValue = 0;
	
	private int maxCols;
	private int maxRows;

	public SmokeBasin(List<String> inputs) {
		
		maxCols = inputs.get(0).length();
		maxRows = inputs.size();
		
		// Initialize heat map values
		heightMap = new Integer[maxRows][maxCols];
		
		for(int row = 0; row < inputs.size(); row++) {
			
			String[] numbers = inputs.get(row).split("|");
			
			for(int col = 0; col < maxCols; col++) {
				heightMap[row][col] = Integer.valueOf(numbers[col]);
			}

		}
		
		// Search lower points
		for(int row = 0; row < heightMap.length; row++) {
			for(int col = 0; col < heightMap[row].length; col++) {
				if(isLowerPoint(row, col)) {
					
					lowerPoints.add(new Vector2(row,col));					
					
					// Part A of the problem can be calculated now to avoid traversing the list again
					lowerPointsValue += heightMap[row][col] + 1;
					
				}
			}
		}
		
	}

	public int solveA() {
		return lowerPointsValue;
	}
	
	public int solveB() {
		
		List<Integer> basinSizes = new ArrayList<>();

		for(Vector2 lowerPoint : lowerPoints) { 
			basinSizes.add(searchBasin(lowerPoint.getX(), lowerPoint.getY(), new HashSet<>()));
		}
		
		List<Integer> orderedBasinSizes = basinSizes.stream().sorted(Comparator.reverseOrder()).limit(3).collect(Collectors.toList());
		
		int result = 1;
		for(Integer basinSize : orderedBasinSizes) {
			result *= basinSize;
		}
		return result;
		
	}
	
	private int searchBasin(int row, int col, Set<Vector2> visitedPositions) {
		
		if(isOutOfBounds(row,col)
			|| (heightMap[row][col] == 9) 
			|| (visitedPositions.contains(new Vector2(row,col)))) {
			return 0;
		}
		
		int result = 1;
		visitedPositions.add(new Vector2(row, col));
		
		result += searchBasin(row, col+1, visitedPositions); // Search right
		result += searchBasin(row, col-1, visitedPositions); // Search left
		result += searchBasin(row+1, col, visitedPositions); // Search down
		result += searchBasin(row-1, col, visitedPositions); // Search up
		
		return result;
		
	}
	
	private boolean isOutOfBounds(int row, int col) {
		return (col >= maxCols || col < 0) || (row >= maxRows || row < 0);
	}
	
	private boolean isLowerPoint(int row, int col) {
		
		int right = col+1;
		int left = col-1;
		int up = row-1;
		int down = row+1;
		
		if((right < maxCols) && (heightMap[row][col] >= heightMap[row][right])) {
			return false;
		}	
		
		if((left >= 0) && (heightMap[row][col] >= heightMap[row][left])) {
			return false;
		}	
		
		if((up >= 0) && (heightMap[row][col] >= heightMap[up][col])) {
			return false;
		}	
		
		return !((down < maxRows) && (heightMap[row][col] >= heightMap[down][col]));
		
	}

}
