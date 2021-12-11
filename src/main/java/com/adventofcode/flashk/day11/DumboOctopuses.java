package com.adventofcode.flashk.day11;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import com.adventofcode.flashk.common.Vector2;

public class DumboOctopuses {

	private final static int FLASH_SATURATION = 9;
	
	private Integer[][] octopusesEnergies;

	private int maxRows;
	private int maxCols;
	
	public DumboOctopuses(List<String> inputs) {
		
		maxRows = inputs.size();
		maxCols = inputs.get(0).length();
		octopusesEnergies = new Integer[maxRows][maxCols];
		
		for(int row = 0; row < inputs.size(); row++) {
			
			String[] numbers = inputs.get(row).split("|");
			
			for(int col = 0; col < maxCols; col++) {
				octopusesEnergies[row][col] = Integer.valueOf(numbers[col]);
			}

		}
	}
	

	public int solveA() {
		
		int result = 0;
		
		for(int i = 0; i < 100; i++) {
			
			Queue<Vector2> flashingOctopuses = new LinkedList<>();
			Set<Vector2> visitedOctopus = new HashSet<>();
			
			for(int row = 0; row < maxRows; row++) {
				for(int col = 0; col < maxCols; col++) {
					
					Integer energy = ++octopusesEnergies[row][col];
					
					if(energy > FLASH_SATURATION) {
						Vector2 pos = new Vector2(row,col);
						flashingOctopuses.add(pos);
						visitedOctopus.add(pos);
					}
					
					
				}
			}
			
			while(!flashingOctopuses.isEmpty()) {
				
				Vector2 octopusPos = flashingOctopuses.poll();		
				octopusesEnergies[octopusPos.getX()][octopusPos.getY()] = 0;
				
				result++;
				
				// Check adjacents
				List<Vector2> adjacents = getAdjacents(octopusPos.getX(), octopusPos.getY());
					
				for(Vector2 adjacentPos : adjacents) {

					if(!visitedOctopus.contains(adjacentPos)) {
						
						// En realidad debería sumar 1 a todos los adyacentes
						Integer energy = ++octopusesEnergies[adjacentPos.getX()][adjacentPos.getY()];
						
						if(energy > FLASH_SATURATION) {
							flashingOctopuses.add(adjacentPos);
							visitedOctopus.add(adjacentPos);
						}
					}
				}
				
				
			}

		}
		
		return result;	
	}

	
	public Long solveB() {
		
		Long result = 0L;
		
		for(Long i = 0L; i < Long.MAX_VALUE; i++) {
			
			Queue<Vector2> flashingOctopuses = new LinkedList<>();
			Set<Vector2> visitedOctopus = new HashSet<>();
			
			for(int row = 0; row < maxRows; row++) {
				for(int col = 0; col < maxCols; col++) {
					
					Integer energy = ++octopusesEnergies[row][col];
					
					if(energy > FLASH_SATURATION) {
						Vector2 pos = new Vector2(row,col);
						flashingOctopuses.add(pos);
						visitedOctopus.add(pos);
					}
					
					
				}
			}
			
			while(!flashingOctopuses.isEmpty()) {
				
				Vector2 octopusPos = flashingOctopuses.poll();		
				octopusesEnergies[octopusPos.getX()][octopusPos.getY()] = 0;
				
				result++;
				
				// Check adjacents
				List<Vector2> adjacents = getAdjacents(octopusPos.getX(), octopusPos.getY());
					
				for(Vector2 adjacentPos : adjacents) {

					if(!visitedOctopus.contains(adjacentPos)) {
						
						// En realidad debería sumar 1 a todos los adyacentes
						Integer energy = ++octopusesEnergies[adjacentPos.getX()][adjacentPos.getY()];
						
						if(energy > FLASH_SATURATION) {
							flashingOctopuses.add(adjacentPos);
							visitedOctopus.add(adjacentPos);
						}
					}
				}
				
				
			}
			
			if(isFlashingSimultaneously()) {
				return i+1;
			}

		}
		
		return -1L;
		
	}
	
	private boolean isFlashingSimultaneously() {
		
		for(int row = 0; row < maxRows; row++) {
			for(int col = 0; col < maxCols; col++) {

				if(octopusesEnergies[row][col] != 0) {
					return false;
				}
				
				
			}
		}
		
		return true;
		
	}
	
	private List<Vector2> getAdjacents(int row, int col) {

		List<Vector2> adjacents = new LinkedList<>();
		
		int right = col+1;
		int left = col-1;
		int up = row-1;
		int down = row+1;
		
		if(right < maxCols) {
			
			adjacents.add(new Vector2(row,right)); // Right 
			
			if(up >= 0) {
				adjacents.add(new Vector2(up,right)); // Top right diagonal
			}
			
			if(down < maxRows) {
				adjacents.add(new Vector2(down,right)); // Bottom right diagonal
			}
		}
		
		if(left >= 0) {
			adjacents.add(new Vector2(row,left)); // Left
			
			if(up >= 0) {
				adjacents.add(new Vector2(up,left)); // Top left diagonal
			}
			
			if(down < maxRows) {
				adjacents.add(new Vector2(down,left)); // Bottom left diagonal
			}
		}
		
		
		if(up >= 0) {
			adjacents.add(new Vector2(up,col)); // Top
		}
		
		if(down < maxRows) {
			adjacents.add(new Vector2(down,col)); // Bottom
		}
		
		return adjacents;
	}
}
