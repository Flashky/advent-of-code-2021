package com.adventofcode.flashk.day18;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SnailfishGsonMathTest {

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

		assertEquals("[[1,2],[[3,4],5]]", SnailfishGsonMath.sum(number1, number2));
	}

	@Test
	void testMagnitude1() {

		String number = "[9,1]";
		assertEquals(29, SnailfishGsonMath.magnitude(number));
	}

	@Test
	void testMagnitude2() {

		String number = "[1,9]";
		assertEquals(21, SnailfishGsonMath.magnitude(number));
	}

	@Test
	void testMagnitude3() {
		String number = "[[9,1],[1,9]]";
		assertEquals(129, SnailfishGsonMath.magnitude(number));
	}

	@Test
	void testMagnitude4() {
		String number = "[[[[0,7],4],[[7,8],[6,0]]],[8,1]]";
		assertEquals(1384, SnailfishGsonMath.magnitude(number));
	}

	@Test
	void testMagnitude5() {
		String number = "[[[[1,1],[2,2]],[3,3]],[4,4]]";
		assertEquals(445, SnailfishGsonMath.magnitude(number));
	}

	@Test
	void testMagnitude6() {
		String number = "[[[[3,0],[5,3]],[4,4]],[5,5]]";
		assertEquals(791, SnailfishGsonMath.magnitude(number));
	}

	@Test
	void testMagnitude7() {
		String number = "[[[[5,0],[7,4]],[5,5]],[6,6]]";
		assertEquals(1137, SnailfishGsonMath.magnitude(number));
	}

	@Test
	void testMagnitude8() {
		String number = "[[[[8,7],[7,7]],[[8,6],[7,7]]],[[[0,7],[6,6]],[8,7]]]";
		assertEquals(3488, SnailfishGsonMath.magnitude(number));
	}

	@Test
	void testMagnitude9() {
		String number = "[[[[6,6],[7,6]],[[7,7],[7,0]]],[[[7,7],[7,7]],[[7,8],[9,9]]]]";
		assertEquals(4140, SnailfishGsonMath.magnitude(number));
	}

	@Test
	void testExplode1() {
		String number = "[[[[[9,8],1],2],3],4]";
		assertEquals("[[[[0,9],2],3],4]", SnailfishGsonMath.explode(number));
	}

	@Test
	void testExplode2() {
		String number = "[7,[6,[5,[4,[3,2]]]]]";
		assertEquals("[7,[6,[5,[7,0]]]]", SnailfishGsonMath.explode(number));
	}

	@Test
	void testExplode3() {
		String number = "[[6,[5,[4,[3,2]]]],1]";
		assertEquals("[[6,[5,[7,0]]],3]", SnailfishGsonMath.explode(number));
	}

	@Test
	void testExplode4() {
		String number = "[[3,[2,[1,[7,3]]]],[6,[5,[4,[3,2]]]]]";
		assertEquals("[[3,[2,[8,0]]],[9,[5,[4,[3,2]]]]]", SnailfishGsonMath.explode(number));
	}

	@Test
	void testExplode5() {
		String number = "[[3,[2,[8,0]]],[9,[5,[4,[3,2]]]]]";
		assertEquals("[[3,[2,[8,0]]],[9,[5,[7,0]]]]", SnailfishGsonMath.explode(number));
	}

	@Test
	void testExplode6() {
		String number = "[[[[[4,3],4],4],[7,[[8,4],9]]],[1,1]]";
		assertEquals("[[[[0,7],4],[7,[[8,4],9]]],[1,1]]", SnailfishGsonMath.explode(number));
	}

	@Test
	void testExplode7() {
		String number = "[[[[0,7],4],[7,[[8,4],9]]],[1,1]]";
		assertEquals("[[[[0,7],4],[15,[0,13]]],[1,1]]", SnailfishGsonMath.explode(number));
	}

	@Test
	void testExplode8() {
		String number = "[[[[0,7],4],[[7,8],[0,[6,7]]]],[1,1]]";
		assertEquals("[[[[0,7],4],[[7,8],[6,0]]],[8,1]]", SnailfishGsonMath.explode(number));
	}

	@Test
	void testSplit1() {
		String number = "10";
		assertEquals("[5,5]", SnailfishGsonMath.split(number));
	}

	@Test
	void testSplit2() {
		String number = "11";
		assertEquals("[5,6]", SnailfishGsonMath.split(number));
	}

	@Test
	void testSplit3() {
		String number = "[[[[0,7],4],[15,[0,13]]],[1,1]]";
		assertEquals("[[[[0,7],4],[[7,8],[0,13]]],[1,1]]", SnailfishGsonMath.split(number));
	}

	@Test
	void testSplit4() {
		String number = "[[[[0,7],4],[[7,8],[0,13]]],[1,1]]";
		assertEquals("[[[[0,7],4],[[7,8],[0,[6,7]]]],[1,1]]", SnailfishGsonMath.split(number));
	}


	@Test
	void testReduce1() {
		String number = "[[[[0,7],4],[[7,8],[6,0]]],[8,1]]";
		assertEquals("[[[[0,7],4],[[7,8],[6,0]]],[8,1]]", SnailfishGsonMath.reduce(number));
	}

	@Test
	void testReduce2() {
		String number = "[[[[0,7],4],[[7,8],[0,[6,7]]]],[1,1]]";
		assertEquals("[[[[0,7],4],[[7,8],[6,0]]],[8,1]]", SnailfishGsonMath.reduce(number));
	}

	@Test
	void testReduce3() {
		String number = "[[[[0,7],4],[[7,8],[0,13]]],[1,1]]";
		assertEquals("[[[[0,7],4],[[7,8],[6,0]]],[8,1]]", SnailfishGsonMath.reduce(number));
	}

	@Test
	void testReduce4() {
		String number = "[[[[0,7],4],[15,[0,13]]],[1,1]]";
		assertEquals("[[[[0,7],4],[[7,8],[6,0]]],[8,1]]", SnailfishGsonMath.reduce(number));
	}

	@Test
	void testReduce5() {
		String number = "[[[[0,7],4],[7,[[8,4],9]]],[1,1]]";
		assertEquals("[[[[0,7],4],[[7,8],[6,0]]],[8,1]]", SnailfishGsonMath.reduce(number));
	}

	@Test
	void testReduce6() {
		String number = "[[[[[4,3],4],4],[7,[[8,4],9]]],[1,1]]";
		assertEquals("[[[[0,7],4],[[7,8],[6,0]]],[8,1]]", SnailfishGsonMath.reduce(number));
	}


}


