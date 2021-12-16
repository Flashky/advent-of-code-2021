package com.adventofcode.flashk.day16;

import com.adventofcode.flashk.common.BaseUtil;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PacketBodyOperatorNumber extends PacketBody {

	private final static Integer LENGTH_SUBPACKETS_NUMBER = 11;
	
	private Integer subpacketsSize;
	private String binaryOutput;

	public PacketBodyOperatorNumber(PacketHeader header, String binaryInput) {

		// Se excluye la cabecera de la trama
		Integer startPos = header.getHeaderSize();
		Integer endPos = startPos + LENGTH_SUBPACKETS_NUMBER;

		String subpacketsSizeBin = binaryInput.substring(startPos,endPos);
		
		subpacketsSize = BaseUtil.binaryToDec(subpacketsSizeBin);
		binaryOutput = binaryInput.substring(endPos);

	}
	
}
