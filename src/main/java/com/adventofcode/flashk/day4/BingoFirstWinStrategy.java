package com.adventofcode.flashk.day4;

import java.util.List;

public class BingoFirstWinStrategy implements BingoStrategy {

	@Override
	public int solve(List<Integer> numbers, List<Board> boards) {
		
		for(Integer number : numbers) {
			for(Board board : boards) {
				if(board.checkNumber(number)) {
					return board.calculateValue(number);
				}
			}
		}
		
		return 0; // There is no solution
	}

}
