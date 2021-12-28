package com.adventofcode.flashk.day24;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ArithmeticLogicUnitTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testGetVariableValue() {
		
		ArithmeticLogicUnit alu = new ArithmeticLogicUnit(1,2,3,4);
		assertEquals(1, alu.getX());
		assertEquals(2, alu.getY());
		assertEquals(3, alu.getZ());
		assertEquals(4, alu.getW());
		
	}
	
	@Test
	void testAddValue() {
		
		ArithmeticLogicUnit alu = new ArithmeticLogicUnit(1,2,3,4);
		
		alu.add(Variable.X, 3);
		assertEquals(4, alu.getX());
		
		alu.add(Variable.W, 3);
		assertEquals(7, alu.getW());
		
		alu.add(Variable.Y, 0);
		assertEquals(2, alu.getY());
		
		alu.add(Variable.Y, -2);
		assertEquals(0, alu.getY());
		
	}
	
	@Test
	void testAddVariable() {
		
		ArithmeticLogicUnit alu = new ArithmeticLogicUnit(1,2,3,4);
		
		alu.add(Variable.Y, Variable.Z);
		assertEquals(5, alu.getY());
		assertEquals(3, alu.getZ()); // 'z' doesn't change.
	}
	
	@Test
	void testMulValue() {
		
		ArithmeticLogicUnit alu = new ArithmeticLogicUnit(1,2,3,4);
		
		alu.mul(Variable.X, 3);
		assertEquals(3, alu.getX());
		
		alu.mul(Variable.W, 3);
		assertEquals(12, alu.getW());
		
		alu.mul(Variable.Y, 0);
		assertEquals(0, alu.getY());
		
	}
	
	@Test
	void testMulVariable() {
		
		ArithmeticLogicUnit alu = new ArithmeticLogicUnit(1,2,3,4);
		
		alu.mul(Variable.Y, Variable.Z);
		assertEquals(6, alu.getY());
		assertEquals(3, alu.getZ()); // 'z' doesn't change.
		
	}
	
	@Test
	void testDivValue() {
		
		ArithmeticLogicUnit alu = new ArithmeticLogicUnit(1,2,3,4);
		
		alu.div(Variable.W, 2);
		assertEquals(2, alu.getW());
		
		alu.div(Variable.Y, 2);
		assertEquals(1, alu.getY());
		
	}
	
	@Test
	void testDivValueOperandAZero() {
		
		ArithmeticLogicUnit alu = new ArithmeticLogicUnit(0,2,3,4);
		
		alu.div(Variable.X, 2);
		assertEquals(0, alu.getX());
		
	}
	
	@Test
	void testDivValueOperandBZeroException() {
		
		ArithmeticLogicUnit alu = new ArithmeticLogicUnit(1,2,3,4);

		assertThrows(InvalidOperandValueException.class, () -> alu.div(Variable.W, 0));
		
	}
	
	@Test
	void testDivVariable() {
		
		ArithmeticLogicUnit alu = new ArithmeticLogicUnit(1,2,3,4);
		
		alu.div(Variable.W, Variable.Y);
		assertEquals(2, alu.getW());
		
		alu.div(Variable.Z, Variable.Y);
		assertEquals(1, alu.getZ());
		
	}
	
	@Test
	void testDivVariableOperandAZero() {
		
		ArithmeticLogicUnit alu = new ArithmeticLogicUnit(0,2,3,4);
		
		alu.div(Variable.X, Variable.Y);
		assertEquals(0, alu.getX());
		
	}
	
	@Test
	void testDivVariableOperandBZeroException() {
		
		ArithmeticLogicUnit alu = new ArithmeticLogicUnit(1,0,3,4);

		assertThrows(InvalidOperandValueException.class, () -> alu.div(Variable.X, Variable.Y));

		
	}
	
	@Test
	void testModValue() {
		
		ArithmeticLogicUnit alu = new ArithmeticLogicUnit(1,2,3,4);
		
		alu.mod(Variable.W, 2);
		assertEquals(0, alu.getW());
		
		alu.mod(Variable.Z, 2);
		assertEquals(1, alu.getZ());
		
	}
	
	@Test
	void testModValueOperandAZero() {
		
		ArithmeticLogicUnit alu = new ArithmeticLogicUnit(0,2,3,4);
		
		alu.mod(Variable.W, 2);
		assertEquals(0, alu.getW());
		
	}
	
	@Test
	void testModValueOperandALessThanZeroException() {
		
		ArithmeticLogicUnit alu = new ArithmeticLogicUnit(-1,2,3,4);

		assertThrows(InvalidOperandValueException.class, () -> alu.mod(Variable.X, 3));
		
	}
	
	@Test
	void testModValueOperandBZeroException() {
		
		ArithmeticLogicUnit alu = new ArithmeticLogicUnit(1,2,3,4);

		assertThrows(InvalidOperandValueException.class, () -> alu.mod(Variable.W, 0));
		
	}
	
	@Test
	void testModValueOperandBLessThanZeroException() {
		
		ArithmeticLogicUnit alu = new ArithmeticLogicUnit(1,2,3,4);

		assertThrows(InvalidOperandValueException.class, () -> alu.mod(Variable.W, -1));
		
	}

	@Test
	void testModVariable() {
		
		ArithmeticLogicUnit alu = new ArithmeticLogicUnit(1,2,3,4);
		
		alu.mod(Variable.Z, Variable.Y);
		assertEquals(1, alu.getZ());
		assertEquals(2, alu.getY()); // 'y' doesn't change.
		
	}
	
	@Test
	void testModVariableOperandAZero() {
		
		ArithmeticLogicUnit alu = new ArithmeticLogicUnit(0,2,3,4);
		
		alu.mod(Variable.X, Variable.Y);
		assertEquals(0, alu.getX());
		assertEquals(2, alu.getY()); // 'y' doesn't change.
		
	}
	
	@Test
	void testModVariableOperandALessThanZeroException() {
		
		ArithmeticLogicUnit alu = new ArithmeticLogicUnit(-1,2,3,4);

		assertThrows(InvalidOperandValueException.class, () -> alu.mod(Variable.X, Variable.Y));
		
	}
	
	@Test
	void testModVariableOperandBZeroException() {
		
		ArithmeticLogicUnit alu = new ArithmeticLogicUnit(0,2,3,4);

		assertThrows(InvalidOperandValueException.class, () -> alu.mod(Variable.W, Variable.X));
		
	}
	
	@Test
	void testModVariableOperandBLessThanZeroException() {
		
		ArithmeticLogicUnit alu = new ArithmeticLogicUnit(-1,2,3,4);

		assertThrows(InvalidOperandValueException.class, () -> alu.mod(Variable.W, Variable.X));
		
	}
	
	
	@Test
	void testEqlValue() {
		
		ArithmeticLogicUnit alu = new ArithmeticLogicUnit(1,2,3,4);
		
		alu.eql(Variable.X, 1);
		assertEquals(1, alu.getX());
		
		alu.eql(Variable.X, 3);
		assertEquals(0, alu.getX());
		
		alu.eql(Variable.Y, 2);
		assertEquals(1, alu.getY());
		
		alu.eql(Variable.Y, 3);
		assertEquals(0, alu.getY());
		
		alu.eql(Variable.Z, 3);
		assertEquals(1, alu.getZ());
		
		alu.eql(Variable.Z, 2);
		assertEquals(0, alu.getZ());
		
		alu.eql(Variable.W, 4);
		assertEquals(1, alu.getW());
		
		alu.eql(Variable.W, 3);
		assertEquals(0, alu.getW());
		
	}
	
	@Test
	void testEqlVariable() {
		
		ArithmeticLogicUnit alu = new ArithmeticLogicUnit(2,2,3,3);
		
		alu.eql(Variable.X, Variable.Y);
		assertEquals(1, alu.getX());
		assertEquals(2, alu.getY()); // 'y' doesn't change.
		
		alu.eql(Variable.Z, Variable.W);
		assertEquals(1, alu.getZ());
		assertEquals(3, alu.getW()); // 'w' doesn't change.
		
		alu.eql(Variable.Y, Variable.W);
		assertEquals(0, alu.getY());
		assertEquals(3, alu.getW()); // 'w' doesn't change.
		
	}
	
}
