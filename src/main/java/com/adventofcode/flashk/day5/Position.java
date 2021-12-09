package com.adventofcode.flashk.day5;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class Position {

	private int x;
	private int y;
	
	public void transform(Direction direction) {
		this.x += direction.getX();
		this.y += direction.getY();
	}
}
