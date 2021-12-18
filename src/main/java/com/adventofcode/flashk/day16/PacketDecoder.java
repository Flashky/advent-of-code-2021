package com.adventofcode.flashk.day16;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import com.adventofcode.flashk.common.BaseUtil;

// Ideas para resolver:
// https://www.geeksforgeeks.org/expression-tree/
// http://www.it.uc3m.es/java/2011-12/units/arboles/guides/2/guide_es.html
// El puzzle es también un árbol de expresión. 
// Pero a diferencia de los árboles de expresión habituales, que son postfijos, este es prefijo.
// Esto implica que primero meteremos operadores en la pila, cuando demos con un literal, sacaremos
// el operador de la pila, y le asignaremos el literal.
public class PacketDecoder {
	
	private final static Integer BITS_PACKET_VERSION = 3;
	private final static Integer BITS_PACKET_ID = 3;
	private final static Integer BITS_PACKET_LENGTH_TYPE_ID  = 1;
	private final static Integer BITS_SUBPACKETS_LENGTH = 15;
	private final static Integer BITS_SUBPACKETS_NUMBER = 11;
	private final static Integer BITS_NUMBER_GROUP = 5;


	
	// Cualquier PACKET_ID que no sea 100 será un operador
	// La parte A no especifica que operador es, de momento no importa.
	
	// El primer paquete que aparece siempre ha de ser un operador.
	// Los operadores pueden tener literales, pero los literales son nodos hoja, no pueden tener más paquetes por debajo.

	private Queue<Packet> packets;
	private Integer version = 0;
	
	public PacketDecoder(List<String> inputs) {

		// Step 1 - Convert from hex to binary
		String hexadecimalInput = inputs.get(0);
		String binaryInput = BaseUtil.hexToBinaryPadLeft(hexadecimalInput);
		
		/*
		System.out.println();
		System.out.println("Input (hex): " + hexadecimalInput);
		System.out.println("Input (bin): " + binaryInput);
		System.out.println();
		*/
		// Step 2 - Process all the binary packets and add it to a queue.
		packets = binaryToPackets(binaryInput);
		
		// Step 3 - Process the queue, polling elements, moving into a stack and building precedences.
		// Only for part 2

	}

	private Queue<Packet> binaryToPackets(String binaryInput) {

		Queue<Packet> packets = new LinkedList<>();
		
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

	public Integer solveB() {
		
		// In a postfix
		Stack<Packet> packetStack = new Stack<>();
		
		while(!packets.isEmpty()) {
			Packet currentPacket = packets.poll();
			
			switch(currentPacket.getTypeId()) { 
			}
		}
		return null;
	}

}
