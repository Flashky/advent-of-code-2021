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
	
	public long solveB() {
		
		long maxMagnitude = Integer.MIN_VALUE;
		
		for(int i = 0; i < numbers.size(); i++) {
			for(int j = 0; j < numbers.size(); j++) {
				
				// Only sum different numbers
				if(i != j) {
					
					String sum = SnailfishMath.sum(numbers.get(i), numbers.get(j));
					String reducedSum = SnailfishMath.reduce(sum);
					long magnitude = SnailfishMath.magnitude(reducedSum);
					
					maxMagnitude = Math.max(magnitude, maxMagnitude);
				}
			}
		}
		
		return maxMagnitude;
	}
}
