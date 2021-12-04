package com.adventofcode.flashk.day4;

import java.util.List;

public class BingoLastWinStrategy implements BingoStrategy {

	private int finishedBoards = 0;
	
	@Override
	public int solve(List<Integer> numbers, List<Board> boards) {
		
		for(Integer number : numbers) {
			for(Board board : boards) {
				if(!board.isSolved() && board.checkNumber(number)) {
					finishedBoards++;
					if(finishedBoards == boards.size()) {
						return board.getScore();
					}
				}
			}
		}
		
		return 0; // There is no solution
	}

}
