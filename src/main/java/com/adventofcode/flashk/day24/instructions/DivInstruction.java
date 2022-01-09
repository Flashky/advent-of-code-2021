package com.adventofcode.flashk.day24.instructions;

import com.adventofcode.flashk.day24.ArithmeticLogicUnit;
import com.adventofcode.flashk.day24.Variable;

public class DivInstruction extends AbstractInstruction {
	
	public DivInstruction(ArithmeticLogicUnit alu, Variable a, Variable b) {
		super(alu, a, b);
	}
	
	public DivInstruction(ArithmeticLogicUnit alu, Variable a, long b) {
		super(alu, a, b);
	}

	@Override
	public void execute() {
		
		if(this.getB() != null) {
			this.getAlu().div(this.getA(), this.getB());
		} else {
			this.getAlu().div(this.getA(), this.getBValue());
		}
		
	}

	
}
