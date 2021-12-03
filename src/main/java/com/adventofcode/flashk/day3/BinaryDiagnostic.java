package com.adventofcode.flashk.day3;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BinaryDiagnostic {
	
	private int[] gammaCount;
	private int[] epsilonCount;
	
	public int solvePowerConsumption(List<String> binaryInputs) {

		calculateGammaEpsilon(binaryInputs);

		StringBuilder gammaBinaryBuilder = new StringBuilder();
		StringBuilder epsilonBinaryBuilder = new StringBuilder();
		
		for(int i = 0; i < gammaCount.length; i++) {
			
			if(gammaCount[i] > epsilonCount[i]) {
				gammaBinaryBuilder.append("1");
				epsilonBinaryBuilder.append("0");
			} else {
				gammaBinaryBuilder.append("0");
				epsilonBinaryBuilder.append("1");
			}
		}
		
		int decimalGammaRate = Integer.parseInt(gammaBinaryBuilder.toString(), 2);
		int decimalEpsilonRate = Integer.parseInt(epsilonBinaryBuilder.toString(), 2);
		
		return decimalGammaRate * decimalEpsilonRate;
	}
	
	public int solveLifeSupportRating(List<String> binaryInputs) {
		
		List<String> oxygenGeneratorRatingInputs = new ArrayList<>(binaryInputs);
		
		for(int i = 0; i < binaryInputs.get(0).length(); i++) {
			
			calculateGammaEpsilon(oxygenGeneratorRatingInputs);
			final int pos = i;
			
			if(gammaCount[i] >= epsilonCount[i]) {
				oxygenGeneratorRatingInputs = oxygenGeneratorRatingInputs.stream().filter(reading -> reading.charAt(pos) == '1').collect(Collectors.toList());
			} else {
				oxygenGeneratorRatingInputs = oxygenGeneratorRatingInputs.stream().filter(reading -> reading.charAt(pos) == '0').collect(Collectors.toList());
			}
		}
		
		
		
		List<String> co2RatingInputs = new ArrayList<>(binaryInputs);
		
		for(int i = 0; i < binaryInputs.get(0).length(); i++) {
			
			calculateGammaEpsilon(co2RatingInputs);
			final int pos = i;
			
			if((co2RatingInputs.size() > 1)) {
				if(gammaCount[i] >= epsilonCount[i]) {
					co2RatingInputs = co2RatingInputs.stream().filter(reading -> reading.charAt(pos) == '0').collect(Collectors.toList());
				} else {
					co2RatingInputs = co2RatingInputs.stream().filter(reading -> reading.charAt(pos) == '1').collect(Collectors.toList());
				}
			}
		}
		
		int oxygenGeneratorRating = Integer.parseInt(oxygenGeneratorRatingInputs.get(0),2);
		int co2Rating = Integer.parseInt(co2RatingInputs.get(0),2);
		
		return oxygenGeneratorRating * co2Rating;
	}
	
	private void calculateGammaEpsilon(List<String> binaryInputs) {
		
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
}
