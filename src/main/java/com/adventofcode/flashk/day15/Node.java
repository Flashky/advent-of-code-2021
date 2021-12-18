package com.adventofcode.flashk.day15;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
public class Node implements Comparable<Node> {

	private final Integer x;
	private final Integer y;

	@Setter
	private Integer totalRisk = Integer.MAX_VALUE;
	
	@Setter
	private Integer risk;
	
	@Setter
	private Node parent = null;
	
	@Setter
	private boolean visited = false;
	
	public Node(Integer x, Integer y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public int compareTo(Node other) {
		return Integer.compare(totalRisk, other.totalRisk);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((x == null) ? 0 : x.hashCode());
		result = prime * result + ((y == null) ? 0 : y.hashCode());
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
		if (x == null) {
			if (other.x != null)
				return false;
		} else if (!x.equals(other.x))
			return false;
		if (y == null) {
			if (other.y != null)
				return false;
		} else if (!y.equals(other.y))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Node [x=" + x + ", y=" + y + ", totalRisk=" + totalRisk + ", risk=" + risk + ", parent=" + parent
				+ ", visited=" + visited + "]";
	}
	
	



	    
}
