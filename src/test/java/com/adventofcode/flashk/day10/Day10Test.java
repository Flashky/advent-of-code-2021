package com.adventofcode.flashk.day10;

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

@DisplayName(TestDisplayName.DAY_10)
@TestMethodOrder(OrderAnnotation.class)
public class Day10Test extends PuzzleTest {

	private final static String INPUT_FOLDER = "day_10";
	private final static String INPUT_FILE_DEBUG_294 = "debug_294.input";

	@BeforeAll
	public static void beforeAll() {
		Timer.printHeader(TestDisplayName.DAY_10);
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
		
		SyntaxScoring syntaxScoring = new SyntaxScoring(inputs);
		int result = syntaxScoring.solveA();
		
		assertEquals(26397, result);
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
		
		SyntaxScoring syntaxScoring = new SyntaxScoring(inputs);
		int result = syntaxScoring.solveA();
		
		assertEquals(394647, result);
		
	}
	
	@Test
	@Order(3)
	@Tag(TestTag.PART_TWO)
	@Tag(TestTag.DEBUG)
	@DisplayName(TestDisplayName.PART_TWO_DEBUG + " (294)")
	public void testSolvePart2SingleSample294() {
		
		System.out.print("2 | debug  | ");
		
		// Read input file
		List<String> inputs = Util.readStringLines(INPUT_FOLDER, INPUT_FILE_DEBUG_294);
		
		SyntaxScoring syntaxScoring = new SyntaxScoring(inputs);
		long result = syntaxScoring.solveB();
		
		assertEquals(294L, result);
		
	}
	
	@Test
	@Order(4)
	@Tag(TestTag.PART_TWO)
	@Tag(TestTag.SAMPLE)
	@DisplayName(TestDisplayName.PART_TWO_SAMPLE)
	public void testSolvePart2Sample() {
		
		System.out.print("2 | sample | ");
		
		// Read input file
		List<String> inputs = Util.readStringLines(INPUT_FOLDER, TestFilename.INPUT_FILE_SAMPLE);
		
		SyntaxScoring syntaxScoring = new SyntaxScoring(inputs);
		long result = syntaxScoring.solveB();
		
		assertEquals(288957L, result);
		
	}
	
	@Test
	@Order(5)
	@Tag(TestTag.PART_TWO)
	@Tag(TestTag.INPUT)
	@DisplayName(TestDisplayName.PART_TWO_INPUT)
	public void testSolvePart2Input() {
		
		System.out.print("2 | input  | ");
		
		// Read input file
		List<String> inputs = Util.readStringLines(INPUT_FOLDER, TestFilename.INPUT_FILE);
		
		SyntaxScoring syntaxScoring = new SyntaxScoring(inputs);
		long result = syntaxScoring.solveB();
		
		assertEquals(2380061249L, result);
		
	}

}
