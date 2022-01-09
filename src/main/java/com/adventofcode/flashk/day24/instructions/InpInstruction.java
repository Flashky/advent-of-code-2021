package com.adventofcode.flashk.day24.instructions;

import com.adventofcode.flashk.day24.ArithmeticLogicUnit;
import com.adventofcode.flashk.day24.Variable;

public class InpInstruction extends AbstractInstruction {

	public InpInstruction(ArithmeticLogicUnit alu, Variable a, long b) {
		super(alu, a, b);
	}

	@Override
	public void execute() {
		
		this.getAlu().inp(this.getA(), this.getBValue());
		
	}

	
}
