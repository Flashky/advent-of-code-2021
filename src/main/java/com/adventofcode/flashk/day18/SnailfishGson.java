package com.adventofcode.flashk.day18;

import java.util.List;

public class SnailfishGson {


	private List<String> numbers;
	
	public SnailfishGson(List<String> inputs) {
		this.numbers = inputs;
	}
	
	public long solveA() {
		
		String leftOperand = numbers.get(0);
		String reducedSum = "";
		for(int i = 1; i < numbers.size(); i++) {
			String rightOperand = numbers.get(i);
			String sum = SnailfishGsonMath.sum(leftOperand, rightOperand);
			reducedSum = SnailfishGsonMath.reduce(sum);
		}

		return SnailfishMath.magnitude(reducedSum);
	}



	public long solveB() {
		
		/*
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
		*/
		return 0;
	}
}
