package com.adventofcode.flashk.day4;

import java.util.ArrayList;
import java.util.List;

public class Bingo {

	private List<Integer> numbers;
	private List<Board> boards;
	
	public Bingo(List<Integer> numbers) {
		this.numbers = numbers;
		this.boards = new ArrayList<>();
	}
	
	public void addBoard(Board board) {
		boards.add(board);
	}
	
	public int solve(BingoStrategy strategy) {
		
		return strategy.solve(numbers, boards);
		
	}
}
