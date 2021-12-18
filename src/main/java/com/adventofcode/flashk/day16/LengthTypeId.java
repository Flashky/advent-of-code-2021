package com.adventofcode.flashk.day16;

import com.adventofcode.flashk.common.BaseUtil;

public enum LengthTypeId {

	SUBPACKETS_LENGTH,
	SUBPACKETS_NUMBER;
	
	public static LengthTypeId fromId(Integer id) {
		
		switch(id) {
			case 0: return SUBPACKETS_LENGTH;
			case 1: return SUBPACKETS_NUMBER;
			default: return null;
		}
	}
	
	public static LengthTypeId fromId(String binaryId) {
		return LengthTypeId.fromId(BaseUtil.binaryToDecInteger(binaryId));
	}
	
}
