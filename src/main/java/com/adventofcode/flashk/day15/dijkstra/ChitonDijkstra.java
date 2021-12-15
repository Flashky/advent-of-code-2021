package com.adventofcode.flashk.day15.dijkstra;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.adventofcode.flashk.common.Vector2;
import com.google.common.graph.MutableValueGraph;
import com.google.common.graph.ValueGraphBuilder;

public class ChitonDijkstra {

	private Integer[][] riskMap;
	private int maxCols;
	private int maxRows;
	private MutableValueGraph<Vector2,Integer> graph;
	
	public ChitonDijkstra(List<String> inputs) {
		
		maxCols = inputs.get(0).length();
		maxRows = inputs.size();

		// Initialize heat map values
		riskMap = new Integer[maxRows][maxCols];
		
		Vector2 destination = new Vector2(maxCols-1, maxRows-1);
		
		Map<Vector2, List<Vector2>> adjacencyList = new HashMap<>();
		
		for(int row = 0; row < inputs.size(); row++) {
			
			String[] numbers = inputs.get(row).split("|");
			
			for(int col = 0; col < maxCols; col++) {
				riskMap[row][col] = Integer.valueOf(numbers[col]);
				Vector2 currentPos = new Vector2(col, row);
				adjacencyList.put(currentPos, getAdjacentPositions(currentPos));
			}
		}
		
		// Set the destination
		destination = new Vector2(maxCols-1, maxRows-1);

		// Initialize Dijkstra
		graph = ValueGraphBuilder.directed().build();
		
		for(Vector2 position : adjacencyList.keySet()) {
			
			graph.addNode(position);
			Integer edgeValue = getRiskValue(position);
			
			// Nodes adjacent to current position
			List<Vector2> adjacentVectors = adjacencyList.get(position);
			for(Vector2 adjacentVector : adjacentVectors) {
				graph.addNode(adjacentVector);
				graph.putEdgeValue(adjacentVector, position, edgeValue);
				//graph.addEdge(adjacentVector, position, edgeValue);
			}
			
			
		}
		System.out.println("finished");
	}

	private List<Vector2> getAdjacentPositions(Vector2 position) {
		return getAdjacentPositions(position.getY(), position.getX());
	}
	
	private List<Vector2> getAdjacentPositions(int row, int col) {
		
		List<Vector2> adjacentCells = new ArrayList<>();
		
		int right = col+1;
		int left = col-1;
		int up = row-1;
		int down = row+1;
		
		if(!isOutOfBounds(row, right)) {
			adjacentCells.add(new Vector2(right, row));
		}
		
		if(!isOutOfBounds(row, left)) {
			adjacentCells.add(new Vector2(left, row));
		}
		
		if(!isOutOfBounds(up, col)) {
			adjacentCells.add(new Vector2(col, up));
		}
		
		if(!isOutOfBounds(down, col)) {
			adjacentCells.add(new Vector2(col, down));
		}
		
		return adjacentCells;
	}
	
	private boolean isOutOfBounds(int row, int col) {
		return (col >= maxCols || col < 0) || (row >= maxRows || row < 0);
	}
	
	private Integer getRiskValue(Vector2 cell) {
		return riskMap[cell.getX()][cell.getY()];
	}
}
