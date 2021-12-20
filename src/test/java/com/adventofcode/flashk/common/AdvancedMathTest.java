package com.adventofcode.flashk.common;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AdvancedMathTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testSummation0() {
		assertEquals(0, AdvancedMath.summation(0));
	}

	@Test
	void testSummation1() {
		assertEquals(1, AdvancedMath.summation(1));
	}
	
	@Test
	void testSummation2() {
		assertEquals(3, AdvancedMath.summation(2));
	}
	
	@Test
	void testSummation10() {
		assertEquals(55, AdvancedMath.summation(10));
	}
}
