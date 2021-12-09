package com.adventofcode.flashk.common;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

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
	
	@Test
	void testNormalizeLeft() {
		
		Vector2 vector = new Vector2(-5,0);
		vector.normalize();
		
		assertEquals(-1,  vector.getX());
		assertEquals(0, vector.getY());
	}
	
	@Test
	void testNormalizeRight() {
		
		Vector2 vector = new Vector2(5,0);
		vector.normalize();
		
		assertEquals(1,  vector.getX());
		assertEquals(0, vector.getY());
	}
	
	@Test
	void testNormalizeUp() {
		
		Vector2 vector = new Vector2(0,5);
		vector.normalize();
		
		assertEquals(0,  vector.getX());
		assertEquals(1, vector.getY());
	}
	
	@Test
	void testNormalizeDown() {
		
		Vector2 vector = new Vector2(0,-5);
		vector.normalize();
		
		assertEquals(0,  vector.getX());
		assertEquals(-1, vector.getY());
	}

	@Test
	void testNormalizeUpRight() {
		
		Vector2 vector = new Vector2(5,5);
		vector.normalize();
		
		assertEquals(1,  vector.getX());
		assertEquals(1, vector.getY());
	}
	
	@Test
	void testNormalizeDownRight() {
		
		Vector2 vector = new Vector2(5,-5);
		vector.normalize();
		
		assertEquals(1,  vector.getX());
		assertEquals(-1, vector.getY());
	}
	
	@Test
	void testNormalizeUpLeft() {
		
		Vector2 vector = new Vector2(-5,5);
		vector.normalize();
		
		assertEquals(-1,  vector.getX());
		assertEquals(1, vector.getY());
	}
	
	@Test
	void testNormalizeDownLeft() {
		
		Vector2 vector = new Vector2(-5,-5);
		vector.normalize();
		
		assertEquals(-1,  vector.getX());
		assertEquals(-1, vector.getY());
	}
	
}
