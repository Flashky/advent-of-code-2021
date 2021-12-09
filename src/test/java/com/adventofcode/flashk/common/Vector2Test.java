package com.adventofcode.flashk.common;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

import com.adventofcode.flashk.common.Vector2;
import com.adventofcode.flashk.day5.Direction;

class Vector2Test {
	
	@Test
	void testTransform() {
		
		Vector2 startPos = new Vector2(2,5);
		Vector2 endPos = new Vector2(3,6);
		
		Direction direction = new Direction(startPos, endPos);
		
		startPos.transform(direction);
		
		assertEquals(3, startPos.getX());
		assertEquals(6, startPos.getY());
		
	}
	
	@Test
	void testEquals() {
		Vector2 obj1 = new Vector2(2,5);
		
		assertEquals(obj1, obj1);
		assertFalse(obj1.equals(null));
		assertFalse(obj1.equals(""));
	}

}
