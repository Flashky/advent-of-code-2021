package com.adventofcode.flashk.day20;

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

@DisplayName(TestDisplayName.DAY_20)
@TestMethodOrder(OrderAnnotation.class)
public class Day20Test extends PuzzleTest {

	private final static String INPUT_FOLDER = "day_20";

	private static final String INPUT_FILE_DEBUG_5326 = "debug_5326.input";
	
	@BeforeAll
	public static void beforeAll() {
		Timer.printHeader(TestDisplayName.DAY_20);
	}

	
	@Test
	@Order(1)
	@Tag(TestTag.PART_ONE)
	@Tag(TestTag.SAMPLE)
	@DisplayName(TestDisplayName.PART_ONE_SAMPLE)
	public void testSolvePart1Sample() {
		
		System.out.print("1 | sample | ");
		
		// Read input file
		List<String> inputs = Util.readStringLines(INPUT_FOLDER, TestFilename.INPUT_FILE_SAMPLE);
		
		TrenchMap trenchMap = new TrenchMap(inputs);
		assertEquals(35, trenchMap.solveA(2));
		
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
		TrenchMap trenchMap = new TrenchMap(inputs);
		System.out.println(trenchMap.solveA(2));
		//assertEquals(35, trenchMap.solveA());
		
		// 5127 -> Too high
		
	}
	
	@Test
	@Order(2)
	@Tag(TestTag.PART_ONE)
	@Tag(TestTag.DEBUG)
	@DisplayName(TestDisplayName.PART_ONE_DEBUG + " (5326)")
	public void testSolvePart1Debug5326() {
		
		System.out.print("1 | debug  | ");
		
		// Read input file
		List<String> inputs = Util.readStringLines(INPUT_FOLDER, INPUT_FILE_DEBUG_5326);
		
		TrenchMap trenchMap = new TrenchMap(inputs);
		assertEquals(5326, trenchMap.solveA(2));


		
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
		
		TrenchMap trenchMap = new TrenchMap(inputs);
		//assertEquals(5326, trenchMap.solveA(50));
		
		System.out.println(trenchMap.solveA(50));
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
		
		TrenchMap trenchMap = new TrenchMap(inputs);
		//assertEquals(5326, trenchMap.solveA(50));
		
		System.out.println(trenchMap.solveA(50));
	}

}
