package com.adventofcode.flashk.day04;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class BingoFirstWinStrategyEdgeCasesTest {
	
	@Test
	void testEmptyNumberList() {
		
		BingoStrategy strategy = new BingoFirstWinStrategy();
		
		int result = strategy.solve(new ArrayList<>(), new ArrayList<>(new ArrayList<>()));
		
		assertEquals(0, result);
	}

}
