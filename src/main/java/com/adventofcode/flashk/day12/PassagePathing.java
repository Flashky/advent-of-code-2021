package com.adventofcode.flashk.day12;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PassagePathing {

	private final static String SMALL_CAVE_PATTERN = "^(?!start|end)[a-z]*";
	private final static String INPUT_PATTERN = "(start|end|[\\w]*)-(start|end|[\\w]*)";
	private final static Pattern EDGE_PATTERN = Pattern.compile(INPUT_PATTERN);
	private final static String START_CAVE = "start";
	private final static String END_CAVE = "end";

	private Map<String,List<String>> caveAdjacency = new HashMap<>();

	private boolean hasVisitedSmallCaveTwice;
	private String visitedSmallCaveTwice = "";
	
	public PassagePathing(List<String> inputs) {
	
		for(String input : inputs) {
			Matcher matcher = EDGE_PATTERN.matcher(input);
			matcher.find();
			
			String origin = matcher.group(1);
			String destination = matcher.group(2);
			
			if(START_CAVE.equals(origin)) {
				
				// Start nodes can only be origin nodes: flip from destination to origin
				List<String> edges = getOrCreate(START_CAVE);
				edges.add(destination);
				
			} else if(START_CAVE.equals(destination)) {
				
				// Start nodes can only be origin nodes: flip from destination to origin
				
				List<String> edges = getOrCreate(START_CAVE);
				edges.add(origin);
				
			} else if(END_CAVE.equals(origin)) {
				
				// End nodes can only be destination nodes: flip from origin to destination
				List<String> edges = getOrCreate(destination);
				edges.add(END_CAVE);
				
			} else if (END_CAVE.equals(destination)) {
				
				// End nodes can only be destination nodes: flip from origin to destination
				List<String> edges = getOrCreate(origin);
				edges.add(END_CAVE);
				
			} else {
				
				// Any cave to any other cave different than 'start' or 'end':
				// Add bidirectional connection
				
				List<String> edges = getOrCreate(origin);
				edges.add(destination);

				edges = getOrCreate(destination);
				edges.add(origin);
				
			}
			
		}
		
	}
	
	public int solveA() {
		
		hasVisitedSmallCaveTwice = true;

		List<String> currentPath = new ArrayList<>();
		currentPath.add(START_CAVE);
		
		return findPaths(START_CAVE, currentPath, new HashSet<>());

	}
	
	
	public int solveB() {
		
		hasVisitedSmallCaveTwice = false;
		
		List<String> currentPath = new ArrayList<>();
		currentPath.add(START_CAVE);
		
		return findPaths(START_CAVE, currentPath, new HashSet<>());
		
	}
	
	private int findPaths(String currentCave, List<String> currentPath, Set<String> visitedSmallCaves) {
		
		if(END_CAVE.equals(currentCave)) {
			return 1;
		} else if(isSmallCave(currentCave) && visitedSmallCaves.contains(currentCave) && hasVisitedSmallCaveTwice) {
			return 0; // Invalid path - Already visited small cave twice at least once
		} else {
		
			int paths = 0;
		
			currentPath.add(currentCave);
			
			if(isSmallCave(currentCave)) {
				
				if(visitedSmallCaves.contains(currentCave)) {
					hasVisitedSmallCaveTwice = true;
					visitedSmallCaveTwice = currentCave;
				} else {
					visitedSmallCaves.add(currentCave);
				}
			}
			
			List<String> adjacentCaves = caveAdjacency.get(currentCave);
			
			for(String cave : adjacentCaves) {
				paths += findPaths(cave, currentPath, visitedSmallCaves);
			}
			
			if(isSmallCave(currentCave)) {
				if(currentCave.equals(visitedSmallCaveTwice)) {
					hasVisitedSmallCaveTwice = false;
					visitedSmallCaveTwice = "";
				} else {
					visitedSmallCaves.remove(currentCave);
				}
			}
			
			return paths;
		}
		
	}

	
	private boolean isSmallCave(String cave) {
		return cave.matches(SMALL_CAVE_PATTERN);
	}
	
	private List<String> getOrCreate(String origin) {
		
		List<String> edges = caveAdjacency.get(origin);
		
		if(edges == null) {
			edges = new ArrayList<>();
			caveAdjacency.put(origin, edges);
		}
		
		return edges;
	}
}
