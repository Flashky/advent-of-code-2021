package com.adventofcode.flashk.day16;

import com.adventofcode.flashk.common.BaseUtil;

import lombok.Getter;

@Getter
public class PacketHeader {

	private final static Integer LENGTH_PACKET_VERSION = 3;
	private final static Integer LENGTH_PACKET_ID = 3;
	private final static Integer LENGTH_PACKET_LENGTH_TYPE_ID  = 1;

	private final static String PACKET_ID_LITERAL_VALUE = "100";
	
	private Integer version;
	private Integer typeId;
	private LengthTypeId lengthTypeId;
	private Integer headerSize;
	private boolean literalPacket;
	
	public PacketHeader(String binaryInput) {
		
		Integer startPos = 0;
		Integer endPos = LENGTH_PACKET_VERSION;			
		String versionBin = binaryInput.substring(startPos, endPos);
		version = BaseUtil.binaryToDec(versionBin);
		
		startPos = endPos;
		endPos += LENGTH_PACKET_ID;
		String typeIdBin = binaryInput.substring(startPos, endPos);
		typeId = BaseUtil.binaryToDec(typeIdBin);
		
		if(!PACKET_ID_LITERAL_VALUE.equals(typeIdBin)) {
			
			// Operators only
			startPos = endPos;
			endPos += LENGTH_PACKET_LENGTH_TYPE_ID;
			String lengthTypeIdBin = binaryInput.substring(startPos,endPos);
			
			lengthTypeId = LengthTypeId.fromBinValue(lengthTypeIdBin);
			literalPacket = false;
			headerSize = 7;
			
		} else {
			
			// Literals
			literalPacket = true;
			headerSize = 6;
		}
	}
	
}
