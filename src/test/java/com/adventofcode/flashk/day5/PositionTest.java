package com.adventofcode.flashk.day5;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

class PositionTest {
	
	@Test
	void testTransform() {
		
		Position startPos = new Position(2,5);
		Position endPos = new Position(3,6);
		
		Direction direction = new Direction(startPos, endPos);
		
		startPos.transform(direction);
		
		assertEquals(3, startPos.getX());
		assertEquals(6, startPos.getY());
		
	}
	
	@Test
	void testEquals() {
		Position obj1 = new Position(2,5);
		
		assertEquals(obj1, obj1);
		assertFalse(obj1.equals(null));
		assertFalse(obj1.equals(""));
	}

}
