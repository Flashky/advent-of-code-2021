package com.adventofcode.flashk.day4;

import lombok.Getter;
import lombok.Setter;

@Getter
public class Cell {

	private Integer number;
	
	@Setter
	private Boolean isSelected = false;
	
	public Cell(Integer number) {
		
		this.number = number;
	}
	
}
