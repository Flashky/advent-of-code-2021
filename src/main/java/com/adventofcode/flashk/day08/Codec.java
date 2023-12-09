package com.adventofcode.flashk.day08;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Codec {

	private final static String DIGIT_PATTERN = "([a-g]*)";
	private final static Pattern PATTERN = Pattern.compile(DIGIT_PATTERN);

	private Map<String, Integer> decodedNumbers = new HashMap<>();
	private Map<Integer, String> encodedNumbers = new HashMap<>();
	
	public Codec(String input) {
		
		Matcher matcher = PATTERN.matcher(input);
		
		List<String> unknownFiveSegmentPatterns = new ArrayList<>();
		List<String> unknownSixSegmentPatterns = new ArrayList<>();
		
		while(matcher.find()) {

			String signalPattern = Arrays.stream(matcher.group(1).split("|")).sorted().collect(Collectors.joining());
			
			switch (signalPattern.length()) {
				case 2: decode(signalPattern,1); break;
				case 3: decode(signalPattern,7); break;
				case 4: decode(signalPattern,4); break;
				case 7: decode(signalPattern,8); break;
				case 5: unknownFiveSegmentPatterns.add(signalPattern); break;
				case 6: unknownSixSegmentPatterns.add(signalPattern); break;
				default: break;
			}
		}
		
		decode(unknownFiveSegmentPatterns, unknownSixSegmentPatterns);
		
	}

	public Integer decode(String outputDigit) {
		return decodedNumbers.get(outputDigit);
	}
	
	private void decode(String signalPattern, Integer number) {

		decodedNumbers.put(signalPattern, number);
		encodedNumbers.put(number, signalPattern);
		 
	}
	
	private void decode(List<String> unknownFiveSegmentPatterns, List<String> unknownSixSegmentPatterns) {
		
		Set<Character> charactersPattern1 = stringToCharacterSet(encodedNumbers.get(1));
		Set<Character> charactersPattern4 = stringToCharacterSet(encodedNumbers.get(4));
		
		for(String unknownSixSegmentPattern : unknownSixSegmentPatterns) {
			
			if(hasAllCharacters(unknownSixSegmentPattern, charactersPattern1)
					&& !hasAllCharacters(unknownSixSegmentPattern, charactersPattern4)){
				
				// En el 0 cabe un 7 o un 1
				decodedNumbers.put(unknownSixSegmentPattern, 0);
				
			} else if(hasAllCharacters(unknownSixSegmentPattern, charactersPattern4)){
				
				// En el 9 cabe un 4 y un 7
				decodedNumbers.put(unknownSixSegmentPattern, 9);
				encodedNumbers.put(9, unknownSixSegmentPattern);


			} else {
				decodedNumbers.put(unknownSixSegmentPattern, 6);
			}
		}
		
		
		for(String unknownFiveSegmentPattern : unknownFiveSegmentPatterns) {
			
			String encoded9Pattern = encodedNumbers.get(9);
			
			if(hasAllCharacters(unknownFiveSegmentPattern, charactersPattern1)) {
				decodedNumbers.put(unknownFiveSegmentPattern, 3);
			} else {
				Set<Character> charactersPattern5 = stringToCharacterSet(unknownFiveSegmentPattern);
				
				// Operaci�n inversa a las de arriba, sabemos que en el 9 cabe un 5, 
				// pero no sabemos cual de los dos n�meros que quedan es un 5, as� que probamos cual
				// de los dos entra.
				
				if(hasAllCharacters(encoded9Pattern, charactersPattern5)) {
					decodedNumbers.put(unknownFiveSegmentPattern,5);
				} else {
					decodedNumbers.put(unknownFiveSegmentPattern, 2);
				}
			}
			
		}

	}

	private Set<Character> stringToCharacterSet(String s) {
	    Set<Character> set = new HashSet<>();
	    for (char c : s.toCharArray()) {
	        set.add(c);
	    }
	    return set;
	}
	
	private boolean hasAllCharacters(String segmentPattern, Set<Character> characters) {
		
		for(Character character : characters) {
			if(!segmentPattern.contains(character.toString())) {
				return false;
			}
		}
		
		return true;
	}
	

	
}
