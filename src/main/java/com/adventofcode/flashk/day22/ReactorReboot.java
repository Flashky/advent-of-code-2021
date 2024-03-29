package com.adventofcode.flashk.day22;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.regex.Pattern;

import com.adventofcode.flashk.common.Vector3;

public class ReactorReboot {
	
	private Queue<RebootInstruction> instructions = new LinkedList<>();
	
	private Set<Vector3> cubes = new HashSet<>(2000000);
	
	public ReactorReboot(List<String> inputs) {
		
		for(String input : inputs) {
			instructions.add(new RebootInstruction(input));
		}

	}
	
	public long solveA() {
		
		instructions.stream().limit(20).forEach(this::performAction);
		return cubes.size();
		
	}
	
	public long solveB() {
		
		// https://www.reddit.com/r/adventofcode/comments/rlxhmg/2021_day_22_solutions/
		
		// Quizá deba rehacer la solución para trabajar con intersecciones de cuboides.
		// Ejemplo: https://github.com/nilanshu96/Advent-Of-Code/blob/main/2021/22/Part2.java
		
		instructions.stream().forEach(this::performAction);
		return cubes.size();
		
	}
	
	public void performAction(RebootInstruction instruction) {
		
		// Para cualquier cubo dentro del cuboide especificado en la instrucción
		for(int x = instruction.getX1(); x <= instruction.getX2(); x++) {
			for(int y = instruction.getY1(); y <= instruction.getY2(); y++) {
				for(int z = instruction.getZ1(); z <= instruction.getZ2(); z++) {
					Vector3 cube = new Vector3(x, y, z);
					
					// Si el cubo no existía:
					// - Añadir cubo al mapa
					// - Incrementar contador solo si la acción es encender.
					
					// Si el cubo ya existía:
					// - Incrementar contador si el cubo estaba apagado y pasa a estar encendido.
					// - Decrementar contador si el cubo estaba encendido y pasa a estar apagado.
					// - Actualizar el estado del cubo
					
					if(instruction.isOn()) {
						cubes.add(cube);
					} else {
						cubes.remove(cube);
					}
					
					
				}
			}
		}
	}
	
}
