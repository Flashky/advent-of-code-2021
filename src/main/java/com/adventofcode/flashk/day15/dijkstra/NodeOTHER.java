package com.adventofcode.flashk.day15.dijkstra;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

// https://github.com/guavabot/design-algorithms-1/blob/master/src/com/guavabot/algo1/Dijkstra.java
@Getter
@Setter
public class NodeOTHER implements Comparable<NodeOTHER> {

	private boolean visited = false;
	private Integer shortestDistance = Integer.MAX_VALUE;
	private List<EdgeOTHER> edges = new ArrayList<>();
	
	@Override
	public int compareTo(NodeOTHER o) {
		// TODO Auto-generated method stub
	
		return 0;
	}

}
