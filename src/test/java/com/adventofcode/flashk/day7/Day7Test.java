package com.adventofcode.flashk.day7;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.adventofcode.flashk.common.DisplayNameConstant;
import com.adventofcode.flashk.common.PuzzleTest;
import com.adventofcode.flashk.common.TagConstant;
import com.adventofcode.flashk.common.Util;

@DisplayName("Day 07")
@TestMethodOrder(OrderAnnotation.class)
public class Day7Test extends PuzzleTest {

	private final static String INPUT_FOLDER = "day7";
	private final static String INPUT_FILE = "data.input";
	private final static String INPUT_FILE_SAMPLE = "sample.input";

	@BeforeAll
	public static void beforeAll() {
		System.out.println();
		System.out.println("========= Day 07	 ==========");
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
		List<String> inputs = Util.readStringLines(INPUT_FOLDER, INPUT_FILE_SAMPLE);
		
		CrabAlignment crabAlignment = new CrabAlignment(inputs.get(0));
		
		int result = crabAlignment.solve();
		
		assertEquals(37, result);
		
	}
	
	@Test
	@Order(2)
	@Tag(TagConstant.PART_ONE)
	@Tag(TagConstant.INPUT)
	@DisplayName(DisplayNameConstant.PART_ONE_INPUT)
	public void testSolvePart1Input() {
		
		System.out.print("1 | input  | ");
		
		// Read input file
		List<String> inputs = Util.readStringLines(INPUT_FOLDER, INPUT_FILE);
		
		CrabAlignment crabAlignment = new CrabAlignment(inputs.get(0));
		
		int result = crabAlignment.solve();
		assertEquals(344605, result);
		
		
	}
	
	@Test
	@Order(3)
	@Tag(TagConstant.PART_TWO)
	@Tag(TagConstant.SAMPLE)
	@DisplayName(DisplayNameConstant.PART_TWO_SAMPLE)
	public void testSolvePart2Sample() {
		
		System.out.print("2 | sample | ");
		
		// Read input file
		List<String> inputs = Util.readStringLines(INPUT_FOLDER, INPUT_FILE_SAMPLE);
		
		CrabAlignment crabAlignment = new CrabAlignment(inputs.get(0));
		
		int result = crabAlignment.solve(1);
		assertEquals(168, result);
		
	}
	
	@Test
	@Order(4)
	@Tag(TagConstant.PART_TWO)
	@Tag(TagConstant.INPUT)
	@DisplayName(DisplayNameConstant.PART_TWO_INPUT)
	public void testSolvePart2Input() {
		
		System.out.print("2 | input  | ");
		
		// Read input file
		List<String> inputs = Util.readStringLines(INPUT_FOLDER, INPUT_FILE);
		
		CrabAlignment crabAlignment = new CrabAlignment(inputs.get(0));
		
		int result = crabAlignment.solve(1);
		assertEquals(93699985, result);
	}

}
