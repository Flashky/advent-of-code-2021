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
	
	//private Vector2 origin = new Vector2(0,0);
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

	public int solveA() {
		
		//Graph<Node> liveGraph = graph.asGraph();
		origin.setRisk(0);
		
		PriorityQueue<Node> queue = new PriorityQueue<>();
		queue.add(origin);

		while(!queue.isEmpty()) {
			
			Node minNode = queue.poll();
			minNode.setVisited(true);
			//System.out.println(minNode.getPosition().toString());
			
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
		
		for(Vector2 position : adjacencyList.keySet()) {
			
			Node node = new Node(position);
			
			if(position.getX() == 0 && position.getY() == 0) {
				origin = node;
			}
			
			graph.addNode(node);
			Integer edgeValue = getRiskValue(position);
			
			// Nodes adjacent to current position
			for(Vector2 adjacentVector : adjacencyList.get(position)) {
				Node adjacentNode = new Node(adjacentVector);
				graph.addNode(adjacentNode);
				graph.putEdgeValue(adjacentNode, node, edgeValue);
				
				if (adjacentVector.getX() == maxX-1 && adjacentVector.getY() == maxY - 1) {
					destination = adjacentNode;
				}
			}
		}
	}
	/*
	private Set<Node> getAdjacentNodes(Node position) {
		
	}*/
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
