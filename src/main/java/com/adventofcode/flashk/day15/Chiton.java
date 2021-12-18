package com.adventofcode.flashk.day15;

import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

public class Chiton {

	private Node[][] riskMap;
	private int maxX;
	private int maxY;

	private Node origin;
	private Node destination;
	
	public Chiton(List<String> inputs) {
		initialize(inputs, 1);
	}
	
	public Chiton(List<String> inputs, int tileSize) {
		initialize(inputs, tileSize);
	}

	private void initialize(List<String> inputs, int tileSize) {
		
		int initialMaxX = inputs.get(0).length();
		int initialMaxY = inputs.size();
		
		maxX = initialMaxX * tileSize;
		maxY = initialMaxY * tileSize;
		
		// Initialize heat map values
		riskMap = new Node[maxY][maxX];
		
		for(int y = 0; y < maxY; y++) {
			
			// Index that iterates from 0 to max number rows.
			int numberRow = y % initialMaxY;
			String[] numbers = inputs.get(numberRow).split("|");
			
			int tileY = y / initialMaxY;
			
			for(int x = 0; x < maxX; x++) {
				
				// Calculate the risk for the n-tile
				int tileX = x / initialMaxX;
				int riskIncrement = tileY+tileX;
				int numberCol = x % initialMaxX;
				int originalRisk = Integer.valueOf(numbers[numberCol]);
				int baseRisk = originalRisk + riskIncrement;
				int modRisk = baseRisk % 10;
				int divRisk = baseRisk / 10;
				int realRisk = modRisk + divRisk;

				riskMap[y][x] = new Node(x, y, realRisk);
				
			}
		}
		
		origin = riskMap[0][0];
		destination = riskMap[maxY-1][maxX-1];
	}

	/**
	 * Uses Dijkstra algorithm to solve the problem.
	 * @return The cost to reach the destination
	 * @see 
	 * <a href="https://es.wikipedia.org/wiki/Algoritmo_de_Dijkstra">Algoritmo de Dijkstra (Wikipedia)</a><br>
	 * <a href="https://www.youtube.com/watch?v=EFg3u_E6eHU">Cómo funciona el Algoritmo de Dijkstra (Youtube)</a>
	 */
	public int solve() {
	
		origin.setTotalRisk(0);
		
		PriorityQueue<Node> queue = new PriorityQueue<>();
		queue.add(origin);

		while(!queue.isEmpty()) {
			
			Node minNode = queue.poll();
			minNode.setVisited(true);

			Set<Node> adjacentNodes = getAdjacentNodesFromArray(minNode);
			
			for(Node adjacentNode : adjacentNodes) {
				if(!adjacentNode.isVisited()) {
					
					// Cost of moving to the adjacent node
					Integer risk = adjacentNode.getRisk();
					// Cost of moving to the adjacent node + total cost to reach to this node
					Integer estimatedRisk = minNode.getTotalRisk() + risk;
					
					if(adjacentNode.getTotalRisk() > estimatedRisk) {
						adjacentNode.setTotalRisk(estimatedRisk);
						adjacentNode.setParent(minNode);
						queue.add(adjacentNode);
					}
				}
			}
		}
		
		return destination.getTotalRisk();
	}
	
	private Set<Node> getAdjacentNodesFromArray(Node node) {
		
		Set<Node> adjacentNodes = new HashSet<>();
		
		int x = node.getX();
		int y = node.getY();
		
		int right = x+1;
		int left = x-1;
		int up = y-1;
		int down = y+1;
		
		// In case of using real nodes, it would be:
		// adjacentNodes.add(new Node(x,y));
		
		if(!isOutOfBounds(y, right)) {
			adjacentNodes.add(riskMap[y][right]);
		}
		
		if(!isOutOfBounds(y, left)) {
			adjacentNodes.add(riskMap[y][left]);
		}
		
		if(!isOutOfBounds(up, y)) {
			adjacentNodes.add(riskMap[up][x]);
		}
		
		if(!isOutOfBounds(down, y)) {
			adjacentNodes.add(riskMap[down][x]);
		}
		
		return adjacentNodes;
	}
	
	private boolean isOutOfBounds(int y, int x) {
		return (y >= maxY || y < 0) || (x >= maxX || x < 0);
	}
	
}
