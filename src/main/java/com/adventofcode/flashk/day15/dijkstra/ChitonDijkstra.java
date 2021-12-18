package com.adventofcode.flashk.day15.dijkstra;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

import com.adventofcode.flashk.common.Vector2;

public class ChitonDijkstra {

	private Integer[][] riskMap;
	private int maxX;
	private int maxY;
	
	// Graph
	private Map<Node, Node> createdNodes = new HashMap<>();
	private Map<Node, Set<Node>> adjacencyList = new HashMap<>();

	private Node origin;
	private Node destination;
	
	public ChitonDijkstra(List<String> inputs) {
		initialize(inputs, 1);
	}
	
	public ChitonDijkstra(List<String> inputs, int tileSize) {
		initialize(inputs, tileSize);
	}

	private void initialize(List<String> inputs, int tileSize) {
		
		int initialMaxX = inputs.get(0).length();
		int initialMaxY = inputs.size();
		
		maxX = initialMaxX * tileSize;
		maxY = initialMaxY * tileSize;
		
		// Initialize heat map values
		riskMap = new Integer[maxY][maxX];
		
		for(int y = 0; y < maxY; y++) {
			
			// Index that iterates from 0 to max number rows.
			int numberRow = y % initialMaxY;
			String[] numbers = inputs.get(numberRow).split("|");
			
			int tileY = y / initialMaxY;
			
			for(int x = 0; x < maxX; x++) {
				
				int tileX = x / initialMaxX;
				int riskIncrement = tileY+tileX;
		
				// Index that iterates from 0 to max number cols.
				int numberCol = x % initialMaxX;
				
				int originalRisk = Integer.valueOf(numbers[numberCol]);
				int baseRisk = originalRisk + riskIncrement;
				int modRisk = baseRisk % 10;
				int divRisk = baseRisk / 10;
				int realRisk = modRisk + divRisk;

				riskMap[y][x] = realRisk;
				//System.out.print(riskMap[y][x]);
				Node currentNode = new Node(new Vector2(x, y));
				currentNode.setRisk(realRisk);
				
				createdNodes.put(currentNode, currentNode);
				
			}
			System.out.println();
		}
		
		System.out.println();
		System.out.println(createdNodes.size());
		
		// Build adjacency list
		for(Node node : createdNodes.keySet()) {
			adjacencyList.put(node, getAdjacentNodes(node));
		}
		
		origin = createdNodes.get(new Node(new Vector2(0,0)));
		destination = createdNodes.get(new Node(new Vector2(maxX-1,maxY-1)));
		
		System.out.println(adjacencyList.size());
		
		// Initialize graph
		//buildValueGraph();
	}

	/**
	 * Uses Dijkstra algorithm to solve part 1.
	 * @return The cost to reach the destination
	 * @see 
	 * <a href="https://es.wikipedia.org/wiki/Algoritmo_de_Dijkstra">Algoritmo de Dijkstra (Wikipedia)</a><br>
	 * <a href="https://www.youtube.com/watch?v=EFg3u_E6eHU">Cómo funciona el Algoritmo de Dijkstra (Youtube)</a>
	 */
	public int solveA() {
		System.out.println();
		//System.out.println("Nodes: " + createdNodes.size());
		//System.out.println("Edges: " +graph.edges().size());
	
		origin.setTotalRisk(0);
		
		PriorityQueue<Node> queue = new PriorityQueue<>();
		queue.add(origin);

		while(!queue.isEmpty()) {
			
			Node minNode = queue.poll();
			minNode.setVisited(true);
			
			//Set<Node> adjacentNodes = graph.successors(minNode);
			Set<Node> adjacentNodes = adjacencyList.get(minNode);
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

/*
	private void buildValueGraph() {
		
		//graph = ValueGraphBuilder.directed().expectedNodeCount(createdNodes.size()).build();
		graph = GraphBuilder.undirected().expectedNodeCount(createdNodes.size()).build();
		
		for(Node node : createdNodes.keySet()) {
			
			// Nodes adjacent to current position
			for(Node adjacentNode : adjacencyList.get(node)) {
				graph.putEdge(node, adjacentNode);
			}
		}
		System.out.println("finished");
	}*/

	private Set<Node> getAdjacentNodes(Node node) {
		
		Set<Node> adjacentNodes = new HashSet<>();
		
		int x = node.getPosition().getX();
		int y = node.getPosition().getY();
		
		int right = x+1;
		int left = x-1;
		int up = y-1;
		int down = y+1;
		
		if(!isOutOfBounds(y, right)) {
			adjacentNodes.add(getCreatedNode(right,y));
		}
		
		if(!isOutOfBounds(y, left)) {
			adjacentNodes.add(getCreatedNode(left,y));
		}
		
		if(!isOutOfBounds(up, y)) {
			adjacentNodes.add(getCreatedNode(x,up));
		}
		
		if(!isOutOfBounds(down, y)) {
			adjacentNodes.add(getCreatedNode(x,down));
		}
		
		return adjacentNodes;
	}
	
	private boolean isOutOfBounds(int y, int x) {
		return (y >= maxY || y < 0) || (x >= maxX || x < 0);
	}
	
	private Node getCreatedNode(int x, int y) {
		Node createdNode = new Node(new Vector2(x,y));
		return createdNodes.get(createdNode);
		
	}
	
}
