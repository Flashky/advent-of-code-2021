package com.adventofcode.flashk.day18;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public final class SnailfishMath {

	private SnailfishMath() {}
	
	private static final String OPEN_BRACKET = "[";
	private static final String CLOSE_BRACKET = "]";
	private static final String SEPARATOR = ",";
	private static final Pattern PATTERN_SIMPLE_NUMBER = Pattern.compile("\\[([0-9]*),([0-9]*)\\]");
	private static final Pattern PATTERN_SINGLE_NUMBER = Pattern.compile("([0-9]*)");
	
	public static String sum(String a, String b) {
		return new StringBuilder()
				.append(OPEN_BRACKET)
				.append(a)
				.append(SEPARATOR)
				.append(b)
				.append(CLOSE_BRACKET)
				.toString();	
	}
	
	public static String reduce(String number) {
		
		// Uses explode and split to reduce the number
		return "";
	}
	
	public static String explode(String number) {
		
		Deque<String> numberComponents = new ArrayDeque<>();
		
		for(int i = 0; i < number.length(); i++) {
			numberComponents.add(String.valueOf(number.charAt(i))); 
		}
		
		Stack<String> leftComponents = new Stack<>();
		
		int openingBrackets = 0;
		
		while((openingBrackets < 5) && (!numberComponents.isEmpty())) {
			
			String component = numberComponents.poll();
			leftComponents.add(component);
			
			if(OPEN_BRACKET.equals(component)) {
				openingBrackets++;
			} else if (CLOSE_BRACKET.equals(component)) {
				openingBrackets--;
			}
		}
		
		if(openingBrackets == 5) {
			
			// Obtain left and right number to explode
			String leftNumber = numberComponents.poll();
			numberComponents.remove();
			String rightNumber = numberComponents.poll();
			
			leftComponents.pop();
			String leftNumberString = leftComponents.stream().collect(Collectors.joining(""));
		
			if(leftNumberString.matches("([0-9]*)")) {
				
				Matcher matcher = PATTERN_SINGLE_NUMBER.matcher(leftNumberString);
				matcher.find();
				
				int newNumber = Integer.valueOf(matcher.group(1)) + Integer.valueOf(leftNumber);
				matcher.replaceFirst(String.valueOf(newNumber));
			} else {
				leftNumberString += "0";
			}
			
			
			numberComponents.removeFirst();
			String rightNumberString = numberComponents.stream().collect(Collectors.joining(""));
			
			System.out.println(leftNumberString + " ---- " + rightNumberString);
			
			// TODO: find the last number at the left side
			// if there is a match, replace with the sum of number + leftNumber
			// if there is no match: 0
			
			// TODO fid the first number at the right side
			// if there is a match, replace with the sum of number + rightNumber
			// if there is no match: 0
		}
/*		
		Deque<String> definitiveLeftComponents = new ArrayDeque();
		Deque<String> definitiveRightComponents = new ArrayDeque();
		
		if(openingBrackets == 5) {
			
			// Explode value to the left
			leftComponents.removeLast();
			
			int newValue = 0;
			boolean foundNumber = false;
			while((!leftComponents.isEmpty()) && (!foundNumber)) {
				
				String component = leftComponents.pollLast();
				
				if(component.matches("[0-9]*")) {
					foundNumber = true;
					newValue = Integer.valueOf(component) +  Integer.valueOf(leftNumber);
				} else {
					definitiveLeftComponents.addLast(component);
				}
			}
			

			definitiveLeftComponents.addLast(String.valueOf(newValue));
			definitiveLeftComponents.addAll(leftComponents);

			// Explode value to the right
			numberComponents.removeFirst();
			
			newValue = 0;
			foundNumber = false;
			
			while((!numberComponents.isEmpty()) && (!foundNumber)) {
				
				String component = numberComponents.pollFirst();
				
				if(component.matches("[0-9]*")) {
					foundNumber = true;
					newValue = Integer.valueOf(component) +  Integer.valueOf(rightNumber);
				} else {
					definitiveRightComponents.addFirst(component);
				}
			}
			
			definitiveRightComponents.addLast(String.valueOf(newValue));
			definitiveRightComponents.addAll(numberComponents);

			
			definitiveLeftComponents.addAll(definitiveRightComponents);
		}

		StringBuilder explodedNumberBuilder = new StringBuilder();
		
		while(!definitiveLeftComponents.isEmpty()) {
			explodedNumberBuilder.append(definitiveLeftComponents.poll());
		}
		*/
		//return explodedNumberBuilder.toString();
		
		return "";
	}
	
	public static String split(String number) {
		return "";
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
	
}
