package com.adventofcode.flashk.day04;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import com.adventofcode.flashk.day04.BingoLastWinStrategy;
import com.adventofcode.flashk.day04.BingoStrategy;

class BingoLastWinStrategyEdgeCasesTest {
	
	@Test
	void testEmptyNumberList() {
		
		BingoStrategy strategy = new BingoLastWinStrategy();
		
		int result = strategy.solve(new ArrayList<>(), new ArrayList<>(new ArrayList<>()));
		
		assertEquals(0, result);
	}

}
