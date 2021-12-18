package com.adventofcode.flashk.day15.dijkstra;

import com.adventofcode.flashk.common.Vector2;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Node implements Comparable<Node> {

	private Vector2 position;
	private Integer totalRisk = Integer.MAX_VALUE;
	private Integer risk;
	
	private Node parent = null;
	private boolean visited = false;
	
	public Node(Vector2 position) {
		this.position = position;
	}

	@Override
	public int compareTo(Node other) {
		return Integer.compare(totalRisk, other.totalRisk);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((position == null) ? 0 : position.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Node other = (Node) obj;
		if (position == null) {
			if (other.position != null)
				return false;
		} else if (!position.equals(other.position))
			return false;
		return true;
	}

	    
}
