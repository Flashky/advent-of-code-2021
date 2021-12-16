package com.adventofcode.flashk.day16;

import com.adventofcode.flashk.common.BaseUtil;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PacketBodyOperatorLength extends PacketBody {

	private final static Integer LENGTH_SUBPACKETS_LENGTH = 15;
	
	private Integer subpacketsLength;
	private String binarySubpackets;
	private String binaryOutput;

	public PacketBodyOperatorLength(PacketHeader header, String binaryInput) {

		// Se excluye la cabecera de la trama
		Integer startPos = header.getHeaderSize();
		Integer endPos = startPos + LENGTH_SUBPACKETS_LENGTH;

		String subpacketsLengthBin = binaryInput.substring(startPos,endPos);
		
		subpacketsLength = BaseUtil.binaryToDec(subpacketsLengthBin);
		
		startPos = endPos;
		endPos += subpacketsLength;
		
		binarySubpackets = binaryInput.substring(startPos, endPos);
		binaryOutput = binaryInput.substring(endPos);

	}
	

	
}
