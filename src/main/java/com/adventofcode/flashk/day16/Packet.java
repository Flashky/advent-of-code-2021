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
	private Integer subpacketsLength = 0;
	private Integer subpacketsNumber = 0;
	
	// Only literals
	private Long literal;
	
	private Integer length;
	
	private List<Packet> subpackets = new ArrayList<>();
	
	public void addSubpacket(Packet subpacket) {
		
		switch(lengthTypeId) {
		
			case SUBPACKETS_NUMBER: {
				if(subpackets.size() == subpacketsNumber) {
					throw new IllegalArgumentException("Packet has reached maximum number of subpackets (1).");
				}
				this.subpacketsLength += subpacket.getTotalLength();
			} break;
			
			case SUBPACKETS_LENGTH: {
				if(subpacket.getTotalLength() > this.getSubpacketsLength()) {
					throw new IllegalArgumentException("Packet has reached maximum number of subpackets (2).");
				}
				this.subpacketsNumber++;
			} break;
			
			default: throw new IllegalArgumentException("Cannot add subpackets to a literal");
		}
		
		this.subpackets.add(subpacket);
		
	}
	
	public Integer getTotalLength() {
		return length + subpacketsLength;
	}
	
}
