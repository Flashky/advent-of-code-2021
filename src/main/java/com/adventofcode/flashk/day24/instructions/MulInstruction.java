package com.adventofcode.flashk.day24.instructions;

import com.adventofcode.flashk.day24.ArithmeticLogicUnit;
import com.adventofcode.flashk.day24.Variable;

public class MulInstruction extends AbstractInstruction {
	
	public MulInstruction(ArithmeticLogicUnit alu, Variable a, Variable b) {
		super(alu, a, b);
	}
	
	public MulInstruction(ArithmeticLogicUnit alu, Variable a, long b) {
		super(alu, a, b);
	}

	@Override
	public void execute() {
		
		if(this.getB() != null) {
			this.getAlu().mul(this.getA(), this.getB());
		} else {
			this.getAlu().mul(this.getA(), this.getBValue());
		}
		
	}

	
}
