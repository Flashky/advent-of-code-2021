package com.adventofcode.flashk.day15.star;

import java.util.PriorityQueue;

// https://stackabuse.com/graphs-in-java-a-star-algorithm/
// https://en.wikipedia.org/wiki/A*_search_algorithm
public class AStar {

	public static Node aStar(Node start, Node target) {
		
	    PriorityQueue<Node> closedList = new PriorityQueue<>();
	    PriorityQueue<Node> openList = new PriorityQueue<>();

	    start.setF(start.getG() + start.calculateHeuristic(target));
	    openList.add(start);
	 
	    while(!openList.isEmpty()) {
	    	
	        Node n = openList.peek();
	        if(n == target){
	            return n;
	        }

	        for(Node.Edge edge : n.neighbors){
	            Node m = edge.node;
	            int totalWeight = n.getG() + edge.weight;

	            if(!openList.contains(m) && !closedList.contains(m)){
	                m.setParent(n);
	                m.setG(totalWeight);
	                m.setF(m.getG() + m.calculateHeuristic(target));
	                openList.add(m);
	            } else {
	                if(totalWeight < m.getG()){
	                    m.setParent(n);
	                    m.setG(totalWeight);
	                    m.setF(m.getG() + m.calculateHeuristic(target));

	                    if(closedList.contains(m)){
	                        closedList.remove(m);
	                        openList.add(m);
	                    }
	                }
	            }
	        }

	        openList.remove(n);
	        closedList.add(n);
	    }
	    
	    return null;
	    
	}
}
