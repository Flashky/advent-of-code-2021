package com.adventofcode.flashk.day16;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Stack;

import com.adventofcode.flashk.common.BaseUtil;

public class PacketDecoder {
	
	private final static Integer BITS_PACKET_VERSION = 3;
	private final static Integer BITS_PACKET_ID = 3;
	private final static Integer BITS_PACKET_LENGTH_TYPE_ID  = 1;
	private final static Integer BITS_SUBPACKETS_LENGTH = 15;
	private final static Integer BITS_SUBPACKETS_NUMBER = 11;
	private final static Integer BITS_NUMBER_GROUP = 5;

	private Deque<Packet> packets;
	private Integer version = 0;
	
	public PacketDecoder(List<String> inputs) {

		// Step 1 - Convert from hex to binary
		String hexadecimalInput = inputs.get(0);
		String binaryInput = BaseUtil.hexToBinaryPadLeft(hexadecimalInput);
		
		// Step 2 - Process all the binary packets and add it to a deque.
		packets = binaryToPackets(binaryInput);

	}

	private Deque<Packet> binaryToPackets(String input) {

		Deque<Packet> packets = new ArrayDeque<>();
		String binaryInput = input;
		
		while(hasPackets(binaryInput)) {
			
			Packet packet = new Packet();
			
			// 3 bits - Packet version
			Integer startPos = 0;
			Integer endPos = BITS_PACKET_VERSION;			
			String versionBin = binaryInput.substring(startPos, endPos);
			packet.setVersion(BaseUtil.binaryToDecInteger(versionBin));
			version += packet.getVersion();
			
			// 3 bits - Packet id
			startPos = endPos;
			endPos += BITS_PACKET_ID;
			String typeIdBin = binaryInput.substring(startPos, endPos);
			packet.setTypeId(TypeId.fromId(typeIdBin));
			
			
			if(!TypeId.LITERAL.equals(packet.getTypeId())) {		
				
				// Only operators 
				
				// 1 bit - Packet length type
				startPos = endPos;
				endPos += BITS_PACKET_LENGTH_TYPE_ID;
				String lengthTypeIdBin = binaryInput.substring(startPos,endPos);
				packet.setLengthTypeId(LengthTypeId.fromId(lengthTypeIdBin));
				
				
				if(packet.getLengthTypeId().equals(LengthTypeId.SUBPACKETS_LENGTH)) {
					
					// 15 bits - Subpackets length (only operators)
					startPos = endPos;
					endPos += BITS_SUBPACKETS_LENGTH;
					String subpacketsLengthBin = binaryInput.substring(startPos, endPos);
					packet.setSubpacketsLength(BaseUtil.binaryToDecInteger(subpacketsLengthBin));
					
				} else if(packet.getLengthTypeId().equals(LengthTypeId.SUBPACKETS_NUMBER)) {
					
					// 11 bits - Subpackets number (only operators)
					startPos = endPos;
					endPos += BITS_SUBPACKETS_NUMBER;
					String subpacketsNumberBin = binaryInput.substring(startPos, endPos);
					packet.setSubpacketsNumber(BaseUtil.binaryToDecInteger(subpacketsNumberBin));
					
				}
				
			} else {
				
				// Only literals 
				
				// n groups of 5 bits - Literal value
				boolean lastGroup = false;

				StringBuilder numberBuilder = new StringBuilder();
				while(!binaryInput.isEmpty() && !lastGroup) {
					
					startPos = endPos;
					endPos += BITS_NUMBER_GROUP;
					String group = binaryInput.substring(startPos, endPos);

					if(group.charAt(0) == '0') {
						lastGroup = true;
					}
					
					String groupNumber = group.substring(1, BITS_NUMBER_GROUP);
					numberBuilder.append(groupNumber);

				}
				
				packet.setLiteral(BaseUtil.binaryToDec(numberBuilder.toString()));
				
			}
			
			// Length of the packet in bits
			packet.setLength(endPos);
			
			packets.add(packet);
			binaryInput = binaryInput.substring(endPos);
			
		}
		
		return packets;
	}

	private boolean hasPackets(String binaryInput) {
		return !binaryInput.isEmpty() && !binaryInput.matches("[0]*");
	}
	
	public Integer solveA() {
		return version;
	}

	public Long solveB() {
		
		// Build expression tree and then evaluate it
		Packet rootPacket = buildExpressionTree();
		return evaluate(rootPacket);

	}

	private Packet buildExpressionTree() {
		
		Stack<Packet> packetStack = new Stack<>();
		
		Packet currentPacket = null;
		
		while(!packets.isEmpty()) {
			
			currentPacket = packets.pollLast();
			
			if(TypeId.LITERAL.equals(currentPacket.getTypeId())) {
				packetStack.push(currentPacket);
			} else if(LengthTypeId.SUBPACKETS_NUMBER.equals(currentPacket.getLengthTypeId())){
	
					// Packet with subpackets by number
					for(int i = 0; i < currentPacket.getSubpacketsNumber(); i++) {
						Packet child = packetStack.pop();
						currentPacket.addSubpacket(child);
					}
	
					packetStack.push(currentPacket);
					
			} else if(LengthTypeId.SUBPACKETS_LENGTH.equals(currentPacket.getLengthTypeId())) {
					
					// Packet with subpackets by length
					int totalLength = 0;
					
					while(totalLength < currentPacket.getSubpacketsLength()) {
						Packet child = packetStack.pop();
						totalLength += child.getTotalLength();
						currentPacket.addSubpacket(child);

					}
					
					packetStack.push(currentPacket);
					
			}
		}
		return currentPacket;
	}

	private Long evaluate(Packet currentPacket) {

		// Leaf nodes
		if(TypeId.LITERAL.equals(currentPacket.getTypeId())) {
			return currentPacket.getLiteral();
		}

		List<Long> literalValues = new ArrayList<>();
		for(Packet subpacket : currentPacket.getSubpackets()) {
			Long literal = evaluate(subpacket);
			literalValues.add(literal);
		}

		return operate(currentPacket.getTypeId(), literalValues);
	}

	private Long operate(TypeId typeId, List<Long> literalValues) {
		
		switch(typeId) {
			case SUM:			return sum(literalValues);
			case PRODUCT: 		return product(literalValues);
			case MINIMUM: 		return min(literalValues);
			case MAXIMUM: 		return max(literalValues);
			case LESS_THAN:		return lessThan(literalValues.get(0), literalValues.get(1));
			case GREATER_THAN:	return greaterThan(literalValues.get(0), literalValues.get(1));
			case EQUAL_TO:		return equalTo(literalValues.get(0), literalValues.get(1));
			default:			throw new IllegalArgumentException("Unknown operation: " + typeId);
		}

	}

	private Long product(List<Long> literalValues) {
		
		Long result = 1L;
		
		for(Long literal : literalValues) {
			result *= literal;
		}
		
		return result;
	}

	private Long sum(List<Long> literalValues) {

		Long result = 0L;
		
		for(Long literal : literalValues) {
			result += literal;
		}
		
		return result;
	}

	private Long min(List<Long> literalValues) {

		Long result = Long.MAX_VALUE;
		
		for(Long literal: literalValues) {
			result =  Math.min(literal, result);
		}
		
		return result;
	}

	private Long max(List<Long> literalValues) {
		
		Long result = Long.MIN_VALUE;
		
		for(Long literal: literalValues) {
			result =  Math.max(literal, result);
		}
		
		return result;
	}

	private Long lessThan(Long a, Long b) {
		return (a < b) ? 1L : 0L; 
	}
	
	private Long greaterThan(Long a, Long b) {
		return (a > b) ? 1L : 0L; 
	}
	

	private Long equalTo(Long a, Long b) {
		return (a.equals(b)) ? 1L : 0L; 
	}

}
