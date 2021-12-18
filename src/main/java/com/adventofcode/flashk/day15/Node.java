package com.adventofcode.flashk.day15;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Node implements Comparable<Node> {

	private final Integer x;
	private final Integer y;
	
	private Integer totalRisk = Integer.MAX_VALUE;
	private Integer risk;
	private Node parent = null;
	private boolean visited = false;

	public Node(Integer x, Integer y, Integer risk) {
		this.x = x;
		this.y = y;
		this.risk = risk;
	}
	
	@Override
	public int compareTo(Node other) {
		return Integer.compare(totalRisk, other.totalRisk);
	}
	
	/*
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((x == null) ? 0 : x.hashCode());
		result = prime * result + ((y == null) ? 0 : y.hashCode());
		return result;
	}*/

}
