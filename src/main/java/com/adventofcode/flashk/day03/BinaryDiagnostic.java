package com.adventofcode.flashk.day03;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BinaryDiagnostic {
	
	private final static char BIT_ZERO = '0';
	private final static char BIT_ONE = '1';
	
	public int solvePowerConsumption(List<String> binaryInputs) {

		GammaEpsilon gammaEpsilon = new GammaEpsilon(binaryInputs);

		StringBuilder gammaBinaryBuilder = new StringBuilder();
		StringBuilder epsilonBinaryBuilder = new StringBuilder();
		
		for(int i = 0; i < gammaEpsilon.getGammaCount().length; i++) {
			
			if(gammaEpsilon.isGammaDominant(i)) {
				gammaBinaryBuilder.append(BIT_ONE);
				epsilonBinaryBuilder.append(BIT_ZERO);
			} else {
				gammaBinaryBuilder.append(BIT_ZERO);
				epsilonBinaryBuilder.append(BIT_ONE);
			}
		}
		
		int decimalGammaRate = Integer.parseInt(gammaBinaryBuilder.toString(), 2);
		int decimalEpsilonRate = Integer.parseInt(epsilonBinaryBuilder.toString(), 2);
		
		return decimalGammaRate * decimalEpsilonRate;
	}
	
	public int solveLifeSupportRating(List<String> binaryInputs) {
		
		List<String> oxygenGeneratorRatingInputs = new ArrayList<>(binaryInputs);
		List<String> co2RatingInputs = new ArrayList<>(binaryInputs);
		
		for(int i = 0; i < binaryInputs.get(0).length(); i++) {
			
			final int pos = i;
			
			// Oxygen calculation
			
			GammaEpsilon gammaEpsilon = new GammaEpsilon(oxygenGeneratorRatingInputs);
			
			if(gammaEpsilon.isGammaDominant(i)) {
				oxygenGeneratorRatingInputs = oxygenGeneratorRatingInputs.stream().filter(reading -> reading.charAt(pos) == BIT_ONE).collect(Collectors.toList());
			} else {
				oxygenGeneratorRatingInputs = oxygenGeneratorRatingInputs.stream().filter(reading -> reading.charAt(pos) == BIT_ZERO).collect(Collectors.toList());
			}
			
			// CO2 calculation

			gammaEpsilon = new GammaEpsilon(co2RatingInputs);
			
			if((co2RatingInputs.size() > 1)) {
				if(gammaEpsilon.isGammaDominant(i)) {
					co2RatingInputs = co2RatingInputs.stream().filter(reading -> reading.charAt(pos) == BIT_ZERO).collect(Collectors.toList());
				} else {
					co2RatingInputs = co2RatingInputs.stream().filter(reading -> reading.charAt(pos) == BIT_ONE).collect(Collectors.toList());
				}
			}
			
		}
		
		// Calculate ratings
		int oxygenGeneratorRating = Integer.parseInt(oxygenGeneratorRatingInputs.get(0),2);
		int co2Rating = Integer.parseInt(co2RatingInputs.get(0),2);
		
		return oxygenGeneratorRating * co2Rating;
	}

}
