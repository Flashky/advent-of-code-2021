package com.adventofcode.flashk.common;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import com.adventofcode.flashk.day05.Direction;

import nl.jqno.equalsverifier.EqualsVerifier;

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
	void testSubstractAbsY1Positive() {
		
		Vector2 leftOperand = new Vector2(0,14);
		Vector2 rightOperand = new Vector2(0,7);
		
		Vector2 result = Vector2.substractAbs(leftOperand, rightOperand);
		
		assertNotNull(result);
		assertEquals(0, result.getX());
		assertEquals(7, result.getY());
	}
	
	@Test
	void testSubstractAbsYNegative() {
		
		Vector2 leftOperand = new Vector2(0,7);
		Vector2 rightOperand = new Vector2(0,14);
		
		Vector2 result = Vector2.substractAbs(leftOperand, rightOperand);
		
		assertNotNull(result);
		assertEquals(0, result.getX());
		assertEquals(7, result.getY());
	}
	
	@Test
	void testSubstractAbsXPositive() {
		
		Vector2 leftOperand = new Vector2(11,0);
		Vector2 rightOperand = new Vector2(5,0);
		
		Vector2 result = Vector2.substractAbs(leftOperand, rightOperand);
		
		assertNotNull(result);
		assertEquals(6, result.getX());
		assertEquals(0, result.getY());
	}
	
	@Test
	void testSubstractAbsXNegative() {
		
		Vector2 leftOperand = new Vector2(5,0);
		Vector2 rightOperand = new Vector2(11,0);
		
		Vector2 result = Vector2.substractAbs(leftOperand, rightOperand);
		
		assertNotNull(result);
		assertEquals(6, result.getX());
		assertEquals(0, result.getY());
	}
	
	@Test
	void testHashCodeAndEquals() {
		EqualsVerifier.simple()
			.forClass(Vector2.class)
			.verify();
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
	
	@Test
	void testNormalizeOrigin() {
		
		Vector2 vector = new Vector2(0,0);
		vector.normalize();
		
		assertEquals(0,  vector.getX());
		assertEquals(0, vector.getY());
	}

}
