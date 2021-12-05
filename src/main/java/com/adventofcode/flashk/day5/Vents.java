package com.adventofcode.flashk.day5;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Vents {

	private final static String COORDINATES_PATTERN = "([0-9]*)[,]([0-9]*)";
	private final static Pattern PATTERN = Pattern.compile(COORDINATES_PATTERN);
	
	private Integer[][] ventCount;
	private List<Vent> vents = new ArrayList<>();
	
	public Vents(List<String> inputs) {
		
		int maxX = 0;
		int maxY = 0;
		
		for(String input : inputs) {
			
			Matcher matcher = PATTERN.matcher(input);
			matcher.find();
			
			Integer x1 = Integer.valueOf(matcher.group(1));
			Integer y1 = Integer.valueOf(matcher.group(2));
			
			matcher.find();
			Integer x2 = Integer.valueOf(matcher.group(1));
			Integer y2 = Integer.valueOf(matcher.group(2));
			
			// Update max x and y to define array limits
			if(x1 > maxX) {
				maxX = x1;
			}
			
			if(x2 > maxX) {
				maxX = x2;
			}
			
			if(y1 > maxY) {
				maxY = y1;
			}
			
			if(y2 > maxX) {
				maxY = y2;
			}
			
			// Create data about the vent
			Position startPos = new Position(x1,y1);
			Position endPos = new Position(x2, y2);
			vents.add(new Vent(startPos, endPos));
			
		}
		
		// Initialize counters
		ventCount = new Integer[maxX+1][maxY+1];
		for(int i = 0; i < ventCount.length; i++) {
			for(int j = 0; j < ventCount[i].length; j++) {
				ventCount[i][j] = 0;
			}
		}
	}
	
	
	public int solvePart1() {
		
		for(Vent vent : vents) {
			
			// Traverse all segments and check where it is passing
			
			if(vent.getStartPos().getY() == vent.getEndPos().getY()) {
				
				// Horizontal vent
				int y = vent.getStartPos().getY();
				
				if(vent.getStartPos().getX() < vent.getEndPos().getX()) {
					
					// Left to right
					for(int i = vent.getStartPos().getX(); i <= vent.getEndPos().getX(); i++) {
						ventCount[i][y]++;
					}
					
				} else if(vent.getStartPos().getX() > vent.getEndPos().getX()) {
					
					// Right to left
					for(int i = vent.getStartPos().getX(); i >= vent.getEndPos().getX(); i--) {
						ventCount[i][y]++;
					}
				}
			}
			
			if(vent.getStartPos().getX() == vent.getEndPos().getX()) {
				
				// Vertical vent
				int x = vent.getStartPos().getX();
				
				if(vent.getStartPos().getY() < vent.getEndPos().getY()) {
					
					// Bottom to top
					for(int j = vent.getStartPos().getY(); j <= vent.getEndPos().getY(); j++) { 
						ventCount[x][j]++;
					}
				} else if(vent.getStartPos().getY() > vent.getEndPos().getY()) {
					
					// Top to bottom
					for(int j = vent.getStartPos().getY(); j >= vent.getEndPos().getY(); j--) {
						ventCount[x][j]++;
					}
				}
			}
			 
			
		}
		
		int overlapCount = 0;
		
		for(int i = 0; i < ventCount.length; i++) {
			for(int j = 0; j < ventCount[i].length; j++) {
				if((ventCount[i][j] != null) && (ventCount[i][j] >= 2)) {
					overlapCount++;
				}
			}
		}
		return overlapCount;
		
	}
	
	public int solvePart2() {
		
		for(Vent vent : vents) {
			
			Position currentPosition = vent.getStartPos();
			ventCount[currentPosition.getX()][currentPosition.getY()]++;
			
			while(!currentPosition.equals(vent.getEndPos())) {
				currentPosition.transform(vent.getDirection());
				ventCount[currentPosition.getX()][currentPosition.getY()]++;
			}
		}
		
		int overlapCount = 0;
		
		for(int i = 0; i < ventCount.length; i++) {
			for(int j = 0; j < ventCount[i].length; j++) {
				if((ventCount[i][j] != null) && (ventCount[i][j] >= 2)) {
					overlapCount++;
				}
			}
		}
		return overlapCount;
	}
	

	
}
