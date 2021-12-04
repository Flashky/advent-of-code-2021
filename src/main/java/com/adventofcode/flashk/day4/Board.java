package com.adventofcode.flashk.day4;

import java.util.List;

public class Board {

	private Cell[][] cells = new Cell[5][5];
	private Integer unselectedValue = 0;
	private Integer lastNumber = -1;
	private Boolean isFinished = false;
	
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

	public boolean checkNumber(Integer number) {
		
		boolean found = false;
		boolean isBingo = false;
		int i = 0;
		int j = 0;
		
		while((!found) && (i < 5)) {
			while((!found) && (j < 5)) {
			
				Cell currentCell = cells[i][j];
				if(currentCell.getNumber().equals(number)) {
					found = true;
					currentCell.setIsSelected(true);
					isBingo = isBingo(i, j);
					lastNumber = number;
					unselectedValue -= number;
					
				} else {
					j++;
				}
			}
			
			i++;
			j = 0;
		}

		return isBingo;
	}
	
	public boolean isFinished() {
		return isFinished;
	}
	
	private boolean isBingo(int i, int j) {
	
		isFinished = checkRow(i) || checkCol(j);
		return isFinished;
	}

	private boolean checkRow(int i) {
		
		boolean isBingo = true;
		int j = 0;
		
		while((j < 5) && (isBingo)){
			isBingo = cells[i][j++].getIsSelected();
		}
		
		return isBingo;
	}
	
	private boolean checkCol(int j) {
		
		boolean isBingo = true;
		int i = 0;
		
		while((i < 5) && (isBingo)){
			isBingo = cells[i++][j].getIsSelected();
		}
		
		return isBingo;
	}

	public int calculateValue(Integer number) {
		return lastNumber * unselectedValue;
	}
}
