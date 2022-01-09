package com.adventofcode.flashk.day24.instructions;

import com.adventofcode.flashk.day24.ArithmeticLogicUnit;
import com.adventofcode.flashk.day24.Variable;

public class ModInstruction extends AbstractInstruction {
	
	public ModInstruction(ArithmeticLogicUnit alu, Variable a, Variable b) {
		super(alu, a, b);
	}
	
	public ModInstruction(ArithmeticLogicUnit alu, Variable a, long b) {
		super(alu, a, b);
	}

	@Override
	public void execute() {
		
		if(this.getB() != null) {
			this.getAlu().mod(this.getA(), this.getB());
		} else {
			this.getAlu().mod(this.getA(), this.getBValue());
		}
		
	}

	
}
