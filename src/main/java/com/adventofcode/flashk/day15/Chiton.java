package com.adventofcode.flashk.day15;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

import com.adventofcode.flashk.common.Vector2;

public class Chiton {

	private Integer[][] riskMap;
	private int maxCols;
	private int maxRows;

	private Vector2 destination;

	private Map<Vector2,Integer> gScores = new HashMap<>();
	private Map<Vector2,Integer> fScores = new HashMap<>();
	
	//private MutableGraph<Vector2> graph = GraphBuilder.undirected().build();

	public Chiton(List<String> inputs) {
		
		maxCols = inputs.get(0).length();
		maxRows = inputs.size();

		// Initialize heat map values
		riskMap = new Integer[maxRows][maxCols];
		
		for(int row = 0; row < inputs.size(); row++) {
			
			String[] numbers = inputs.get(row).split("|");
			
			for(int col = 0; col < maxCols; col++) {
				riskMap[row][col] = Integer.valueOf(numbers[col]);
				
				Vector2 node = new Vector2(col,row);
				gScores.put(node, Integer.MAX_VALUE);
				fScores.put(node, Integer.MAX_VALUE);
				//graph.addNode(new Vector2(col,row));
				
			}
			
			
			

		}
		
		// Set the destination
		destination = new Vector2(maxCols-1, maxRows-1);
		
	}
	
	/*
	public int solveA() {
		
		lowerPathRisk = Integer.MAX_VALUE;
		
		searchMinPath(0, 0, new HashSet<>(), -riskMap[0][0]);
		
		return lowerPathRisk;
	}


	private void searchMinPath(int row, int col, Set<Vector2> visitedCells, Integer currentPathRisk) {
		
		Vector2 currentCell = new Vector2(col, row);
		
		if(isOutOfBounds(row, col)) {
			return;
		} else if(visitedCells.contains(currentCell)) {
			return;
		} else if(currentPathRisk > lowerPathRisk) {
			return;
		} else if(destination.equals(currentCell)) {
			lowerPathRisk = Math.min(currentPathRisk + riskMap[row][col], lowerPathRisk);
			return;
		}

		// Visit cell
		visitedCells.add(currentCell);
		currentPathRisk += riskMap[row][col];
		
		searchMinPath(row, col+1, visitedCells, currentPathRisk); // Search right
		searchMinPath(row+1, col, visitedCells, currentPathRisk); // Search down
		searchMinPath(row, col-1, visitedCells, currentPathRisk); // Search left
		searchMinPath(row-1, col, visitedCells, currentPathRisk); // Search up
		
		// Devisit cell
		visitedCells.remove(currentCell);
		currentPathRisk -= riskMap[row][col];
		
	}
	*/
	public int solveA() {

		return aStar(new Vector2(0,0), destination, 23);
		
		
	}

	private Integer aStar(Vector2 origin, Vector2 destination, Integer h) {
		
		// A* search algorithm: https://en.wikipedia.org/wiki/A*_search_algorithm
		// https://stackabuse.com/graphs-in-java-a-star-algorithm/
		// Initialize algorithm
		Integer initialHScore = h(origin);
		
		//Cell originCell = new Cell(initialHScore, 0, initialHScore, origin);
		/*Cell originCell = new Cell(origin);
		originCell.calculateHeuristic(destination);
		originCell.setGScore(0);
		originCell.setFScore(originCell.getHScore());*/
		
	    // The set of discovered nodes that may need to be (re-)expanded.
	    // Initially, only the start node is known.
	    // This is usually implemented as a min-heap or priority queue rather than a hash-set.
		//Set<Vector2> openSet = new HashSet<>();
		//openSet.add(origin);
		//PriorityQueue<Cell> openSet = new PriorityQueue<>(new CellComparator());
		PriorityQueue<Cell> openSet = new PriorityQueue<>();
		openSet.add(originCell);

	    // For node n, cameFrom[n] is the node immediately preceding it on the cheapest path from start
	    // to n currently known.
		Map<Vector2, Vector2> cameFrom = new HashMap<>();
		
		// For node n, gScore[n] is the cost of the cheapest path from start to n currently known.
		//Map<Vector2, Integer> gScore = new HashMap<>();
		//gScore.put(origin, 0);
		
		// For node n, fScore[n] := gScore[n] + h(n). fScore[n] represents our current best guess as to
	    // how short a path from start to finish can be if it goes through n.
		//Map<Vector2, Integer> fScore = new HashMap<>();
		//fScore.put(origin, h(origin));
		
		
		while(!openSet.isEmpty()) {
			// This operation can occur in O(1) time if openSet is a min-heap or a priority queue
			// Por ahora uso un set, para usar una cola de prioridad tendría que tener algún tipo de comparación que establezca los pesos bien
			///Cell currentCell = openSet.stream().min(Comparator.comparing(Cell::getFScore)).orElse(null);
			Cell currentCell = openSet.peek();
			
			// TODO falta enganchar esto en el algoritmo:
			if(currentCell.getPosition().equals(destination)) {
				return reconstructPath(cameFrom, currentCell);
			}
			
			openSet.remove(currentCell);
			
			// Calcular vecinos
			Set<Cell> adjacentCells = getAdjacentCells(currentCell);
			
			for(Cell adjacentCell : adjacentCells) {
				// d(current,neighbor) is the weight of the edge from current to neighbor
	            // tentative_gScore is the distance from start to the neighbor through current
				Integer tentativeGScore = currentCell.getGScore() + getRiskValue(adjacentCell.getPosition());
				
				if(tentativeGScore < adjacentCell.getGScore()) {
					// This path to neighbor is better than any previous one. Record it!
					cameFrom.put(adjacentCell.getPosition(), currentCell.getPosition());
					adjacentCell.setGScore(tentativeGScore);
					adjacentCell.setFScore(tentativeGScore + h(adjacentCell.getPosition()));
					openSet.add(adjacentCell);
				}
			}
		}
		
	
		return 0;
		
	}
	private Integer reconstructPath(Map<Vector2, Vector2> cameFrom, Cell currentCell) {

		/*
		Integer result = getRiskValue(currentCell.getPosition());
		
		Vector2 currentPos = currentCell.getPosition();
		while(cameFrom.containsKey(currentPos)) {
			currentPos = cameFrom.get(currentPos);
			cameFrom.remove(currentPos);
			result += getRiskValue(currentPos);
		}
		*/
		Integer result = 0;
		Vector2 currentPos = currentCell.getPosition();
		while(cameFrom.containsKey(currentPos)) {
			result = 0;
		}
		return result;
	}
	
	private Integer h(Vector2 position) {
		
		// Calculate heuristic function euclidian and rounding to integer
		// https://es.wikipedia.org/wiki/Distancia_euclidiana
		/*
		int x = destination.getX() - position.getX();
		int y = destination.getY() - position.getY();
		
		double xPow = Math.pow(x, 2);
		double yPow = Math.pow(y, 2);
		
		// Otra posible heurística sería calcular una media:
		// La media entre el camino mas corto * 1 y el camino mas corto * 9
		return (int) Math.sqrt(xPow+yPow);
		*/
		
		
		// Otra heurística: taxicab distance (Manhattan)
		// Es la distancia entre dos puntos es la suma de las diferencias (absolutas) de sus coordenadas.
		
		int x = destination.getX() - position.getX();
		int y = destination.getY() - position.getY();
		
		return Math.abs(x+y);
	}

	private Integer getRiskValue(Vector2 cell) {
		return riskMap[cell.getX()][cell.getY()];
	}

	private boolean isOutOfBounds(int row, int col) {
		return (col >= maxCols || col < 0) || (row >= maxRows || row < 0);
	}

	private Set<Cell> getAdjacentCells(Cell currentCell) {
		Set<Cell> adjacentCells = new HashSet<>();
		
		int row = currentCell.getPosition().getY();
		int col = currentCell.getPosition().getX();
		
		int right = col+1;
		int left = col-1;
		int up = row-1;
		int down = row+1;
		
		
		if(!isOutOfBounds(row, right)) {
			Vector2 pos = new Vector2(row, right);
			adjacentCells.add(new Cell(pos));
		}
		
		if(!isOutOfBounds(row, left)) {
			Vector2 pos = new Vector2(row, left);
			adjacentCells.add(new Cell(pos));
		}
		
		if(!isOutOfBounds(up, col)) {
			Vector2 pos = new Vector2(up, col);
			adjacentCells.add(new Cell(pos));
		}
		
		if(!isOutOfBounds(down, col)) {
			Vector2 pos = new Vector2(down, col);
			adjacentCells.add(new Cell(pos));
		}
		
		return adjacentCells;
	}
	
	private Queue<Vector2> getAdjacentCells(Vector2 position) {
		return getAdjacentCells(position.getY(), position.getX());	
	}
	
	private Queue<Vector2> getAdjacentCells(int row, int col) {
		
		
		Queue<Vector2> adjacentCells = new LinkedList<>();
		
		int right = col+1;
		int left = col-1;
		int up = row-1;
		int down = row+1;
		
		if(!isOutOfBounds(row, right)) {
			adjacentCells.add(new Vector2(row, right));
		}
		
		if(!isOutOfBounds(row, left)) {
			adjacentCells.add(new Vector2(row, left));
		}
		
		if(!isOutOfBounds(up, col)) {
			adjacentCells.add(new Vector2(up, col));
		}
		
		if(!isOutOfBounds(down, col)) {
			adjacentCells.add(new Vector2(down, col));
		}
		
		return adjacentCells;
	}

	
	/*
	public int solveA() {
		
		shortestPaths.put(destination, riskMap[destination.getY()][destination.getX()]);
		System.out.println(shortestPaths);
		
		return 0;
	}*/
}
