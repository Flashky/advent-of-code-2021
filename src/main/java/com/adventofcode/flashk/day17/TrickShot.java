package com.adventofcode.flashk.day17;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.adventofcode.flashk.common.AdvancedMath;
import com.adventofcode.flashk.common.Vector2;

//target area: x=20..30, y=-10..-5
// Initial speed = (7,2)
// 
// 0. pos = (0,0), 		speed = (7,2) 	-> new pos = (7,2)
// 1. pos = (7,2), 		speed = (6,1) 	-> new pos = (13,3) 
// 2. pos = (13,3), 	speed = (5,0) 	-> new pos = (18,3) -> APEX
// 3. pos = (18,3), 	speed = (4,-1) 	-> new pos = (22,2)
// 4. pos = (22,2), 	speed = (3,-2)	-> new pos = (25,0)
// 5. pos = (25,0),		speed = (2,-3)	-> new pos = (27,-3)	
// 6. pos = (27,-3), 	speed = (1,-4)	-> new pos = (28,-7)
// 
// Se ha llegado a una solución: 
// 20 <= x = 28 <= 30
// -10 <= y = -7 <= -5
// 
// Se necesita una función para comprobar si hemos llegado al ápice:
// Cuidado:
// - Son lanzamientos válidos lanzamientos horizontales y hacia abajo.
// - En ese caso, el punto y más alto es el punto de lanzamiento inicial (0,0).

// Conceptos:
// - Ángulo de alcance (angle of reach) para alcanzar una coordenada
// http://www.sc.ehu.es/sbweb/fisica3/cinematica/curvilineo/curvilineo_3.html
//
// - Altura máxima: se da cuando v(y) = 0
// - Componentes de velocidad: no necesario en este caso:
// 		x e y decrecen de una manera constante independientemente del ángulo

// Posible enfoque del problema:
// - Hay infinitas combinaciones de velocidad x e y, aunque se poden ciertos casos por ser inviables.
// - En vez de enfocar el problema desde el submarino, lo podemos enfocar desde la zona objetivo:
// 		-> Hay un número límitado de coordenadas en un cuadrado. 
// 		-> Por lo tanto, para cada coordenada perteneciente al cuadrado, calcularíamos ángulo rho de disparo para llegar al mismo.
public class TrickShot {

	private final static Pattern PATTERN_COORDINATES = Pattern.compile("x=([-]?[0-9]*)..([-]?[0-9]*), y=([-]?[0-9]*)..([-]?[0-9]*)");
	
	// Acceleration is uniform on this problem and there are two possible cases:
	//- If the probe has horizontal speed, it's acceleration evenly reduces both in x-axis and y-axis, simulating a ballistic acceleration.
	//- If the probe doesn't have horizontal speed (vx = 0), it is on free fall.
	
	private static final Vector2 BALLISTIC_ACCELERATION = new Vector2(-1, -1);
	private static final Vector2 FREE_FALL_ACCELERATION = new Vector2(0, -1);
	
	// The following coordinates determine the target area:
	private int minX;
	private int maxX;
	private int minY;
	private int maxY;
	
	// Initial position of the submarine / probe
	private Vector2 initialPos = new Vector2(0,0);
	private Vector2 probePosition = initialPos;
	
	private int minVx;
	private int maxVx;
	
	public TrickShot(String input) {
	
		Matcher matcher = PATTERN_COORDINATES.matcher(input);
		
		if(matcher.find()) {
			minX = Integer.valueOf(matcher.group(1));
			maxX = Integer.valueOf(matcher.group(2));
			minY = Integer.valueOf(matcher.group(3));
			maxY = Integer.valueOf(matcher.group(4));
		}
		
		// Calculate min v(x)
		boolean foundMinVx = false;
		minVx = 1;
		
		while(!foundMinVx) {
			int maxXDistance = (int) AdvancedMath.summation(minVx);
			if(maxXDistance >= minX) {
				foundMinVx = true;
			} else {
				minVx++;
			}
		}
		
		// Calculate max v(x)
		maxVx = maxX;
		
		System.out.println("finished");
	}
	
	public int solveA() {
		
		// Más ideas: https://www.fisica.uson.mx/mecanica/proyectiles/Documentacion/TeoriaProyectiles.htm
		
		// 1. Select speed
		
		// Premisas:
		// - Apuntar horizontalmente o hacia abajo implica una altura máxima de 0. 
		// 		-> Si no hay soluciones por encima de 0, entonces la primera solución horizontal será la mejor solución.
		// - Apuntar hacia arriba tiene soluciones infinitas si la zona objetivo está justo debajo del submarino
		// 		-> Podemos descartar estos casos, la zona objetivo va a estar desplazada respecto al submarino.
		
		Vector2 speed = new Vector2(6,9); // Ejemplo arbitrario que da max y para el sample
		
		int maxHeight = probePosition.getY();
		
		while(isFactible(probePosition) && !isSolution(probePosition)) {
			
			// Apply movement
			probePosition.transform(speed);
			
			if((speed.getY() == 0) && (probePosition.getY() > maxHeight)) {
				maxHeight = probePosition.getY();
				
				// TODO si es peor que la solución más óptima, cancelar.
			}
			
			// Update speed, applying the horizontal drag and vertical gravity.
			if(speed.getX() > 0) {
				speed.transform(BALLISTIC_ACCELERATION);
			} else {
				speed.transform(FREE_FALL_ACCELERATION);
			}
			
		}
		
		//int height = (int) AdvancedMath.summation(probePosition.getY());
		System.out.println(maxHeight);
		return maxHeight;
		
	}

	private boolean isFactible(Vector2 probePosition) {

		if(probePosition.getX() > maxX) {
			return false;
		}
		
		if(probePosition.getY() < minY) {
			return false;
		}
		
		return true;
	}
	
	private boolean isSolution(Vector2 probePosition) {	
		return isInXRange(probePosition.getX()) && isInYRange(probePosition.getY());
	}

	private boolean isInXRange(int x) {
		return (x >= minX && x <= maxX);
	}
	
	private boolean isInYRange(int y) {
		return (y >= minY && y <= maxY);
	}
}
