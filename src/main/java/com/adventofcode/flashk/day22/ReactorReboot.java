package com.adventofcode.flashk.day22;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.regex.Pattern;

import com.adventofcode.flashk.common.Vector3;

public class ReactorReboot {

	private static final String INPUT_PATTERN = "(on|off) x=([-]?[0-9]*)..([-]?[0-9]*),y=([-]?[0-9]*)..([-]?[0-9]*),z=([-]?[0-9]*)..([-]?[0-9]*)";
	private static final Pattern REBOOT_PATTERN = Pattern.compile(INPUT_PATTERN);
	
	private Queue<RebootInstruction> instructions = new LinkedList<>();
	
	// TODO ojo, la estructura de array no vale: 
	// Con los datos de sample, esto da un total de: 102.108.134.953.200 elementos en el array
	// El m�ximo n�mero de elementos es: 2,147,483,647
	
	//private boolean[][][] reactor;
	//private int minX, minY, minZ = Integer.MAX_VALUE;
	//private int maxX, maxY, maxZ = Integer.MIN_VALUE;
	
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
