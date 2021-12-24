package com.adventofcode.flashk.day18;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SnailfishMathTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testSum() {

		String number1 = "[1,2]";
		String number2 = "[[3,4],5]";
		
		assertEquals("[[1,2],[[3,4],5]]", SnailfishMath.sum(number1, number2));
	}
	
	@Test
	void testMagnitudeSimple91() {

		String number = "[9,1]";
		assertEquals(29, SnailfishMath.magnitude(number));
	}
	
	@Test
	void testMagnitudeSimple19() {

		String number = "[1,9]";
		assertEquals(21, SnailfishMath.magnitude(number));
	}
	
	@Test
	void testMagnitudeComplex1() {
		String number = "[[9,1],[1,9]]";
		assertEquals(129, SnailfishMath.magnitude(number));
	}
	
	@Test
	void testMagnitudeComplex2() {
		String number = "[[[[0,7],4],[[7,8],[6,0]]],[8,1]]";
		assertEquals(1384, SnailfishMath.magnitude(number));
	}
	
	@Test
	void testMagnitudeComplex3() {
		String number = "[[[[1,1],[2,2]],[3,3]],[4,4]]";
		assertEquals(445, SnailfishMath.magnitude(number));
	}
	
	@Test
	void testMagnitudeComplex4() {
		String number = "[[[[3,0],[5,3]],[4,4]],[5,5]]";
		assertEquals(791, SnailfishMath.magnitude(number));
	}
	
	@Test
	void testMagnitudeComplex5() {
		String number = "[[[[5,0],[7,4]],[5,5]],[6,6]]";
		assertEquals(1137, SnailfishMath.magnitude(number));
	}
	
	@Test
	void testMagnitudeComplex6() {
		String number = "[[[[8,7],[7,7]],[[8,6],[7,7]]],[[[0,7],[6,6]],[8,7]]]";
		assertEquals(3488, SnailfishMath.magnitude(number));
	}
	
	@Test
	void testMagnitudeComplex7() {
		String number = "[[[[6,6],[7,6]],[[7,7],[7,0]]],[[[7,7],[7,7]],[[7,8],[9,9]]]]";
		assertEquals(4140, SnailfishMath.magnitude(number));
	}
	
	@Test
	void testExplode1() {
		String number = "[[[[[9,8],1],2],3],4]";
		assertEquals("[[[[0,9],2],3],4]", SnailfishMath.explode(number));
	}

	@Test
	void testExplode2() {
		String number = "[7,[6,[5,[4,[3,2]]]]]";
		assertEquals("[7,[6,[5,[7,0]]]]", SnailfishMath.explode(number));
	}
	
	@Test
	void testExplode3() {
		String number = "[[6,[5,[4,[3,2]]]],1]";
		assertEquals("[[6,[5,[7,0]]],3]", SnailfishMath.explode(number));
	}
	
	@Test
	void testExplode4() {
		String number = "[[3,[2,[1,[7,3]]]],[6,[5,[4,[3,2]]]]]";
		assertEquals("[[3,[2,[8,0]]],[9,[5,[4,[3,2]]]]]", SnailfishMath.explode(number));
	}
	
	@Test
	void testExplode5() {
		String number = "[[3,[2,[8,0]]],[9,[5,[4,[3,2]]]]]";
		assertEquals("[[3,[2,[8,0]]],[9,[5,[7,0]]]]", SnailfishMath.explode(number));
	}
	
	@Test
	void testExplode6() {
		String number = "[[[[[4,3],4],4],[7,[[8,4],9]]],[1,1]]";
		assertEquals("[[[[0,7],4],[7,[[8,4],9]]],[1,1]]", SnailfishMath.explode(number));
	}
	
	@Test
	void testExplode7() {
		String number = "[[[[0,7],4],[7,[[8,4],9]]],[1,1]]";
		assertEquals("[[[[0,7],4],[15,[0,13]]],[1,1]]", SnailfishMath.explode(number));
	}
	
	@Test
	void testExplode8() {
		String number = "[[[[0,7],4],[[7,8],[0,[6,7]]]],[1,1]]";
		assertEquals("[[[[0,7],4],[[7,8],[6,0]]],[8,1]]", SnailfishMath.explode(number));
	}
}


