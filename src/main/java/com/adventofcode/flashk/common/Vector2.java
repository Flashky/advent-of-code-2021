package com.adventofcode.flashk.common;

import com.adventofcode.flashk.day05.Direction;

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
	
	public void normalize() {
		
		double length = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
		
		if(length != 0) {
			double s = 1 / length;
			x = (int) Math.round(x * s);
			y = (int) Math.round(y * s);
		}
		
	}
}
