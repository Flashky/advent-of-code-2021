package com.adventofcode.flashk.day18;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class SnailfishMath {

	private SnailfishMath() {}
	
	private static final String OPEN_BRACKET = "[";
	private static final String CLOSE_BRACKET = "]";
	private static final String SEPARATOR = ",";
	
	private static final Pattern PATTERN_SIMPLE_NUMBER = Pattern.compile("\\[([0-9]*),([0-9]*)\\]");
	private static final Pattern PATTERN_NUMBER_SPLIT = Pattern.compile("(\\d{2,})");
	
	public static String sum(String a , String b) {
		return new StringBuilder()
				.append(OPEN_BRACKET)
				.append(a)
				.append(SEPARATOR)
				.append(b)
				.append(CLOSE_BRACKET)
				.toString();	
	}
	
	public static String reduce(String number) {
		
		String previousReducedNumber = number;
		String reducedNumber = previousReducedNumber;
		
		boolean isReduced = false;
		
		do {
			
			previousReducedNumber = reducedNumber;
			reducedNumber = explode(previousReducedNumber);
			
			if(reducedNumber.equals(previousReducedNumber)) {
				reducedNumber = split(previousReducedNumber);
				isReduced = (reducedNumber.equals(previousReducedNumber));
			}

		} while(!isReduced);
		
		return reducedNumber;
	}
	
	public static String explode(String number) {
		

		// Step 1 - Find if there are any 5-nested brackets
		int openingBrackets = 0;
		boolean foundExplodeIndexes = false;
		int openBracketIndex = -1;
		int closeBracketIndex = -1;
		int currentIndex = 0;
		
		while(!foundExplodeIndexes && currentIndex < number.length()) {
			
			String currentCharacter = number.substring(currentIndex, currentIndex+1);
			
			if(OPEN_BRACKET.equals(currentCharacter)) {
				openingBrackets++;
				if(openingBrackets == 5) {
					openBracketIndex = currentIndex;
				}
			} else if (CLOSE_BRACKET.equals(currentCharacter)) {
				openingBrackets--;
				if(openBracketIndex != -1) {
					closeBracketIndex = currentIndex;
					foundExplodeIndexes = true;
				}
			}
			
			currentIndex++;
		}
		
		if(!foundExplodeIndexes) {
			return number; // Nothing to explode
		}

		
		// Step 2 - Obtain left, middle and right side
		
		String left = number.substring(0, openBracketIndex);
		String middle = number.substring(openBracketIndex, closeBracketIndex+1);
		String right = number.substring(closeBracketIndex+1);
		
		Matcher matcher = PATTERN_SIMPLE_NUMBER.matcher(middle);
		matcher.find();
		
		int leftNumber = Integer.valueOf(matcher.group(1));
		int rightNumber = Integer.valueOf(matcher.group(2));
		
		// Step 3 - Apply explotion addition to the left and right side of the number
		left = addLeft(left, leftNumber);
		right = addRight(right, rightNumber);
		
		return new StringBuilder().append(left).append(right).toString();
	}

	/**
	 * Adds the passed number to the right side of a snailfish number.
	 * <p>If there are no numbers at the right side, then a 0 is preppended.</p>
	 * <p>If the number is not immediately to the right, then an additional 0 is preppended,</p>
	 * 
	 * <b>Examples:</b>
	 * <pre>
	 * addRight("]]]]", 3)		=> "0]]]]"
	 * addRight("4],5],6],7]",3)	=> "7,5],6],7]"
	 * addRight("]]],7]")		=> "0]]],10]"
	 * </pre>
	 * @param leftSide The left side of an exploded snailfish number
	 * @param number The number to add to the left side
	 * @return the left side of the exploded numbe including the needed addition.
	 */
	private static String addRight(String rightSide, int number) {

		int currentIndex = 0;
		int openNumberIndex = -1;
		int closeNumberIndex = -1;
		boolean foundNumber = false;
		
		// Found the number if there is any.
		while(!foundNumber &&  currentIndex < rightSide.length()) {
			String currentCharacter = rightSide.substring(currentIndex, currentIndex+1);
			
			boolean currentCharacterIsNumeric = isNumeric(currentCharacter);
			
			if(!currentCharacterIsNumeric && openNumberIndex != -1) {
				foundNumber = true;
				closeNumberIndex = currentIndex;
			}
			
			if(currentCharacterIsNumeric && openNumberIndex == -1) {
				openNumberIndex = currentIndex;
			}
			
			currentIndex++;
		}
		
		// Build the result of adding
		StringBuilder rightNumberBuilder = new StringBuilder();
		if(!foundNumber) {
			rightNumberBuilder.append("0").append(rightSide);
		} else {
			
			String left = rightSide.substring(0, openNumberIndex);
			String middle = rightSide.substring(openNumberIndex, closeNumberIndex);
			String right = rightSide.substring(closeNumberIndex);
			
			int newNumber = Integer.valueOf(middle) + number;
			
			if(openNumberIndex > 1) {
				rightNumberBuilder.append("0");
			}
			
			rightNumberBuilder.append(left).append(newNumber).append(right);
		}
		
		return rightNumberBuilder.toString();
	}

	/**
	 * Adds the passed number to the left side of a snailfish number.
	 * <p>If there are no numbers at the left side, then a 0 is appended.</p>
	 * <p>If the number is not immediately to the left, then an additional 0 is appended,</p>
	 * 
	 * <b>Examples:</b>
	 * <pre>
	 * addLeft("[[[[", 3)		=> "[[[[0"
	 * addLeft("[7,[6,[5,[4",3)	=> "[7,[6,[5,[7"
	 * addLeft("[7,[[[")		=> "[10,[[[0"
	 * </pre>
	 * @param leftSide The left side of an exploded snailfish number
	 * @param number The number to add to the left side
	 * @return the left side of the exploded numbe including the needed addition.
	 */
	private static String addLeft(String leftSide, int number) {
	
		int currentIndex = leftSide.length() - 1;
		int openNumberIndex = -1;
		int closeNumberIndex = -1;
		boolean foundNumber = false;
		
		// Found the number if there is any.
		while(!foundNumber &&  currentIndex > 0) {
			String currentCharacter = leftSide.substring(currentIndex-1, currentIndex);
			
			boolean currentCharacterIsNumeric = isNumeric(currentCharacter);
			
			if(closeNumberIndex != -1 && !currentCharacterIsNumeric) {
				foundNumber = true;
				openNumberIndex = currentIndex;
			}
			
			if(closeNumberIndex == -1 && currentCharacterIsNumeric) {
				closeNumberIndex = currentIndex;
			}
			
			currentIndex--;
		}
		
		// Build the result of adding
		StringBuilder leftNumberBuilder = new StringBuilder();
		if(!foundNumber) {
			leftNumberBuilder.append(leftSide).append("0");
		} else {
			
			String left = leftSide.substring(0, openNumberIndex);
			String middle = leftSide.substring(openNumberIndex, closeNumberIndex);
			String right = leftSide.substring(closeNumberIndex);
			
			int newNumber = Integer.valueOf(middle) + number;
		
			
			leftNumberBuilder.append(left).append(newNumber).append(right);
			
			if(closeNumberIndex < leftSide.length() - 1) {
				leftNumberBuilder.append("0");
			}
		}
		
		return leftNumberBuilder.toString();
	}

	public static String split(String number) {
		
		String result = number;
		
		// Step 1 - Find if there is any 2-digit number
		Matcher matcher = PATTERN_NUMBER_SPLIT.matcher(number);
		
		if(matcher.find()) {
			
			float numberToSplit = Float.valueOf(matcher.group(1));
			float division = numberToSplit / 2;
	
			int leftNumber = (int) Math.floor(division);
			int rightNumber = (int) Math.ceil(division);
			
			String newPair = new StringBuilder()
					.append(OPEN_BRACKET)
					.append(leftNumber)
					.append(SEPARATOR)
					.append(rightNumber)
					.append(CLOSE_BRACKET)
					.toString();
			
			result = matcher.replaceFirst(newPair);
		}
		
		return result;
	}
	
	/**
	 * Calculates the magnitude of a snailfish number.
	 * <p>The magnitude of a pair is 3 times the magnitude of its left element plus 2 times the magnitude of its right element.</p> 
	 * <p>The magnitude of a regular number is just that number.</p>
	 * @param number
	 * @return
	 */
	public static long magnitude(String number) {

		String result = "0";
		Matcher matcher = PATTERN_SIMPLE_NUMBER.matcher(number);
		
		while(matcher.find()) {
			
			long leftNumber = Long.valueOf(matcher.group(1));
			long rightNumber = Long.valueOf(matcher.group(2));
			
			long magnitude = 3 * leftNumber + 2 * rightNumber;
			result = matcher.replaceFirst(String.valueOf(magnitude));
			
			matcher = PATTERN_SIMPLE_NUMBER.matcher(result);
			
		}
		
		return Long.valueOf(result);
	}

	private static boolean isNumeric(String number) {
	    if (number == null) {
	        return false;
	    }
	    try {
	        Long.parseLong(number);
	    } catch (NumberFormatException nfe) {
	        return false;
	    }
	    return true;
	}
}
