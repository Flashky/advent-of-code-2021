package com.adventofcode.flashk.common;

import com.adventofcode.flashk.day5.Direction;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class Vector2 {

	private int x;
	private int y;
	
	public void transform(Direction direction) {
		this.x += direction.getX();
		this.y += direction.getY();
	}
}
