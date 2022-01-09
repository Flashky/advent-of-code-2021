package com.adventofcode.flashk.day24.instructions;

import com.adventofcode.flashk.day24.ArithmeticLogicUnit;
import com.adventofcode.flashk.day24.Variable;

import lombok.Getter;
import lombok.Setter;

@Getter
public abstract class AbstractInstruction implements Instruction {

	private ArithmeticLogicUnit alu;
	private Variable a;
	private Variable b;
	
	@Setter
	private long bValue;
	
	public AbstractInstruction(ArithmeticLogicUnit alu, Variable a, Variable b) {
		this.alu = alu;
		this.a = a;
		this.b = b;
	}
	
	public AbstractInstruction(ArithmeticLogicUnit alu, Variable a, long b) {
		this.alu = alu;
		this.a = a;
		this.bValue = b;
	}
	
	public abstract void execute();

}
