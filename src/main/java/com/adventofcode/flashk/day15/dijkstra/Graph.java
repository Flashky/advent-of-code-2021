package com.adventofcode.flashk.day15.dijkstra;

import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Graph {

	private Set<NodeOTHER> nodes = new HashSet<>();
	
	public void addNode(NodeOTHER node) {
		nodes.add(node);
	}
}
