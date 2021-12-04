package com.adventofcode.flashk.day4;

import java.util.ArrayList;
import java.util.List;

public class Bingo {

	private List<Integer> numbers;
	private List<Board> boards;
	private int finishedBoards = 0;
	
	public Bingo(List<Integer> numbers) {
		this.numbers = numbers;
		this.boards = new ArrayList<>();
	}
	
	public void addBoard(Board board) {
		boards.add(board);
	}
	
	public int solve() {
		
		for(Integer number : numbers) {
			for(Board board : boards) {
				if(board.checkNumber(number)) {
					return board.calculateValue(number);
				}
			}
		}
		
		return 0; // There is no solution
		
	}
	
	public int solveB() {
		
		for(Integer number : numbers) {
			for(Board board : boards) {
				if(!board.isFinished()) {
					if(board.checkNumber(number)) {
						finishedBoards++;
						if(finishedBoards == boards.size()) {
							return board.calculateValue(number);
						}
					}
				}
			}
		}
		
		return 0; // There is no solution
	}
}
