package com.adventofcode.flashk.day5;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.adventofcode.flashk.common.Vector2;

public class HydrothermalVenture {

	private final static String COORDINATES_PATTERN = "([0-9]*),([0-9]*) -> ([0-9]*),([0-9]*)";
	private final static Pattern PATTERN = Pattern.compile(COORDINATES_PATTERN);
	private final static Integer OVERLAPPING_SATURATION = 2;
	
	private int[][] heatMap;
	private List<Vent> vents = new ArrayList<>();
	private int totalOverlapCount = 0;
	
	public HydrothermalVenture(List<String> inputs) {
		
		int maxX = 0;
		int maxY = 0;
		
		for(String input : inputs) {
			
			Matcher matcher = PATTERN.matcher(input);
			matcher.find();
			
			Integer x1 = Integer.valueOf(matcher.group(1));
			Integer y1 = Integer.valueOf(matcher.group(2));
			Integer x2 = Integer.valueOf(matcher.group(3));
			Integer y2 = Integer.valueOf(matcher.group(4));
			
			// Update max x and y to define array limits
			maxX = Math.max(x1, Math.max(x2, maxX));
			maxY = Math.max(y1, Math.max(y2, maxY));

			// Add vent data
			Vector2 startPos = new Vector2(x1,y1);
			Vector2 endPos = new Vector2(x2, y2);
			vents.add(new Vent(startPos, endPos));
			
		}
		
		// Initialize overlapping map
		heatMap = new int[maxX+1][maxY+1];
		
	}
	
	public int solve(boolean includeDiagonals) {
		
		totalOverlapCount = 0;
		
		if(includeDiagonals) {
			vents.stream().forEach(this::calculateOverlaps);
		} else {
			vents.stream().filter(vent -> !vent.getDirection().isDiagonal()).forEach(this::calculateOverlaps);
		}
		
		return totalOverlapCount;
		
	}
	
	private void calculateOverlaps(Vent vent) {
		
		boolean traversed = false;
		Vector2 currentPos = vent.getStartPos();
		
		do {
			
			int currentHeat = ++heatMap[currentPos.getX()][currentPos.getY()];
			
			if(currentHeat == OVERLAPPING_SATURATION) {
				totalOverlapCount++;
			}
			
			if(currentPos.equals(vent.getEndPos())) {
				traversed = true;
			} else {
				currentPos.transform(vent.getDirection());
			}
			
		} while(!traversed);
	}
	
}
