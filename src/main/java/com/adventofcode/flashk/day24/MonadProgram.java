package com.adventofcode.flashk.day24;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.adventofcode.flashk.day24.instructions.AddInstruction;
import com.adventofcode.flashk.day24.instructions.DivInstruction;
import com.adventofcode.flashk.day24.instructions.EqlInstruction;
import com.adventofcode.flashk.day24.instructions.InpInstruction;
import com.adventofcode.flashk.day24.instructions.Instruction;
import com.adventofcode.flashk.day24.instructions.ModInstruction;
import com.adventofcode.flashk.day24.instructions.MulInstruction;

public class MonadProgram {

	private static final Pattern INPUT_PATTERN = Pattern.compile("inp ([xyzw])");
	private static final Pattern OPERATION_VALUE_PATTERN = Pattern.compile("(add|mul|div|mod|eql) ([xyzw]) ([-]?[0-9]+)");
	private static final Pattern OPERATION_VARIABLE_PATTERN = Pattern.compile("(add|mul|div|mod|eql) ([xyzw]) ([xyzw])");
	
	private ArithmeticLogicUnit alu = new ArithmeticLogicUnit();
	
	//private List<String> instructions;
	private Queue<Instruction> instructions = new LinkedList<>();
	private Queue<Instruction> executedInstructions = new LinkedList<>();
	
	private Long modelNumber = 99999999999999L;
	private Stack<Integer> modelNumberDigits;
	
	public MonadProgram(List<String> inputs) {

		// Copy to array list for faster access
		//this.instructions = new ArrayList<>(inputs);
		
		for(String input : inputs) {
			Matcher inputMatcher = INPUT_PATTERN.matcher(input);
			Matcher operationValueMatcher = OPERATION_VALUE_PATTERN.matcher(input);
			Matcher operationVariableMatcher = OPERATION_VARIABLE_PATTERN.matcher(input);
			
			if(inputMatcher.find()) {	
				Variable variable = Variable.valueOf(inputMatcher.group(1).toUpperCase());
				//alu.inp(variable, modelNumberDigits.pop());
				
				instructions.add(new InpInstruction(alu, variable, 0L));
						
			} else if(operationValueMatcher.find()) {
				
				String operation = operationValueMatcher.group(1);
				Variable a = Variable.valueOf(operationValueMatcher.group(2).toUpperCase());
				int b = Integer.valueOf(operationValueMatcher.group(3));
				
				addOperation(operation, a, b);
				//executeOperation(operation, a, b);
				
			} else if(operationVariableMatcher.find()) {
				
				String operation = operationVariableMatcher.group(1);
				Variable a = Variable.valueOf(operationVariableMatcher.group(2).toUpperCase());
				Variable b =  Variable.valueOf(operationVariableMatcher.group(3).toUpperCase());
				
				addOperation(operation, a, b);
				//executeOperation(operation, a, b);
				
			}
		}
		
		initializeNextDigits();
		
	}

	public long solveA() {

		boolean found = false; 

		while(!found) {
			
			found = executeInstructions();
			//found = true; // TODO remove
			if(!found) {
				initializeNextDigits();
				resetAlu();
			}
		
		}
		
		return modelNumber;
	}
	 
	/*
	private boolean executeInstructions() {

		//System.out.println();
		try {
			for(String instruction : instructions) {
				
				// TODO optimization: don't apply regex patterns on each iteration
				// Just load all the instructions in memory and then execute them.
				
				// Input command
				Matcher inputMatcher = INPUT_PATTERN.matcher(instruction);
				Matcher operationValueMatcher = OPERATION_VALUE_PATTERN.matcher(instruction);
				Matcher operationVariableMatcher = OPERATION_VARIABLE_PATTERN.matcher(instruction);
				
				if(inputMatcher.find()) {	
					Variable var = Variable.valueOf(inputMatcher.group(1).toUpperCase());
					alu.inp(var, modelNumberDigits.pop());
				} else if(operationValueMatcher.find()) {
					
					String operation = operationValueMatcher.group(1);
					Variable a = Variable.valueOf(operationValueMatcher.group(2).toUpperCase());
					int b = Integer.valueOf(operationValueMatcher.group(3));
					
					executeOperation(operation, a, b);
					
				} else if(operationVariableMatcher.find()) {
					
					String operation = operationVariableMatcher.group(1);
					Variable a = Variable.valueOf(operationVariableMatcher.group(2).toUpperCase());
					Variable b =  Variable.valueOf(operationVariableMatcher.group(3).toUpperCase());
					
					executeOperation(operation, a, b);
					
				}

			}	
		} catch (InvalidOperandValueException ex) {
			// If any instruction is a division or module with invalid parameters, 
			// then the current instruction set is not valid.
			return false;
		}
		
		return alu.getZ() == 0;
	}*/

	private boolean executeInstructions() {

		//System.out.println();
		try {
			
			while(!instructions.isEmpty()) {
				
				Instruction instruction = instructions.poll();
				
				if(instruction instanceof InpInstruction) {
					InpInstruction input = (InpInstruction) instruction;
					input.setBValue(modelNumberDigits.pop());
				}
				
				instruction.execute();
				executedInstructions.add(instruction);
			}	
			
		} catch (InvalidOperandValueException ex) {
			// If any instruction is a division or module with invalid parameters, 
			// then the current instruction set is not valid.
			return false;
		}
		
		return alu.getZ() == 0;
	}
	
	private void addOperation(String operation, Variable a, int b) {
		
		switch(operation) {
			case "add": instructions.add(new AddInstruction(alu, a, b)); break;
			case "mul": instructions.add(new MulInstruction(alu, a, b)); break;
			case "div": instructions.add(new DivInstruction(alu, a, b)); break;
			case "mod": instructions.add(new ModInstruction(alu, a, b)); break;
			case "eql": instructions.add(new EqlInstruction(alu, a, b)); break;
		}	
		
	}
	
	private void addOperation(String operation, Variable a, Variable b) {
		
		switch(operation) {
			case "add": instructions.add(new AddInstruction(alu, a, b)); break;
			case "mul": instructions.add(new MulInstruction(alu, a, b)); break;
			case "div": instructions.add(new DivInstruction(alu, a, b)); break;
			case "mod": instructions.add(new ModInstruction(alu, a, b)); break;
			case "eql": instructions.add(new EqlInstruction(alu, a, b)); break;
		}	
		
	}
	
	/*
	private void executeOperation(String operation, Variable a, int b) {

		switch(operation) {
			case "add": alu.add(a, b); break;
			case "mul": alu.mul(a, b); break;
			case "div": alu.div(a, b); break;
			case "mod": alu.mod(a, b); break;
			case "eql": alu.eql(a, b); break;
		}
		
	}
	
	private void executeOperation(String operation, Variable a, Variable b) {
		
		switch(operation) {
			case "add": alu.add(a, b); break;
			case "mul": alu.mul(a, b); break;
			case "div": alu.div(a, b); break;
			case "mod": alu.mod(a, b); break;
			case "eql": alu.eql(a, b); break;
		}
		
	}
	*/

	private void initializeNextDigits() {
		
		modelNumberDigits = new Stack<>();
		
		long modelNumberMod = modelNumber;
		
		while(modelNumberMod > 0) {
			long remainder = modelNumberMod % 10;
			modelNumberDigits.add((int) remainder);
			modelNumberMod /= 10;
		}
		
		calculateNextModelNumber();

	}

	private void resetAlu() {
		alu.reset();
		instructions = executedInstructions;
		executedInstructions = new LinkedList<>();
	}

	private void calculateNextModelNumber() {

		// Model number cannot contain any zeros.
		// This method will try to calculate the next model number. if it has any zeros, it will calculate the next one until find a valid one.
		
		boolean validModelNumber = false;
		while(!validModelNumber) {
			modelNumber--;
			String modelNumberStr = String.valueOf(modelNumber);
			validModelNumber = !modelNumberStr.contains("0");
		}
		
	}
}
