package com.adventofcode.flashk.day16;

import java.util.List;

import com.adventofcode.flashk.common.BaseUtil;

public class PacketDecoder {
	
	//private final static String PACKET_VERSION_6 = "110";
	private final static String PACKET_ID_LITERAL_VALUE = "100";
	private final static String LENGTH_TYPE_ID_SUBPACKETS_LENGTH = "0";
	private final static String LENGTH_TYPE_ID_SUBPACKETS_NUMBER = "1";

	
	// Cualquier PACKET_ID que no sea 100 será un operador
	// La parte A no especifica que operador es, de momento no importa.
	
	// Aparentemente, el primer paquete que aparece siempre ha de ser un operador.
	// Los operadores pueden tener literales, pero los literales son nodos hoja, no pueden tener más paquetes por debajo.

	// Implicaciones: expression trees?
	// https://www.geeksforgeeks.org/expression-tree/
	// La pregunta es, ¿cómo generar el árbol a partir de la cadena?
	
	// Parte 1: 
	// Obtener todos los version numbers y sumarlos.
	private String hexadecimalInput;
	private String binaryInput;
	
	public PacketDecoder(List<String> inputs) {
		
		hexadecimalInput = inputs.get(0);
		binaryInput = BaseUtil.hexToBinaryPadLeft(hexadecimalInput);
		System.out.println();
		System.out.println("Input (hex): " + hexadecimalInput);
		System.out.println("Input (bin): " + binaryInput);
		System.out.println();
		
	}

	public Integer solveA2() {
		return decode(binaryInput);
	}
	
	private Integer decode(String binaryInput) {
		
		if(binaryInput.isEmpty()) {
			return 0;
		} else if(binaryInput.matches("[0]*")) {
			System.out.println("No more bits to decode");
			System.out.println();
			return 0;
		}
		
		Integer result = 0;
		
		PacketHeader header = new PacketHeader(binaryInput);
		result += header.getVersion();
		
		if(header.isLiteralPacket()) {
			
			System.out.print("Literal: ");
			System.out.println(binaryInput);
			
			PacketBodyLiteral bodyLiteral = new PacketBodyLiteral(header, binaryInput);
			String binaryOutput = bodyLiteral.getBinaryOutput();
			
			result += decode(binaryOutput);
			
		} else if(LengthTypeId.SUBPACKETS_LENGTH.equals(header.getLengthTypeId())){
			
			System.out.print("Packet length: "); 
			System.out.println(binaryInput);
			
			PacketBodyOperatorLength bodyOperator = new PacketBodyOperatorLength(header, binaryInput);

			// Hay dos cadenas, la que cubre la longitud del operador, y lo que hay a la derecha de esta
			String binarySubpacket = bodyOperator.getBinarySubpackets();
			String rightBinaryOutput = bodyOperator.getBinaryOutput();
			
			result += decode(binarySubpacket);
			result += decode(rightBinaryOutput);
			
		} else if(LengthTypeId.SUBPACKETS_NUMBER.equals(header.getLengthTypeId())){
			
			System.out.print("Packet number: ");
			System.out.println(binaryInput);
			PacketBodyOperatorNumber bodyOperator = new PacketBodyOperatorNumber(header, binaryInput);
			String binaryOutput = bodyOperator.getBinaryOutput();

			// Este es el caso difícil, sabemos que hay X paquetes, pero no cuantos hay.
			for(int i = 0; i < bodyOperator.getSubpacketsSize(); i++) {
				// esto está fallando
				result += decode(binaryOutput);
			}
			
		}
		
		return result;
	}


}
