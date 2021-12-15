package com.adventofcode.flashk.day15;

import com.adventofcode.flashk.common.Vector2;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Cell implements Comparable<Cell>{

	// f(v) = g(v) + h(v)
	private Integer fScore = Integer.MAX_VALUE;
	private Integer gScore = Integer.MAX_VALUE;
	private Integer hScore;
	private Vector2 position;
	
	public Cell(Vector2 position) {
		this.position = position;
	}

	@Override
	public int compareTo(Cell cell) {
		return Integer.compare(this.fScore, cell.fScore);
	}

	public Integer calculateHeuristic(Vector2 destination) {
		
		int x = destination.getX() - position.getX();
		int y = destination.getY() - position.getY();
		hScore = Math.abs(x+y);
		
		return hScore;
	}
}
