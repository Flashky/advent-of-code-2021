package com.adventofcode.flashk.day03;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.adventofcode.flashk.common.test.utils.DisplayNameConstant;
import com.adventofcode.flashk.common.test.utils.PuzzleTest;
import com.adventofcode.flashk.common.test.utils.TagConstant;
import com.adventofcode.flashk.common.test.utils.Util;

@DisplayName("Day 03")
@TestMethodOrder(OrderAnnotation.class)
public class Day3Test extends PuzzleTest {

	private final static String INPUT_FOLDER = "day3";
	private final static String INPUT_FILE = "data.input";
	private final static String INPUT_FILE_SAMPLE = "sample.input";
	
	@BeforeAll
	public static void beforeClass() {
		System.out.println();
		System.out.println("========= Day 03 ==========");
		System.out.println("---------------------------");
		System.out.println("# | Input  | Elapsed time");
		System.out.println("---------------------------");
	}
	
	@Test
	@Order(1)
	@Tag(TagConstant.PART_ONE)
	@Tag(TagConstant.SAMPLE)
	@DisplayName(DisplayNameConstant.PART_ONE_SAMPLE)
	public void testSolvePart1Sample() {
		
		System.out.print("1 | sample | ");
		
		// Read input file
		List<String> input = Util.readStringLines(INPUT_FOLDER, INPUT_FILE_SAMPLE);
		
		BinaryDiagnostic binaryDiagnostic = new BinaryDiagnostic();
		
		int result = binaryDiagnostic.solvePowerConsumption(input);
		
		assertEquals(198, result);
	}
	
	@Test
	@Order(2)
	@Tag(TagConstant.PART_ONE)
	@Tag(TagConstant.INPUT)
	@DisplayName(DisplayNameConstant.PART_ONE_INPUT)
	public void testSolvePart1Input() {
		
		System.out.print("1 | input  | ");
		
		// Read input file
		List<String> input = Util.readStringLines(INPUT_FOLDER, INPUT_FILE);
		
		BinaryDiagnostic binaryDiagnostic = new BinaryDiagnostic();
		
		int result = binaryDiagnostic.solvePowerConsumption(input);
		
		//System.out.println(result);
		assertEquals(2967914, result);
	}
	
	@Test
	@Order(3)
	@Tag(TagConstant.PART_TWO)
	@Tag(TagConstant.SAMPLE)
	@DisplayName(DisplayNameConstant.PART_TWO_SAMPLE)
	public void testSolvePart2Sample() {
		
		System.out.print("2 | sample | ");
		
		// Read input file
		List<String> input = Util.readStringLines(INPUT_FOLDER, INPUT_FILE_SAMPLE);
		
		BinaryDiagnostic binaryDiagnostic = new BinaryDiagnostic();
		
		int result = binaryDiagnostic.solveLifeSupportRating(input);
		
		assertEquals(230, result);
	}
	
	@Test
	@Order(4)
	@Tag(TagConstant.PART_TWO)
	@Tag(TagConstant.INPUT)
	@DisplayName(DisplayNameConstant.PART_TWO_INPUT)
	public void testSolvePart2Input() {
		
		System.out.print("2 | input  | ");
		
		// Read input file
		List<String> input = Util.readStringLines(INPUT_FOLDER, INPUT_FILE);
		
		BinaryDiagnostic binaryDiagnostic = new BinaryDiagnostic();
		
		int result = binaryDiagnostic.solveLifeSupportRating(input);
		assertEquals(7041258, result);
	}

}
