package com.adventofcode.flashk.day13;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import com.adventofcode.flashk.common.Vector2;

public class TransparentOrigami {

	private final static Pattern COORDINATES_PATTERN = Pattern.compile("([0-9]*),([0-9]*)");
	private final static Pattern INSTRUCTIONS_PATTERN = Pattern.compile("(x|y)[=]([0-9]*)");
	private final static char DOT = '#';
	
	// Paper and dots representations
	private int maxX = Integer.MIN_VALUE;
	private int maxY = Integer.MIN_VALUE;
	private char[][] paper;
	private Set<Vector2> dots = new HashSet<>();

	// Folding instructions
	private List<Instruction> instructions = new ArrayList<>();
	
	public TransparentOrigami(List<String> inputs) {
		

		
		for(String input : inputs) {
			
			Matcher coordinatesMatcher = COORDINATES_PATTERN.matcher(input);
			Matcher instructionsMatcher = INSTRUCTIONS_PATTERN.matcher(input);
			
			if(coordinatesMatcher.find()) {
				
				// Points coordinates
				int x = Integer.valueOf(coordinatesMatcher.group(1));
				int y = Integer.valueOf(coordinatesMatcher.group(2));

				maxX = Math.max(x, maxX);
				maxY = Math.max(y, maxY);
				
				dots.add(new Vector2(x,y));
				
			} else if (instructionsMatcher.find()){
				
				// Folding instructions
				String axis = instructionsMatcher.group(1);
				Integer value = Integer.valueOf(instructionsMatcher.group(2));
				
				instructions.add(new Instruction(axis, value));
				
			}
		}
		
		// Verify the paper is a perfectly shapped square
		
		// Initialize square
		paper = new char[maxX+1][maxY+1];
		for(Vector2 coordinates : dots) {
			paper[coordinates.getX()][coordinates.getY()] = DOT;
		}
		
	}
	
	public int foldOnce() {
		fold(instructions.get(0));
		return dots.size();
	}

	public int foldAll(boolean printResult) {

		for(Instruction instruction : instructions) {
			fold(instruction);
		}
		
		if(printResult) {
			print();
		}
		
		return dots.size();
	}

	private void fold(Instruction instruction) {
		
		if("x".equals(instruction.getAxis())) {
			
			// Obtains dots at the right of the folding point
			List<Vector2> dotsToFold = dots.stream()
											.filter(dot -> dot.getX() > instruction.getValue())
											.collect(Collectors.toList());
			
			fold(new Vector2(2 * instruction.getValue(), 0), dotsToFold);
			
		} else {
			
			// Obtains dots at the top of the folding point
			List<Vector2> dotsToFold = dots.stream()
											.filter(dot -> dot.getY() > instruction.getValue())
											.collect(Collectors.toList());
			
			fold(new Vector2(0, 2 * instruction.getValue()), dotsToFold);
		}
	}
	
	private void fold(Vector2 foldingPoint, List<Vector2> dotsToFold) {
		
		for(Vector2 dotToFold : dotsToFold) {

			Vector2 newPos = Vector2.substractAbs(foldingPoint, dotToFold);
			
			paper[newPos.getX()][newPos.getY()] = DOT;
			dots.add(newPos);
			dots.remove(dotToFold);

		}
		
	}
	
	private void print() {
		System.out.println();
		for(int y = 0; y <= maxY; y++) {
			for(int x = 0; x <= maxX; x++) {
				System.out.print(paper[x][y]);
			}
			System.out.println();
		}
	}
}
