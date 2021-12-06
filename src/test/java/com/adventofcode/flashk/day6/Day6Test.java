package com.adventofcode.flashk.day6;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import com.adventofcode.flashk.common.DisplayNameConstant;
import com.adventofcode.flashk.common.PuzzleTest;
import com.adventofcode.flashk.common.TagConstant;
import com.adventofcode.flashk.common.Util;


@DisplayName("Day 06")
@TestMethodOrder(OrderAnnotation.class)
public class Day6Test extends PuzzleTest {

	private final static String INPUT_FOLDER = "day6";
	private final static String INPUT_FILE = "data.input";
	private final static String INPUT_FILE_SAMPLE = "sample.input";

	@BeforeAll
	public static void beforeAll() {
		System.out.println();
		System.out.println("========= Day XX ==========");
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
		
		LanternFish lanternFish = new LanternFish(inputs.get(0));
		Long result = lanternFish.solve(80);
		
		assertEquals(5934, result);
		
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
		
		LanternFish lanternFish = new LanternFish(inputs.get(0));
		Long result = lanternFish.solve(80);
		assertEquals(376194, result);
		
	}

	@Test
	@Order(3)
	@Tag(TagConstant.PART_TWO)
	@Tag(TagConstant.INPUT)
	@DisplayName(DisplayNameConstant.PART_TWO_INPUT)
	public void testSolvePart2Input() {
		
		System.out.print("2 | input  | ");
		
		// Read input file
		List<String> inputs = Util.readStringLines(INPUT_FOLDER, INPUT_FILE_SAMPLE);
		
		LanternFish lanternFish = new LanternFish(inputs.get(0));
		Long result = lanternFish.solve(256);
		
		assertEquals(26984457539L, result);
		
	}

}
