package com.adventofcode.flashk.day16;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Packet {

	private Integer version;
	private TypeId typeId;
	private LengthTypeId lengthTypeId;
	
	// Only operators
	private Integer subpacketsLength;
	private Integer subpacketsNumber;
	
	// Only literals
	private Long literal;
	
	private Integer length;
	
	private List<Packet> subpackets = new ArrayList<>();
	
	public void addSubpacket(Packet subpacket) {
		this.subpackets.add(subpacket);
	}
	
}
