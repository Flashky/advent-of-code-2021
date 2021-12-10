package com.adventofcode.flashk.day04;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Bingo {

	private List<Integer> numbers;
	private List<Board> boards = new ArrayList<>();
	
	public Bingo(List<String> inputs) {
		
		// Numbers generation
		numbers = Arrays.stream(inputs.get(0).split(",")).map(Integer::parseInt).collect(Collectors.toList());
		inputs.remove(0);
		
		// Boards generation
		boards = new ArrayList<>();
		List<String> boardRows = new ArrayList<>();
		
		for(String row : inputs) {
			
			if((row != null) && (!row.isEmpty())) {
				
				boardRows.add(row);
				
				if(boardRows.size() == 5) {
					boards.add(new Board(boardRows));
					boardRows = new ArrayList<>();
				}
				
			}
		}
		
	}
	
	public int solve(BingoStrategy strategy) {
		return strategy.solve(numbers, boards);
	}
	
	
}
