package com.adventofcode.flashk.day22;

import lombok.Builder;
import lombok.Getter;


@Builder
public class RebootInstruction {

	private static final int INITIALIZATION_CUBE_MIN = -50;
	private static final int INITIALIZATION_CUBE_MAX = 50;
	
	@Getter
	private RebootAction action;
	
	private int x1;
	private int x2;
	private int y1;
	private int y2;
	private int z1;
	private int z2;
	
	public int getMinX() {
		return Math.min(x1, x2);
	}
	
	public int getMaxX() {
		return Math.max(x1, x2);
	}
	
	public int getMinY() {
		return Math.min(y1, y2);
	}
	
	public int getMaxY() {
		return Math.max(y1, y2);
	}
	public int getMinZ() {
		return Math.min(z1, z2);
	}
	
	public int getMaxZ() {
		return Math.max(z1, z2);
	}
	
	/**
	 * Indicates if the reboot instruction coordinates are located in the range of initilization cubes.
	 * <p>Initialization cubes have x,y and z positions at least -50 and at most 50.</p>
	 * @return true if it is inside the -50-50 range. false in other case.
	 */
	public boolean isInitializationCube() {
		
		if((getMinX() < INITIALIZATION_CUBE_MIN) || (getMaxX() > INITIALIZATION_CUBE_MAX)){
			return false;
		}
		
		if((getMinY() < INITIALIZATION_CUBE_MIN) || (getMaxY() > INITIALIZATION_CUBE_MAX)){
			return false;
		}

		return (getMinZ() >= INITIALIZATION_CUBE_MIN) || (getMaxY() <= INITIALIZATION_CUBE_MAX);
	}
}
