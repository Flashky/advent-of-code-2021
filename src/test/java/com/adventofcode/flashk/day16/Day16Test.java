package com.adventofcode.flashk.day16;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.adventofcode.flashk.common.test.constants.TestDisplayName;
import com.adventofcode.flashk.common.test.constants.TestFilename;
import com.adventofcode.flashk.common.test.constants.TestTag;
import com.adventofcode.flashk.common.test.utils.PuzzleTest;
import com.adventofcode.flashk.common.test.utils.Timer;
import com.adventofcode.flashk.common.test.utils.Util;

@DisplayName(TestDisplayName.DAY_16)
@TestMethodOrder(OrderAnnotation.class)
public class Day16Test extends PuzzleTest {

	private final static String INPUT_FOLDER = "day_16";

	private final static String INPUT_FILE_SAMPLE_LITERAL = "sample_literal.input";
	private final static String INPUT_FILE_SAMPLE_12 = "sample_12.input";
	private final static String INPUT_FILE_SAMPLE_16 = "sample_16.input";
	private final static String INPUT_FILE_SAMPLE_23 = "sample_23.input";
	private final static String INPUT_FILE_SAMPLE_31 = "sample_31.input";
	
	@BeforeAll
	public static void beforeAll() {
		Timer.printHeader(TestDisplayName.DAY_XX);
	}

	
	@Test
	@Order(1)
	@Tag(TestTag.PART_ONE)
	@Tag(TestTag.SAMPLE)
	@DisplayName(TestDisplayName.PART_ONE_SAMPLE)
	public void testSolvePart1Sample() {
		
		System.out.print("1 | sample | ");
		
		// Read input file
		List<String> inputs = Util.readStringLines(INPUT_FOLDER, TestFilename.INPUT_FILE);
		
		PacketDecoder packetDecoder = new PacketDecoder(inputs);
		int result = packetDecoder.solveA2();
		
		//assertEquals(,result);
	}
	
	@Test
	@Order(1)
	@Tag(TestTag.PART_ONE)
	@Tag(TestTag.DEBUG)
	@DisplayName(TestDisplayName.PART_ONE_DEBUG + " - Literal")
	public void testSolvePart1SampleLiteral() {
		
		System.out.print("1 | sample literal | ");
		
		// Read input file
		List<String> inputs = Util.readStringLines(INPUT_FOLDER, INPUT_FILE_SAMPLE_LITERAL);
		
		PacketDecoder packetDecoder = new PacketDecoder(inputs);
		int result = packetDecoder.solveA2();
		assertEquals(6, result);
	}
	
	@Test
	@Order(1)
	@Tag(TestTag.PART_ONE)
	@Tag(TestTag.DEBUG)
	@DisplayName(TestDisplayName.PART_ONE_DEBUG + " - 12")
	public void testSolvePart1Sample12() {
		
		System.out.print("1 | sample 12 | ");
		
		// Read input file
		List<String> inputs = Util.readStringLines(INPUT_FOLDER, INPUT_FILE_SAMPLE_12);
		
		PacketDecoder packetDecoder = new PacketDecoder(inputs);
		int result = packetDecoder.solveA2();
		assertEquals(12, result);
	}
	
	@Test
	@Order(1)
	@Tag(TestTag.PART_ONE)
	@Tag(TestTag.DEBUG)
	@DisplayName(TestDisplayName.PART_ONE_DEBUG + " - 16")
	public void testSolvePart1Sample16() {
		
		System.out.print("1 | sample 16 | ");
		
		// Read input file
		List<String> inputs = Util.readStringLines(INPUT_FOLDER, INPUT_FILE_SAMPLE_16);
		
		PacketDecoder packetDecoder = new PacketDecoder(inputs);
		int result = packetDecoder.solveA2();
		assertEquals(16, result);
	}
	
	
	@Test
	@Order(1)
	@Tag(TestTag.PART_ONE)
	@Tag(TestTag.DEBUG)
	@DisplayName(TestDisplayName.PART_ONE_DEBUG + " - 23")
	public void testSolvePart1Sample23() {
		
		System.out.print("1 | sample 23 | ");
		
		// Read input file
		List<String> inputs = Util.readStringLines(INPUT_FOLDER, INPUT_FILE_SAMPLE_23);
		
		PacketDecoder packetDecoder = new PacketDecoder(inputs);
		int result = packetDecoder.solveA2();
		assertEquals(23, result);
	}
	
	@Test
	@Order(1)
	@Tag(TestTag.PART_ONE)
	@Tag(TestTag.DEBUG)
	@DisplayName(TestDisplayName.PART_ONE_DEBUG + " - 31")
	public void testSolvePart1Sample31() {
		
		System.out.print("1 | sample 31 | ");
		
		// Read input file
		List<String> inputs = Util.readStringLines(INPUT_FOLDER, INPUT_FILE_SAMPLE_31);
		
		PacketDecoder packetDecoder = new PacketDecoder(inputs);
		int result = packetDecoder.solveA2();
		assertEquals(31, result);
	}
	
	@Test
	@Order(2)
	@Tag(TestTag.PART_ONE)
	@Tag(TestTag.INPUT)
	@DisplayName(TestDisplayName.PART_ONE_INPUT)
	public void testSolvePart1Input() {
		
		System.out.print("1 | input  | ");
		
		// Read input file
		List<String> inputs = Util.readStringLines(INPUT_FOLDER, TestFilename.INPUT_FILE);
		
		PacketDecoder packetDecoder = new PacketDecoder(inputs);
		int result = packetDecoder.solveA();
	}
	
	@Test
	@Order(3)
	@Tag(TestTag.PART_TWO)
	@Tag(TestTag.SAMPLE)
	@DisplayName(TestDisplayName.PART_TWO_SAMPLE)
	public void testSolvePart2Sample() {
		
		System.out.print("2 | sample | ");
		
		// Read input file
		List<String> inputs = Util.readStringLines(INPUT_FOLDER, TestFilename.INPUT_FILE_SAMPLE);
		
	}
	
	@Test
	@Order(4)
	@Tag(TestTag.PART_TWO)
	@Tag(TestTag.INPUT)
	@DisplayName(TestDisplayName.PART_TWO_INPUT)
	public void testSolvePart2Input() {
		
		System.out.print("2 | input  | ");
		
		// Read input file
		List<String> inputs = Util.readStringLines(INPUT_FOLDER, TestFilename.INPUT_FILE);
		
	}

}
