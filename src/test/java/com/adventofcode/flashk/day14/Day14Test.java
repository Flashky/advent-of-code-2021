package com.adventofcode.flashk.day14;

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

@DisplayName(TestDisplayName.DAY_14)
@TestMethodOrder(OrderAnnotation.class)
public class Day14Test extends PuzzleTest {

	private final static String INPUT_FOLDER = "day_14";

	@BeforeAll
	public static void beforeAll() {
		Timer.printHeader(TestDisplayName.DAY_14);
	}

	
	@Test
	@Order(5)
	@Tag(TestTag.PART_ONE)
	@Tag(TestTag.SAMPLE)
	@DisplayName(TestDisplayName.PART_ONE_SAMPLE)
	public void testSolvePart1Sample() {
		
		System.out.print("1 | sample | ");
		
		// Read input file
		List<String> inputs = Input.readStringLines(INPUT_FOLDER, TestFilename.INPUT_FILE_SAMPLE);
		ExtendedPolymerization polymerization = new ExtendedPolymerization(inputs);
		
		assertEquals(1588, polymerization.solveA(10));
		
	}
	
	@Test
	@Order(1)
	@Tag(TestTag.PART_ONE)
	@Tag(TestTag.DEBUG)
	@DisplayName(TestDisplayName.PART_ONE_DEBUG + " - 1 iteration")
	public void testSolvePart1SampleDebug1() {
		
		System.out.print("1 | debug 1 | ");
		
		// Read input file
		List<String> inputs = Input.readStringLines(INPUT_FOLDER, TestFilename.INPUT_FILE_SAMPLE);
		ExtendedPolymerization polymerization = new ExtendedPolymerization(inputs);
		
		assertEquals(1L, polymerization.solveA(1));
		
	}
	
	@Test
	@Order(2)
	@Tag(TestTag.PART_ONE)
	@Tag(TestTag.DEBUG)
	@DisplayName(TestDisplayName.PART_ONE_DEBUG + " - 2 iterations")
	public void testSolvePart1SampleDebug2() {
		
		System.out.print("1 | debug 2 | ");
		
		// Read input file
		List<String> inputs = Input.readStringLines(INPUT_FOLDER, TestFilename.INPUT_FILE_SAMPLE);
		ExtendedPolymerization polymerization = new ExtendedPolymerization(inputs);
		
		assertEquals(5L, polymerization.solveA(2));
		
	}
	
	@Test
	@Order(3)
	@Tag(TestTag.PART_ONE)
	@Tag(TestTag.DEBUG)
	@DisplayName(TestDisplayName.PART_ONE_DEBUG + " - 3 iterations")
	public void testSolvePart1SampleDebug3() {
		
		System.out.print("1 | debug 3 | ");
		
		// Read input file
		List<String> inputs = Input.readStringLines(INPUT_FOLDER, TestFilename.INPUT_FILE_SAMPLE);
		ExtendedPolymerization polymerization = new ExtendedPolymerization(inputs);
		
		assertEquals(7L, polymerization.solveA(3));
		
	}
	
	@Test
	@Order(4)
	@Tag(TestTag.PART_ONE)
	@Tag(TestTag.DEBUG)
	@DisplayName(TestDisplayName.PART_ONE_DEBUG + " - 4 iterations")
	public void testSolvePart1SampleDebug4() {
		
		System.out.print("1 | debug 4 | ");
		
		// Read input file
		List<String> inputs = Input.readStringLines(INPUT_FOLDER, TestFilename.INPUT_FILE_SAMPLE);
		ExtendedPolymerization polymerization = new ExtendedPolymerization(inputs);
		
		assertEquals(18L, polymerization.solveA(4));
		
	}
	
	@Test
	@Order(6)
	@Tag(TestTag.PART_ONE)
	@Tag(TestTag.INPUT)
	@DisplayName(TestDisplayName.PART_ONE_INPUT)
	public void testSolvePart1Input() {
		
		System.out.print("1 | input  | ");
		
		// Read input file
		List<String> inputs = Input.readStringLines(INPUT_FOLDER, TestFilename.INPUT_FILE);
		ExtendedPolymerization polymerization = new ExtendedPolymerization(inputs);
		
		assertEquals(3009L, polymerization.solveA(10));
	}
	
	@Test
	@Order(7)
	@Tag(TestTag.PART_TWO)
	@Tag(TestTag.SAMPLE)
	@DisplayName(TestDisplayName.PART_TWO_SAMPLE)
	public void testSolvePart2Sample() {
		
		System.out.print("2 | sample | ");
		
		// Read input file
		List<String> inputs = Input.readStringLines(INPUT_FOLDER, TestFilename.INPUT_FILE_SAMPLE);
		ExtendedPolymerization polymerization = new ExtendedPolymerization(inputs);
		assertEquals(2188189693529L, polymerization.solveA(40));
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
		
		ExtendedPolymerization polymerization = new ExtendedPolymerization(inputs);
		assertEquals(3459822539451L, polymerization.solveA(40));
	}

}
