package com.adventofcode.flashk.day24;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ArithmeticLogicUnit {

	private static final String INVALID_VARIABLE_VALUE_ERROR = "Must be a valid variable value";
	private static final String DIVISION_BY_ZERO_ERROR = "Cannot divide by zero";
	private static final String MODULE_OPERAND_A_ERROR = "Module first operand must be greater than zero";
	private static final String MODULE_OPERAND_B_ERROR = "Module second operand must be greater or equal than zero";
	
	private long x, y, z, w = 0;

	public void inp(Variable a, long value) {
		switch(a) {
			case X: x = value; break;
			case Y: y = value; break;
			case Z: z = value; break;
			case W: w = value; break;
		
		}
	}
	
	public void add(Variable a, long b) {
		
		switch(a) {
			case X: x += b; break;
			case Y: y += b; break;
			case Z: z += b; break;
			case W: w += b; break;
			
		}
	}
	
	public void reset() {
		x = 0;
		y = 0;
		z = 0;
		w = 0;
	}
	
	public void add(Variable a, Variable b) {
		add(a, getVariableValue(b));
	}
	
	public void mul(Variable a, long b) {
		
		switch(a) {
			case X: x *= b; break;
			case Y: y *= b; break;
			case Z: z *= b; break;
			case W: w *= b; break;
			
		}
	}
	
	public void mul(Variable a, Variable b) {
		mul(a, getVariableValue(b));
	}

	public void div(Variable a, long b) {
		
		if(b == 0) {
			throw new InvalidOperandValueException(DIVISION_BY_ZERO_ERROR);
		}
		
		switch(a) {
			case X: x /= b; break;
			case Y: y /= b; break;
			case Z: z /= b; break;
			case W: w /= b; break;
			
		}
	}
	
	public void div(Variable a, Variable b) {
		div(a, getVariableValue(b));
	}
	
	public void mod(Variable a, long b) {
		
		if(getVariableValue(a) < 0) {
			throw new InvalidOperandValueException(MODULE_OPERAND_A_ERROR);
		}
		
		if(b <= 0) {
			throw new InvalidOperandValueException(MODULE_OPERAND_B_ERROR);
		}
		
		switch(a) {
			case X: x %= b; break;
			case Y: y %= b; break;
			case Z: z %= b; break;
			case W: w %= b; break;
			
		}
	}
	
	public void mod(Variable a, Variable b) {
		mod(a, getVariableValue(b));
	}
	
	public void eql(Variable a, long b) {
		
		switch(a) {
			case X: x = equal(x, b); break;
			case Y: y = equal(y, b); break;
			case Z: z = equal(z, b); break;
			case W: w = equal(w, b); break;
		}
	}
	
	public void eql(Variable a, Variable b) {
		eql(a, getVariableValue(b));
	}
	
	private int equal(long a, long b) {
		return (a == b) ? 1 : 0;
	}
	
	private long getVariableValue(Variable variable) {
	
		switch(variable) {
			case X: return x;
			case Y: return y;
			case Z: return z;
			case W: return w;
			default: throw new IllegalArgumentException(INVALID_VARIABLE_VALUE_ERROR);
		}

	}
}
