package com.adventofcode.flashk.day24.instructions;

import com.adventofcode.flashk.day24.ArithmeticLogicUnit;
import com.adventofcode.flashk.day24.Variable;

public class AddInstruction extends AbstractInstruction {
	
	public AddInstruction(ArithmeticLogicUnit alu, Variable a, Variable b) {
		super(alu, a, b);
	}
	
	public AddInstruction(ArithmeticLogicUnit alu, Variable a, long b) {
		super(alu, a, b);
	}

	@Override
	public void execute() {
		
		if(this.getB() != null) {
			this.getAlu().add(this.getA(), this.getB());
		} else {
			this.getAlu().add(this.getA(), this.getBValue());
		}
		
	}

	
}
