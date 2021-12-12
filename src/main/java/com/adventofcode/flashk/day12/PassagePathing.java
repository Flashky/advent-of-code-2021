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

	private final static String SMALL_CAVE_PATTERN = "[a-z]*";
	private final static String INPUT_PATTERN = "(start|end|[\\w]*)-(start|end|[\\w]*)";
	private final static Pattern PATTERN = Pattern.compile(INPUT_PATTERN);
	private final static String START_CAVE = "start";
	private final static String END_CAVE = "end";

	private Map<String,List<String>> caveAdjacency = new HashMap<>();

	private boolean hasVisitedSmallCaveTwice;
	private String visitedSmallCaveTwice = "";
	
	public PassagePathing(List<String> inputs) {
	
		for(String input : inputs) {
			
			Matcher matcher = PATTERN.matcher(input);
			matcher.find();
			
			String origin = matcher.group(1);
			String destination = matcher.group(2);
			
			if(!START_CAVE.equals(destination)) {
				createEdge(origin, destination);
			}
			
			if(!START_CAVE.equals(origin)) {
				createEdge(destination, origin);
			}
		
		}
	}
	
	public int solve(boolean allowVisitSmallCaveTwice) {
		
		hasVisitedSmallCaveTwice = !allowVisitSmallCaveTwice;
		visitedSmallCaveTwice = "";
		
		return findPaths(START_CAVE, new ArrayList<>(), new HashSet<>());

	}
	
	private int findPaths(String currentCave, List<String> currentPath, Set<String> visitedSmallCaves) {
		
		if(END_CAVE.equals(currentCave)) {
			return 1;
		} else if(isSmallCave(currentCave) && visitedSmallCaves.contains(currentCave) && hasVisitedSmallCaveTwice) {
			return 0; // Invalid path - Already visited small cave twice at least once
		}
		
		int paths = 0;
		currentPath.add(currentCave);
		markCaveAsVisited(currentCave, visitedSmallCaves);
		
		for(String adjacentCave : caveAdjacency.get(currentCave)) {
			paths += findPaths(adjacentCave, currentPath, visitedSmallCaves);
		}
		
		unmarkCaveAsVisited(currentCave, visitedSmallCaves);
		
		return paths;
	}

	private void markCaveAsVisited(String currentCave, Set<String> visitedSmallCaves) {
	
		// Only mark small caves as visited
		if(isSmallCave(currentCave)) {
			if(visitedSmallCaves.contains(currentCave)) {
				hasVisitedSmallCaveTwice = true;
				visitedSmallCaveTwice = currentCave;
			} else {
				visitedSmallCaves.add(currentCave);
			}
		}
	}
	

	private void unmarkCaveAsVisited(String currentCave, Set<String> visitedSmallCaves) {
		
		if(currentCave.equals(visitedSmallCaveTwice)) {
			hasVisitedSmallCaveTwice = false;
			visitedSmallCaveTwice = "";
		} else {
			visitedSmallCaves.remove(currentCave);
		}
	
	}


	
	private boolean isSmallCave(String cave) {
		return cave.matches(SMALL_CAVE_PATTERN);
	}
	
	private void createEdge(String origin, String destination) {
		
		List<String> edges = caveAdjacency.getOrDefault(origin, new ArrayList<>());
		edges.add(destination);

		if(!caveAdjacency.containsKey(origin)) {
			caveAdjacency.put(origin, edges);
		}
		
	}
}
