package com.adventofcode.flashk.day16;

import com.adventofcode.flashk.common.BaseUtil;

public enum TypeId {

	SUM,
	PRODUCT,
	MINIMUM,
	MAXIMUM,
	LITERAL,
	GREATER_THAN,
	LESS_THAN,
	EQUAL_TO;
	
	public static TypeId fromId(Integer id) {
		
		switch(id) {
			case 0: return SUM;
			case 1: return PRODUCT;
			case 2: return MINIMUM;
			case 3: return MAXIMUM;
			case 4: return LITERAL;
			case 5: return GREATER_THAN;
			case 6: return LESS_THAN;
			case 7: return EQUAL_TO;
			default: return null;
		}
	}
	
	public static TypeId fromId(String binaryId) {
		return TypeId.fromId(BaseUtil.binaryToDecInteger(binaryId));
	}
}
