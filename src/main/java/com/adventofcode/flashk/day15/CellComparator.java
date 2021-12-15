package com.adventofcode.flashk.day15;

import java.util.Comparator;

public class CellComparator implements Comparator<Cell>{
    
    // Overriding compare()method of Comparator 
                // for descending order of cgpa
    public int compare(Cell s1, Cell s2) {
    	return Integer.compare(s1.getFScore(), s2.getFScore());
    }
}
