package com.adventofcode.flashk.day17;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.adventofcode.flashk.common.MathUtil;
import com.adventofcode.flashk.common.Vector2;

public class TrickShot {

	private final static Pattern PATTERN_COORDINATES = Pattern.compile("x=([-]?[0-9]*)..([-]?[0-9]*), y=([-]?[0-9]*)..([-]?[0-9]*)");
	
	// Acceleration is uniform on this problem and there are two possible cases:
	//- If the probe has horizontal speed, it's acceleration evenly reduces both in x-axis and y-axis, simulating a ballistic acceleration.
	//- If the probe doesn't have horizontal speed (vx = 0), it is on free fall.
	
	private static final Vector2 BALLISTIC_ACCELERATION = new Vector2(-1, -1);
	private static final Vector2 FREE_FALL_ACCELERATION = new Vector2(0, -1);
	
	// The following coordinates determine the target area:
	private int minX;
	private int maxX;
	private int minY;
	private int maxY;
	
	// Initial position of the submarine / probe
	private Vector2 initialPos = new Vector2(0,0);
	private Vector2 probePosition = initialPos;
	
	private int minVx;
	private int maxVx;
	
	public TrickShot(String input) {
	
		Matcher matcher = PATTERN_COORDINATES.matcher(input);
		
		if(matcher.find()) {
			minX = Integer.valueOf(matcher.group(1));
			maxX = Integer.valueOf(matcher.group(2));
			minY = Integer.valueOf(matcher.group(3));
			maxY = Integer.valueOf(matcher.group(4));
		}
		
		// Calculate min v(x)
		boolean foundMinVx = false;
		minVx = 1;
		
		while(!foundMinVx) {
			int maxXDistance = (int) MathUtil.summation(minVx);
			if(maxXDistance >= minX) {
				foundMinVx = true;
			} else {
				minVx++;
			}
		}
		
		// Calculate max v(x)
		maxVx = maxX;
		
		System.out.println("finished");
	}
	
	public int solveA() {
		
		int maxHeight = probePosition.getY();
		
		for(int vX = minVx; vX <= maxVx; vX++) {
			for(int vY = 0; vY <= 1000; vY++) {
				
				// Test one speed at a time
				Vector2 speed = new Vector2(vX, vY);
				int currentMaxHeight = Integer.MIN_VALUE;
				boolean isOptimal = true;
				probePosition = new Vector2(0,0);
				
				while(isFactible(probePosition) 
						&& !isSolution(probePosition) 
						&& isOptimal) {
					
					// Apply movement
					probePosition.transform(speed);
					
					if(speed.getY() == 0) {
						if(probePosition.getY() > maxHeight) {
							currentMaxHeight = probePosition.getY();
						} else {
							isOptimal = false;
						}
					} 
					
					// Update speed, applying the horizontal drag and vertical gravity.
					if(speed.getX() > 0) {
						speed.transform(BALLISTIC_ACCELERATION);
					} else {
						speed.transform(FREE_FALL_ACCELERATION);
					}
				}
				
				if(isSolution(probePosition) && isOptimal) {
					maxHeight = currentMaxHeight;
				}
			}
		}
		
		System.out.println(maxHeight);
		return maxHeight;
		
	}
	
	public int solveB() {
		
		int validSolutions = 0;
		
		for(int vX = minVx; vX <= maxVx; vX++) {
			for(int vY = minY; vY <= 1000; vY++) {
				
				// Test one speed at a time
				Vector2 speed = new Vector2(vX, vY);
				probePosition = new Vector2(0,0);
				
				while(isFactible(probePosition) 
						&& !isSolution(probePosition)) {
					
					// Apply movement
					probePosition.transform(speed);
					
					// Update speed, applying the horizontal drag and vertical gravity.
					if(speed.getX() > 0) {
						speed.transform(BALLISTIC_ACCELERATION);
					} else {
						speed.transform(FREE_FALL_ACCELERATION);
					}
				}
				
				if(isSolution(probePosition)) {
					validSolutions++;
				}
			}
		}
		
		return validSolutions;
		
	}

	private boolean isFactible(Vector2 probePosition) {
		return probePosition.getX() <= maxX && probePosition.getY() >= minY;
	}
	
	private boolean isSolution(Vector2 probePosition) {	
		return isInXRange(probePosition.getX()) && isInYRange(probePosition.getY());
	}

	private boolean isInXRange(int x) {
		return (x >= minX && x <= maxX);
	}
	
	private boolean isInYRange(int y) {
		return (y >= minY && y <= maxY);
	}
}
