package com.adventofcode.flashk.day22;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.adventofcode.flashk.common.Vector3;

import lombok.Getter;

@Getter
public class Cuboid {

	private static final String CUBE_REGEX = "(on|off) x=([-]?[0-9]*)..([-]?[0-9]*),y=([-]?[0-9]*)..([-]?[0-9]*),z=([-]?[0-9]*)..([-]?[0-9]*)";
	private static final Pattern CUBE_PATTERN = Pattern.compile(CUBE_REGEX);
	private static final String ON = "on";
	
	private boolean on;
	private Vector3 start;
	private Vector3 end;
	
	public Cuboid(String input) {
		
		Matcher matcher = CUBE_PATTERN.matcher(input);
		matcher.find();
		
		String action = matcher.group(1);
		on = (ON.equals(action)) ? true: false;
	
		int x1 = Integer.valueOf(matcher.group(2));
		int x2 = Integer.valueOf(matcher.group(3));
		int y1 = Integer.valueOf(matcher.group(4));
		int y2 = Integer.valueOf(matcher.group(5));
		int z1 = Integer.valueOf(matcher.group(6));
		int z2 = Integer.valueOf(matcher.group(7));
		
		start = new Vector3(x1,y1,z1);
		end = new Vector3(x2,y2,z2);
	}
	
	private Cuboid() {
		
	}
	
	/**
	 * Intersects cube 2 over cube 1, generating a new cube.
	 * @param c1 cube to be intersected.
	 * @param c2 cube to intersect
	 * @return a new cube that represents the intersection of both cubes. Returns <code>null</code> if there is no intersection possible.
	 */
	public static Cuboid intersect(Cuboid c1, Cuboid c2) {
		
		// Idea from: https://observablehq.com/@jwolondon/advent-of-code-2021-day-22
		
		/*
		 *  Hay varios subproblemas a tratar según la solución de las intersecciones:
		 *  - En primer lugar, la generación de una intersección. La intersección entre dos cuboides genera otro cuboide.
		 *  - Por otro lado, el cálculo del volumen de un cubo. Esto nos permite saber cuantos cubos están iluminados en un cuboide:
		 *  	- En un cuboide apagado, el número de luces es 0.
		 *  	- En un cuboide encendido, el número de luces encendidas será las del volumen de dicho cubo.
		 *  	- Por ejemplo, en un cuboide como el cubo de rubik (3x3), su volumen es 3^3 = 27. 
		 *  		- Si el cubo está apagado, sus luces encendidas serán 0. 
		 *  		- Si está encendido, sus luces encendidas serán 27.
		 *  
		 */
		
		/*
		 * Given two cuboids: 
		 * 	a = [x1,y1,z1] -> [x2,y2,z2]
		 *  b = [x3,y3,z3] -> [x4,y4,z4]
		 *  
		 * Each cuboid has three segments, one per dimension:
		 * 
		 * X:
		 * - ax = (ax1,ax2)
		 * - bx = (bx1,bx2)
		 * 
		 * Y:
		 * - ay = (ay1,ay2)
		 * - by = (by1,by2)
		 * 
		 * Z:
		 * - az = (az1,az2)
		 * - bz = (bz1,bz2)
		 * 
		 * If on any single dimension the range does not match, then there is no intersection.
		 * If there is an intersection, to calculate it:
		 * 
		 * Given segment a and b the intersection is (max(a1,b1), min(a2,b2)).
		 * Therefore, we apply a very similar formula:
		 * - startX = max(ax1, bx1) 
		 * - endX = min(ax2, bx2)
		 * - startY = max(ay1, by1)
		 * ...
		 * And so on with each dimension.
		 * At the end, we well have an intersected cuboid.
		 */
		
		// No intersection between cubes
		if(	c1.getStart().getX() > c2.getEnd().getX() ||
			c1.getStart().getY() > c2.getEnd().getY() || 
			c1.getStart().getZ() > c2.getEnd().getZ() ||
			c1.getEnd().getX() < c2.getStart().getX() || 
			c1.getEnd().getY() < c2.getStart().getY() ||
			c1.getEnd().getZ() < c2.getStart().getZ()) {
			return null;
		}
		
		// Create a new cuboid
		Cuboid intersectionCuboid = new Cuboid();
		intersectionCuboid.on = c2.on;
		
		int xStart = Math.max(c1.getStart().getX(), c1.getStart().getX());
		int yStart = Math.max(c1.getStart().getY(), c1.getStart().getY());
		int zStart = Math.max(c1.getStart().getZ(), c1.getStart().getZ());
		
		int xEnd = Math.min(c1.getEnd().getX(), c1.getEnd().getX());
		int yEnd = Math.min(c1.getEnd().getY(), c1.getEnd().getY());
		int zEnd = Math.min(c1.getEnd().getZ(), c1.getEnd().getZ());
		
		intersectionCuboid.start = new Vector3(xStart, yStart, zStart);
		intersectionCuboid.end = new Vector3(xEnd, yEnd, zEnd);
		
		return intersectionCuboid;
	}
	
}
