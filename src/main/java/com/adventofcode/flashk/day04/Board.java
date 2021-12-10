package com.adventofcode.flashk.day04;

import java.util.Arrays;
import java.util.List;

public class Board {

	private Cell[][] cells = new Cell[5][5];
	private Integer unselectedValue = 0;
	private Boolean isSolved = false;
	private Integer score = 0;
	
	public Board(List<String> inputs) {
		
		int i = 0;
		for(String input : inputs) {
			
			// Obtain row numbers
			String[] numbersStr = input.split(" ");
			int j = 0;
			
			for(String numberStr : numbersStr) {
				if(!numberStr.equals("")) {
					Integer number = Integer.valueOf(numberStr);
					cells[i][j] = new Cell(number);					
					unselectedValue += number;

					j++;
				}
			}
			
			i++;
		}
	}

	/**
	 * Checks the number presence in the board.
	 * <p>If the number exists, verifies if the bingo board has been solved.</p>
	 * @param number the number too check
	 * @return true if the board has been solved. false otherwise.
	 */
	public boolean checkNumber(Integer number) {
		
		boolean found = false;

		int i = 0;
		int j = 0;
		
		while((!found) && (i < 5)) {
			while((!found) && (j < 5)) {
			
				Cell currentCell = cells[i][j];
				if(currentCell.getNumber().equals(number)) {
					found = true;
					currentCell.setIsSelected(true);
					checkSolution(i, j, number);
				}
				
				j++;
			}
			
			i++;
			j = 0;
		}

		return isSolved;
	}

	private void checkSolution(int i, int j, Integer number) {
		
		unselectedValue -= number;
		isSolved = checkRow(i) || checkCol(j);
		
		if(isSolved) {
			score = number * unselectedValue;
		}
	}
	
	public boolean isSolved() {
		return isSolved;
	}
	
	public int getScore() {
		return score;
	}
	
	private boolean checkRow(int i) {
		return Arrays.stream(cells[i]).filter(Cell::getIsSelected).count() == 5;
	}
	
	private boolean checkCol(int j) {
		
		boolean isBingo = true;
		int i = 0;
		
		while((i < 5) && (isBingo)){
			isBingo = cells[i++][j].getIsSelected();
		}
		
		return isBingo;
	}


}
