package com.adventofcode.flashk.day18;

import java.util.List;

public class Snailfish {

	private List<String> numbers;
	
	// Problema principal:
	// - Sumar dos n�meros snailfish
	
	// Pasos para sumar dos n�meros.
	// 1. Primero, se concatenan ambos n�meros en formato: "[" + number1 + ","+number2+"]".
	// 2. En segundo lugar se reduce el n�mero (subproblemas explode y split)
	// 3. Por �ltimo, se calcula la magnitud del n�mero resultante de manera recursiva. (subproblema magnitud)
	
	// Subproblema - Explode:
	// 1. Primero, de izquierda a derecha, si hay alg�n anidamiento superior a 4 (se puede contar n�mero de "["), se hace explode.
	// 2. Se repite el paso 1 hasta que no queden elementos a los que hacer explode.
	
	// Subproblema - Split
	// 1. Se hace split de un n�mero.
	// 2. Si hay nuevos elementos a los que hacer explode, empezar nuevamente a realizar el algoritmo de explode.
	// 3. Si sigue habiendo elementos a los que hacer split, repetir paso 1 de split.
	// 4. Una vez ya no se pueden hacer m�s explode o split, el n�mero est� reducido.
	
	// Subproblema - Magnitud
	// 1. F�rmula: 3 * elemento izquierdo + 2 * elemento derecho.
	// 2. Se aplica recursi�n.
	public Snailfish(List<String> inputs) {
		this.numbers = inputs;
	}
	
	public long solveA() {
		long result = 0;
		
		return result;
	}
}
