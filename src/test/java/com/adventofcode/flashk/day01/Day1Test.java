package com.adventofcode.flashk.day01;

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

@TestMethodOrder(OrderAnnotation.class)
@DisplayName("Day 01")
public class Day1Test extends PuzzleTest {

	private final static String INPUT_FOLDER = "day1";
	private final static String INPUT_FILE = "data.input";
	private final static String INPUT_FILE_SAMPLE = "sample.input";
	
	@BeforeAll
	public static void beforeClass() {
		System.out.println();
		System.out.println("========= Day 01 ==========");
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
		List<Integer> input = Util.readIntegerLines(INPUT_FOLDER, INPUT_FILE_SAMPLE);
		
		SonarSweep sonarSweep = new SonarSweep();
		
		int result = sonarSweep.solve(input);
		
		assertEquals(7, result);
	}
	
	
	@Test
	@Order(2)
	@Tag(TagConstant.PART_ONE)
	@Tag(TagConstant.INPUT)
	@DisplayName(DisplayNameConstant.PART_ONE_INPUT)
	public void testSolvePart1Input() {
		
		System.out.print("1 | input  | ");
		
		// Read input file
		List<Integer> input = Util.readIntegerLines(INPUT_FOLDER, INPUT_FILE);
		
		SonarSweep sonarSweep = new SonarSweep();
		
		int result = sonarSweep.solve(input);
		
		assertEquals(1342, result);
	}
	
	
	@Test
	@Order(3)
	@Tag(TagConstant.PART_TWO)
	@Tag(TagConstant.SAMPLE)
	@DisplayName(DisplayNameConstant.PART_TWO_SAMPLE)
	public void testSolvePart2Sample() {
		
		System.out.print("2 | sample | ");
		
		// Read input file
		List<Integer> input =  Util.readIntegerLines(INPUT_FOLDER, INPUT_FILE_SAMPLE);
		
		SonarSweep sonarSweep = new SonarSweep();
		
		int result = sonarSweep.solve(input, 3);

		assertEquals(5, result);
	}
	
	
	@Test
	@Order(4)
	@Tag(TagConstant.PART_TWO)
	@Tag(TagConstant.INPUT)
	@DisplayName(DisplayNameConstant.PART_TWO_INPUT)
	public void testSolvePart2Input() {
		
		System.out.print("2 | input  | ");
		
		// Read input file
		List<Integer> input = Util.readIntegerLines(INPUT_FOLDER, INPUT_FILE);
		
		SonarSweep sonarSweep = new SonarSweep();
		
		int result = sonarSweep.solve(input, 3);
		//System.out.println(result); // gives 1378
		assertEquals(1378, result);
	}

}
