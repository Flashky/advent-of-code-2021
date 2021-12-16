package com.adventofcode.flashk.day16;

import lombok.Getter;

public enum LengthTypeId {

	SUBPACKETS_LENGTH("0"),
	SUBPACKETS_NUMBER("1");
	
	@Getter
	private String value;
	
	private LengthTypeId(String value) {
		this.value = value;
	}
	
	public static LengthTypeId fromBinValue(String value) {
		switch(value) {
			case "0": return SUBPACKETS_LENGTH;
			case "1": return SUBPACKETS_NUMBER;
			default: return null;
		}
	}
	
}
