package com.adventofcode.flashk.day15.star;

import java.util.ArrayList;
import java.util.List;

import com.adventofcode.flashk.common.Vector2;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Node implements Comparable<Node> {

	public Node parent = null;
	public List<Edge> neighbors;
	
	// Evaluation functions
    private int f = Integer.MAX_VALUE;
    private int g = Integer.MAX_VALUE;
    
    // Hardcoded heuristic
    public int h;
    
    // Additional data
    private Vector2 position;
    
    Node(int h){
        this.h = h;
        this.neighbors = new ArrayList<>();
    }
    
    Node(Vector2 position){
    	this.calculateHeuristic(position);
        this.neighbors = new ArrayList<>();
    }
    
    @Override
    public int compareTo(Node n) {
    	return Integer.compare(this.f, n.f);
    }
	
    public void addBranch(int weight, Node node){
    	Edge newEdge = new Edge(weight, node);
    	neighbors.add(newEdge);
    }
	
    public int calculateHeuristic(Node target){
			
		  int x = target.position.getX() - position.getX();
		  int y = target.position.getY() - position.getY();
		  h = Math.abs(x+y);
			
		  return h;
    }
	  
		
	  public int calculateHeuristic(Vector2 position){
			
		  int x = position.getX() - position.getX();
		  int y = position.getY() - position.getY();
		  h = Math.abs(x+y);
			
		  return h;
	  }
	  
	  public static class Edge {
	        Edge(int weight, Node node){
	              this.weight = weight;
	              this.node = node;
	        }
	
	        public int weight;
	        public Node node;
	  }
}
