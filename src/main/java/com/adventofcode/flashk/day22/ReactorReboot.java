package com.adventofcode.flashk.day22;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.adventofcode.flashk.common.Vector3;

public class ReactorReboot {

	private static final String INPUT_PATTERN = "(on|off) x=([-]?[0-9]*)..([-]?[0-9]*),y=([-]?[0-9]*)..([-]?[0-9]*),z=([-]?[0-9]*)..([-]?[0-9]*)";
	private static final Pattern REBOOT_PATTERN = Pattern.compile(INPUT_PATTERN);
	
	private Queue<RebootInstruction> instructions = new LinkedList<>();
	
	// TODO ojo, la estructura de array no vale: 
	// Con los datos de sample, esto da un total de: 102.108.134.953.200 elementos en el array
	// El máximo número de elementos es: 2,147,483,647
	
	//private boolean[][][] reactor;
	//private int minX, minY, minZ = Integer.MAX_VALUE;
	//private int maxX, maxY, maxZ = Integer.MIN_VALUE;
	private HashMap<Vector3, Boolean> cubes = new HashMap<>(2000000);	
	
	public ReactorReboot(List<String> inputs) {
		
		for(String input : inputs) {
			
			Matcher matcher = REBOOT_PATTERN.matcher(input);
			
			if(matcher.find()) {
				
				String action = matcher.group(1).toUpperCase();
				
				RebootInstruction rebootInstruction = RebootInstruction.builder()
						.action(RebootAction.valueOf(action))
						.x1(Integer.valueOf(matcher.group(2)))
						.x2(Integer.valueOf(matcher.group(3)))
						.y1(Integer.valueOf(matcher.group(4)))
						.y2(Integer.valueOf(matcher.group(5)))
						.z1(Integer.valueOf(matcher.group(6)))
						.z2(Integer.valueOf(matcher.group(7)))
						.build();
				
				// Add the cuboid if not already present
				for(int x = rebootInstruction.getMinX(); x <= rebootInstruction.getMaxX(); x++) {
					for(int y = rebootInstruction.getMinY(); y <= rebootInstruction.getMaxY(); y++) {
						for(int z = rebootInstruction.getMinZ(); z <= rebootInstruction.getMaxZ(); z++) {
							if(rebootInstruction.isInitializationCube()) {
								cubes.putIfAbsent(new Vector3(x,y,z), false);
							}
						}
					}
				}
				//minX = Math.min(minX, rebootInstruction.getMinX());
				//maxX = Math.max(maxX, rebootInstruction.getMaxX());
				//minY = Math.min(minY, rebootInstruction.getMinY());
				//maxY = Math.max(maxY, rebootInstruction.getMaxY());
				//minZ = Math.min(minZ, rebootInstruction.getMinZ());
				//maxZ = Math.max(maxZ, rebootInstruction.getMaxZ());
				
				instructions.add(rebootInstruction);
			}
		}
		
		//reactor = new boolean[maxX][maxY][maxZ];
	}
	
	public long solveA() {
		long result = 0;
		
		while(!instructions.isEmpty() && instructions.peek().isInitializationCube()) {
			
		}
		
		return result;
	}
	
}
