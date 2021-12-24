package com.adventofcode.flashk.day18;

import java.util.List;

public class Snailfish {
	
	private List<String> numbers;

	public Snailfish(List<String> inputs) {
		this.numbers = inputs;
	}
	
	public long solveA() {
		
		String lastReducedSum = "";
		String leftOperand = numbers.get(0);
		
		for(int i = 1; i < numbers.size(); i++) {
			
			String rightOperand = numbers.get(i);
			
			String sum = SnailfishMath.sum(leftOperand, rightOperand);
			lastReducedSum = SnailfishMath.reduce(sum);
			
			leftOperand = lastReducedSum;
			
		}
		
		return SnailfishMath.magnitude(lastReducedSum);
		
	}
	
	// Subproblema - Magnitud
	public long magnitude(String number) {
		
		// The magnitude of a pair is 3 times the magnitude of its left element plus 2 times the magnitude of its right element. 
		// The magnitude of a regular number is just that number.
		
		// Example: 
		// [9,1] -> 3*9 + 2*1 = 29
		// [1,9] -> 3*1 + 2*9 = 21
		// [[9,1],[1,9]] -> 3*29 + 2*21 = 129
		// Es decir, es como si calculásemos la magnitud de un número como el siguiente:
		// [29,21]
		
		return 0;
	}
}
