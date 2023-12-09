package com.adventofcode.flashk.day08;
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
import com.adventofcode.flashk.common.test.utils.Input;

@DisplayName(TestDisplayName.DAY_08)
@TestMethodOrder(OrderAnnotation.class)
public class Day08Test extends PuzzleTest {

	private final static String INPUT_FOLDER = "day_08";
	
	private final static String INPUT_FILE_DEBUG_1625 = "debug_1625.input";
	private final static String INPUT_FILE_DEBUG_4315 = "debug_4315.input";
	private final static String INPUT_FILE_DEBUG_4548 = "debug_4548.input";
	
	@BeforeAll
	public static void beforeAll() {
		Timer.printHeader(TestDisplayName.DAY_08);
	}
	
	@Test
	@Order(1)
	@Tag(TestTag.PART_ONE)
	@Tag(TestTag.SAMPLE)
	@DisplayName(TestDisplayName.PART_ONE_SAMPLE)
	public void testSolvePart1Sample() {
		
		System.out.print("1 | sample | ");
		
		// Read input file
		List<String> inputs = Input.readStringLines(INPUT_FOLDER, TestFilename.INPUT_FILE_SAMPLE);
		
		SevenSegmentSearch segmentSearch = new SevenSegmentSearch(inputs);
		int result = segmentSearch.solveA();
		
		assertEquals(26, result);
	}
	
	@Test
	@Order(2)
	@Tag(TestTag.PART_ONE)
	@Tag(TestTag.INPUT)
	@DisplayName(TestDisplayName.PART_ONE_INPUT)
	public void testSolvePart1Input() {
		
		System.out.print("1 | input  | ");
		
		// Read input file
		List<String> inputs = Input.readStringLines(INPUT_FOLDER, TestFilename.INPUT_FILE);
		
		SevenSegmentSearch segmentSearch = new SevenSegmentSearch(inputs);
		int result = segmentSearch.solveA();
		
		assertEquals(519, result);
		
	}
	
	@Test
	@Order(3)
	@Tag(TestTag.PART_TWO)
	@Tag(TestTag.SAMPLE)
	@DisplayName(TestDisplayName.PART_TWO_SAMPLE)
	public void testSolvePart2SingleSample() {
		
		System.out.print("2 | single | ");
		
		// Read input file
		List<String> inputs = Input.readStringLines(INPUT_FOLDER, TestFilename.INPUT_FILE_SINGLE_SAMPLE);
		
		SevenSegmentSearch segmentSearch = new SevenSegmentSearch(inputs);
		int result = segmentSearch.solveB();
		
		assertEquals(5353, result);
	}
	
	@Test
	@Order(4)
	@Tag(TestTag.PART_TWO)
	@Tag(TestTag.DEBUG)
	@DisplayName(TestDisplayName.PART_TWO_DEBUG + " (1625)")
	public void testSolvePart2SingleSample1625() {
		
		System.out.print("2 | single | ");
		
		// Read input file
		List<String> inputs = Input.readStringLines(INPUT_FOLDER, INPUT_FILE_DEBUG_1625);
		
		SevenSegmentSearch segmentSearch = new SevenSegmentSearch(inputs);
		int result = segmentSearch.solveB();
		
		assertEquals(1625, result);
	}
	
	@Test
	@Order(5)
	@Tag(TestTag.PART_TWO)
	@Tag(TestTag.DEBUG)
	@DisplayName(TestDisplayName.PART_TWO_DEBUG + " (4315)")
	public void testSolvePart2SingleSample4315() {
		
		System.out.print("2 | single | ");
		
		// Read input file
		List<String> inputs = Input.readStringLines(INPUT_FOLDER, INPUT_FILE_DEBUG_4315);
		
		SevenSegmentSearch segmentSearch = new SevenSegmentSearch(inputs);
		int result = segmentSearch.solveB();
		
		assertEquals(4315, result);
	}
	
	@Test
	@Order(6)
	@Tag(TestTag.PART_TWO)
	@Tag(TestTag.DEBUG)
	@DisplayName(TestDisplayName.PART_TWO_DEBUG + " (4548)")
	public void testSolvePart2SingleSample4548() {
		
		System.out.print("2 | single | ");
		
		// Read input file
		List<String> inputs = Input.readStringLines(INPUT_FOLDER, INPUT_FILE_DEBUG_4548);
		
		SevenSegmentSearch segmentSearch = new SevenSegmentSearch(inputs);
		int result = segmentSearch.solveB();
		
		assertEquals(4548, result);
	}
	
	@Test
	@Order(7)
	@Tag(TestTag.PART_TWO)
	@Tag(TestTag.SAMPLE)
	@DisplayName(TestDisplayName.PART_TWO_SAMPLE)
	public void testSolvePart2Sample() {
		
		System.out.print("2 | input  | ");
		
		// Read input file
		List<String> inputs = Input.readStringLines(INPUT_FOLDER, TestFilename.INPUT_FILE_SAMPLE);
		
		SevenSegmentSearch segmentSearch = new SevenSegmentSearch(inputs);
		int result = segmentSearch.solveB();
		
		assertEquals(61229, result);
		
	}
	
	@Test
	@Order(8)
	@Tag(TestTag.PART_TWO)
	@Tag(TestTag.INPUT)
	@DisplayName(TestDisplayName.PART_TWO_INPUT)
	public void testSolvePart2Input() {
		
		System.out.print("2 | input  | ");
		
		// Read input file
		List<String> inputs = Input.readStringLines(INPUT_FOLDER, TestFilename.INPUT_FILE);
		
		SevenSegmentSearch segmentSearch = new SevenSegmentSearch(inputs);
		int result = segmentSearch.solveB();
		
		assertEquals(1027483, result);
	}

}
