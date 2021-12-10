package com.adventofcode.flashk.day03;

import java.util.List;

import lombok.Getter;

public class GammaEpsilon {

	@Getter
	private int[] gammaCount;
	private int[] epsilonCount;
	
	/**
	 * Constructs a new GammaEpsilon object.
	 * <p>Calculates the gamma and epsilon values from the binary input list.</p>
	 * @param binaryInputs
	 */
	public GammaEpsilon(List<String> binaryInputs) {
		
		gammaCount = new int[binaryInputs.get(0).length()];
		epsilonCount = new int[binaryInputs.get(0).length()];
		
		for(String binaryInput : binaryInputs) {
			
			char[] bits = binaryInput.toCharArray();

			for(int i = 0; i < bits.length; i++) {
				if(bits[i] == '1') {
					gammaCount[i]++;
				} else {
					epsilonCount[i]++;
				}
			}
		}
		
	}
	
	boolean isGammaDominant(int bitIndex) {
		return gammaCount[bitIndex] >= epsilonCount[bitIndex];
	}
}
