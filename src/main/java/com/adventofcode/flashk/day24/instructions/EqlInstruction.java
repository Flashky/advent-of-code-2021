package com.adventofcode.flashk.day24.instructions;

import com.adventofcode.flashk.day24.ArithmeticLogicUnit;
import com.adventofcode.flashk.day24.Variable;

public class EqlInstruction extends AbstractInstruction {
	
	public EqlInstruction(ArithmeticLogicUnit alu, Variable a, Variable b) {
		super(alu, a, b);
	}
	
	public EqlInstruction(ArithmeticLogicUnit alu, Variable a, long b) {
		super(alu, a, b);
	}

	@Override
	public void execute() {
		
		if(this.getB() != null) {
			this.getAlu().eql(this.getA(), this.getB());
		} else {
			this.getAlu().eql(this.getA(), this.getBValue());
		}
		
	}

	
}
