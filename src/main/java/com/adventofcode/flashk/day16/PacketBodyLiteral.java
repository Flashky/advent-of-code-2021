package com.adventofcode.flashk.day16;

import com.adventofcode.flashk.common.BaseUtil;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PacketBodyLiteral extends PacketBody {

	private final static Integer LENGTH_NUMBER_GROUP = 5;
	
	private Integer literal; 
	private String binaryOutput;
	
	public PacketBodyLiteral(PacketHeader header, String binaryInput) {

		// Se excluye la cabecera de la trama
		Integer startPos = header.getHeaderSize();
		Integer endPos = 0;
		
		binaryInput = binaryInput.substring(startPos);
		StringBuilder numberBuilder = new StringBuilder();

		boolean lastGroup = false;

		// Se calcula el número
		while(!binaryInput.isEmpty() && !lastGroup) {
			
			startPos = endPos;
			endPos += LENGTH_NUMBER_GROUP;
			String group = binaryInput.substring(startPos, endPos);

			if(group.charAt(0) == '0') {
				lastGroup = true;
			}
			
			String groupNumber = group.substring(1,LENGTH_NUMBER_GROUP);
			numberBuilder.append(groupNumber);
			binaryInput = binaryInput.substring(endPos);

		}
		
		literal = BaseUtil.binaryToDec(numberBuilder.toString());
		binaryOutput = binaryInput;

	}
	
}
