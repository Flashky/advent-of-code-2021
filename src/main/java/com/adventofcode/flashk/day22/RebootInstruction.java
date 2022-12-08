package com.adventofcode.flashk.day22;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lombok.Getter;


@Getter
public class RebootInstruction {

	private static final String INSTRUCTION_REGEX = "(on|off) x=([-]?[0-9]*)..([-]?[0-9]*),y=([-]?[0-9]*)..([-]?[0-9]*),z=([-]?[0-9]*)..([-]?[0-9]*)";
	private static final Pattern REBOOT_PATTERN = Pattern.compile(INSTRUCTION_REGEX);
	
	private static final int INITIALIZATION_CUBE_MIN = -50;
	private static final int INITIALIZATION_CUBE_MAX = 50;
	
	public static final String ON = "on";
	public static final String OFF = "off";
	
	private String action;
	
	private boolean on;
	private int x1;
	private int x2;
	private int y1;
	private int y2;
	private int z1;
	private int z2;
	
	public RebootInstruction(String input) {
		
		Matcher matcher = REBOOT_PATTERN.matcher(input);
		matcher.find();
		action = matcher.group(1);
		on = (ON.equals(action)) ? true: false;
		x1 = Integer.valueOf(matcher.group(2));
		x2 = Integer.valueOf(matcher.group(3));
		y1 = Integer.valueOf(matcher.group(4));
		y2 = Integer.valueOf(matcher.group(5));
		z1 = Integer.valueOf(matcher.group(6));
		z2 = Integer.valueOf(matcher.group(7));
		
	}
	
	/*
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
	*/
	/**
	 * Indicates if the reboot instruction coordinates are located in the range of initilization cubes.
	 * <p>Initialization cubes have x,y and z positions at least -50 and at most 50.</p>
	 * @return true if it is inside the -50-50 range. false in other case.
	 */
	/*
	public boolean isInitializationCube() {
		
		if((getMinX() < INITIALIZATION_CUBE_MIN) || (getMaxX() > INITIALIZATION_CUBE_MAX)){
			return false;
		}
		
		if((getMinY() < INITIALIZATION_CUBE_MIN) || (getMaxY() > INITIALIZATION_CUBE_MAX)){
			return false;
		}

		return (getMinZ() >= INITIALIZATION_CUBE_MIN) || (getMaxY() <= INITIALIZATION_CUBE_MAX);
	}
	*/
}
