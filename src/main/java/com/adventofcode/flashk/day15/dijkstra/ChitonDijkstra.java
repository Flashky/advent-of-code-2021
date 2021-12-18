package com.adventofcode.flashk.day15.dijkstra;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

import com.adventofcode.flashk.common.Vector2;
import com.google.common.graph.MutableValueGraph;
import com.google.common.graph.ValueGraphBuilder;

public class ChitonDijkstra {

	private Integer[][] riskMap;
	private int maxX;
	private int maxY;
	
	private MutableValueGraph<Node,Integer> graph;
	private Node origin;
	private Node destination;
	
	public ChitonDijkstra(List<String> inputs) {
		
		maxX = inputs.get(0).length();
		maxY = inputs.size();

		// Initialize heat map values
		riskMap = new Integer[maxY][maxX];
		
		Map<Vector2, List<Vector2>> adjacencyList = new HashMap<>();
		
		
		for(int y = 0; y < maxY; y++) {
			
			String[] numbers = inputs.get(y).split("|");
			
			for(int x = 0; x < maxX; x++) {
				riskMap[y][x] = Integer.valueOf(numbers[x]);
				Vector2 currentPos = new Vector2(x, y);
				adjacencyList.put(currentPos, getAdjacentPositions(currentPos));
			}
		}

		// Initialize graph
		buildValueGraph(adjacencyList);

	}
	
	public ChitonDijkstra(List<String> inputs, int tileSize) {
		
		int initialMaxX = inputs.get(0).length();
		int initialMaxY = inputs.size();
		
		maxX = initialMaxX * tileSize;
		maxY = initialMaxY * tileSize;
		
		// Initialize heat map values
		riskMap = new Integer[maxY][maxX];
		
		Map<Vector2, List<Vector2>> adjacencyList = new HashMap<>();
		
		for(int y = 0; y < maxY; y++) {
			
			// Index that iterates from 0 to max number rows.
			int numberRow = y % initialMaxY;
			String[] numbers = inputs.get(numberRow).split("|");
			
			// tile 0: y = 0-9
			// tile 1: y = 10-19
			// tile 2: y = 20-29
			// tile 3: y = 30-39
			// tile 4: y = 40-49
			
			// Idea:
			// 1. Calcular el n�mero de tileY en el que estamos [0 - tileSize-1]
			// 2. Calcular el n�mero de tileX en el que estamos [0 - tileSize-1]
			// 3. Sumar: tileIncrement = tileY+tileX
			// 3. riskMap[y][x] = Integer.valueOf(numbers[x]) + tileIncrement 
			
			// Formula para determinar tileY/tileX?
			// Para un array de 10x10, divisi�n qued�ndo solo la parte entera: 
			// [0-9] / 10 = 0
			// [10-19] / 10 = 1
			// [20-29] / 10 = 2
			// [30-39] / 10 = 3
			// [40-49] / 10 = 4

			// �Y si el array es de tama�o 5?
			// [0-4] / 5 = 0
			// [5-9] / 5 = 1
			// Y as�.
			
			// Por lo tanto, la f�rmula es: 
			// tileY = y / initialMaxY
			// tileX = x / initialMaxX
			
			int tileY = y / initialMaxY;
			
			for(int x = 0; x < maxX; x++) {
				
				int tileX = x / initialMaxX;
				int riskIncrement = tileY+tileX;
				
				// Index that iterates from 0 to max number cols.
				int numberCol = x % initialMaxX;
				
				// tile 0: x = 0-9
				// tile 1: x = 10-19
				// tile 2: x = 20-29
				// tile 3: x = 30-39
				// tile 4: x = 40-49

				riskMap[y][x] = Integer.valueOf(numbers[numberCol]) + riskIncrement;
				Vector2 currentPos = new Vector2(x, y);
				adjacencyList.put(currentPos, getAdjacentPositions(currentPos));
				
			}
		}
		
		// Initialize graph
		buildValueGraph(adjacencyList);
		
		
	}

	/**
	 * Uses Dijkstra algorithm to solve part 1.
	 * @return The cost to reach the destination
	 * @see 
	 * <a href="https://es.wikipedia.org/wiki/Algoritmo_de_Dijkstra">Algoritmo de Dijkstra (Wikipedia)</a><br>
	 * <a href="https://www.youtube.com/watch?v=EFg3u_E6eHU">C�mo funciona el Algoritmo de Dijkstra (Youtube)</a>
	 */
	public int solveA() {

		origin.setRisk(0);
		
		PriorityQueue<Node> queue = new PriorityQueue<>();
		queue.add(origin);

		while(!queue.isEmpty()) {
			
			Node minNode = queue.poll();
			minNode.setVisited(true);
			
			Set<Node> adjacentNodes = graph.successors(minNode);
			for(Node adjacentNode : adjacentNodes) {
				if(!adjacentNode.isVisited()) {
					Integer risk = graph.edgeValueOrDefault(minNode, adjacentNode, Integer.MAX_VALUE);
					if(adjacentNode.getRisk() > minNode.getRisk() + risk) {
						adjacentNode.setRisk(minNode.getRisk() + risk);
						adjacentNode.setParent(minNode);
						queue.add(adjacentNode);
					}
				}
			}
		}
		
		return destination.getRisk();
	}


	private void buildValueGraph(Map<Vector2, List<Vector2>> adjacencyList) {
		
		graph = ValueGraphBuilder.directed().build();
		
		// Temporal store to avoid duplicating nodes
		Map<Node,Node> createdNodes = new HashMap<>();

		for(Vector2 position : adjacencyList.keySet()) {
			
			Node node = new Node(position);
			
			if(createdNodes.containsKey(node)) {
				node = createdNodes.get(node);
			} else {
				createdNodes.put(node,node);
			}
			
			if(position.getX() == 0 && position.getY() == 0) {
				origin = node;
			}
			
			graph.addNode(node);
			Integer edgeValue = getRiskValue(position);
			
			// Nodes adjacent to current position
			for(Vector2 adjacentVector : adjacencyList.get(position)) {
				
				Node adjacentNode = new Node(adjacentVector);
				
				if(createdNodes.containsKey(adjacentNode)) {
					adjacentNode = createdNodes.get(adjacentNode);
				} else {
					createdNodes.put(adjacentNode,adjacentNode);
				}
				
				graph.putEdgeValue(adjacentNode, node, edgeValue);
				
				if (adjacentVector.getX() == maxX-1 && adjacentVector.getY() == maxY - 1) {
					destination = adjacentNode;
				}
			}
		}

	}

	private List<Vector2> getAdjacentPositions(Vector2 position) {
		return getAdjacentPositions(position.getY(),position.getX());
	}
	
	private List<Vector2> getAdjacentPositions(int y, int x) {
		
		List<Vector2> adjacentCells = new ArrayList<>();
		
		int right = x+1;
		int left = x-1;
		int up = y-1;
		int down = y+1;
		
		if(!isOutOfBounds(y, right)) {
			adjacentCells.add(new Vector2(right, y));
		}
		
		if(!isOutOfBounds(y, left)) {
			adjacentCells.add(new Vector2(left, y));
		}
		
		if(!isOutOfBounds(up, y)) {
			adjacentCells.add(new Vector2(x, up));
		}
		
		if(!isOutOfBounds(down, y)) {
			adjacentCells.add(new Vector2(x, down));
		}
		
		return adjacentCells;
	}
	
	private boolean isOutOfBounds(int y, int x) {
		return (y >= maxY || y < 0) || (x >= maxX || x < 0);
	}
	
	private Integer getRiskValue(Vector2 cell) {
		return riskMap[cell.getY()][cell.getX()];
	}
}
