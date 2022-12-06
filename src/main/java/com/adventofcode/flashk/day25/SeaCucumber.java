package com.adventofcode.flashk.day25;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class SeaCucumber {

	private final static char EAST_CUCUMBER = '>';
	private final static char SOUTH_CUCUMBER = 'v';
	private final static char EMPTY = '.';
	
	private char[][] cucumbersMap;
	
	private int cols;
	private int rows;
	
	public SeaCucumber(List<String> inputs) {
		
		rows = inputs.size();
		cols = inputs.get(0).length();
		
		cucumbersMap = new char[cols][rows];
		
		int rowNumber = 0;
		for(String input : inputs) {
			cucumbersMap[rowNumber++] = input.toCharArray();
		}
		
		System.out.println("test");
	}
	
	public int solve() {
		
		int steps = 0;
		int movements = 0;
		
		do {
			steps++;
			movements = step();
		} while (movements > 0);
		
		return steps;
	}

	private int step() {
		int movements = 0;
		
		for(int rowIndex = 0; rowIndex < rows; rowIndex++) {
			char[] row = cucumbersMap[rowIndex];
			movements += moveEast(row, rowIndex);
		}
		
		for(int colIndex = 0; colIndex < cols; colIndex++) {
			char[] col = getCol(colIndex);
			movements += moveSouth(col, colIndex);
		}
		
		return movements;
	}


	/*==============================================================================*/
	/* EAST																		*/
	/*==============================================================================*/
	
	private int moveEast(char[] row, int rowIndex) {

		Queue<Integer> stagedCucumbers = new LinkedList<>();
		
		for(int colIndex = 0; colIndex < cols; colIndex++) {
			if(canMoveEast(row, rowIndex, colIndex)) {
				stagedCucumbers.add(colIndex);
			}
			
		}
		
		return commitEastMovements(row, stagedCucumbers);
	}

	private boolean canMoveEast(char[] row, int rowIndex, int colIndex) {
		
		// Only move '>' cucumbers
		if(row[colIndex] != EAST_CUCUMBER) {
			return false;
		}
		
		// The space next to the right is occupied
		if(row[getNextColIndex(colIndex)] != EMPTY) {
			return false;
		}
		
		return true;
	}

	private int getNextColIndex(int colIndex) {
		int nextColIndex = colIndex+1;
		if(nextColIndex % cols == 0) {
			nextColIndex = 0;
		}
		return nextColIndex;
	}
	
	private int commitEastMovements(char[] row, Queue<Integer> stagedCucumbers) {
		
		for(int colIndex : stagedCucumbers) {
			row[colIndex] = EMPTY;
			row[getNextColIndex(colIndex)] = EAST_CUCUMBER;
		}
		
		return stagedCucumbers.size();
	}
	
	
	/*==============================================================================*/
	/* SOUTH																		*/
	/*==============================================================================*/
	
	private int moveSouth(char[] col, int colIndex) {
		Queue<Integer> stagedCucumbers = new LinkedList<>();
		
		for(int rowIndex = 0; rowIndex < rows; rowIndex++) {
			if(canMoveSouth(col, rowIndex, colIndex)) { // Verificar si hay que dar vuelta a rowIndex y colIndex
				stagedCucumbers.add(rowIndex);
			}
			
		}
		
		return commitSouthMovements(col, colIndex, stagedCucumbers);
	}

	private boolean canMoveSouth(char[] col, int rowIndex, int colIndex) {
		// Only move '>' cucumbers
		if(col[rowIndex] != SOUTH_CUCUMBER) {
			return false;
		}
		
		// The space next to the right is occupied
		if(col[getNextRowIndex(rowIndex)] != EMPTY) {
			return false;
		}
		
		return true;
	}
	
	private int getNextRowIndex(int rowIndex) {
		int nextRowIndex = rowIndex+1;
		if(nextRowIndex % rows == 0) {
			nextRowIndex = 0;
		}
		return nextRowIndex;
	}
	
	private int commitSouthMovements(char[] col, int colIndex, Queue<Integer> stagedCucumbers) {
		
		for(int rowIndex : stagedCucumbers) {
			cucumbersMap[rowIndex][colIndex] = EMPTY;
			cucumbersMap[getNextRowIndex(rowIndex)][colIndex] = SOUTH_CUCUMBER;
		}
		
		return stagedCucumbers.size();
	}

	
	private char[] getCol(int colIndex) {
		
		char[] col = new char[rows];
		for(int rowIndex = 0; rowIndex < rows; rowIndex++) {
			col[rowIndex] = cucumbersMap[rowIndex][colIndex];
		}
		return col;
	}
}
