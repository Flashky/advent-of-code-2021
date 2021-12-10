package com.adventofcode.flashk.day04;

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
import com.adventofcode.flashk.common.test.utils.Util;

@DisplayName("Day 04")
@TestMethodOrder(OrderAnnotation.class)
public class Day4Test extends PuzzleTest {

	private final static String INPUT_FOLDER = "day4";
	
	@BeforeAll
	public static void beforeClass() {
		System.out.println();
		System.out.println("========= Day 04 ==========");
		System.out.println("---------------------------");
		System.out.println("# | Input  | Elapsed time");
		System.out.println("---------------------------");
	}
	
	@Test
	@Order(1)
	@Tag(TestTag.PART_ONE)
	@Tag(TestTag.SAMPLE)
	@DisplayName(TestDisplayName.PART_ONE_SAMPLE)
	public void testSolvePart1Sample() {
		
		System.out.print("1 | sample | ");
		
		// Read input file
		List<String> input = Util.readStringLines(INPUT_FOLDER, TestFilename.INPUT_FILE_SAMPLE);
		Bingo bingo = new Bingo(input);
		
		assertEquals(4512, bingo.solve(new BingoFirstWinStrategy()));
	}
	
	@Test
	@Order(2)
	@Tag(TestTag.PART_ONE)
	@Tag(TestTag.INPUT)
	@DisplayName(TestDisplayName.PART_ONE_INPUT)
	public void testSolvePart1Input() {
		
		System.out.print("1 | input  | ");
		
		// Read input file
		List<String> input = Util.readStringLines(INPUT_FOLDER, TestFilename.INPUT_FILE);
		Bingo bingo = new Bingo(input);
		
		assertEquals(38594, bingo.solve(new BingoFirstWinStrategy()));
	}
	

	@Test
	@Order(3)
	@Tag(TestTag.PART_TWO)
	@Tag(TestTag.SAMPLE)
	@DisplayName(TestDisplayName.PART_TWO_SAMPLE)
	public void testSolvePart2Sample() {
		
		System.out.print("2 | sample | ");
		
		// Read input file
		List<String> input = Util.readStringLines(INPUT_FOLDER, TestFilename.INPUT_FILE_SAMPLE);
		Bingo bingo = new Bingo(input);
		
		assertEquals(1924, bingo.solve(new BingoLastWinStrategy()));
		
	}
	
	@Test
	@Order(4)
	@Tag(TestTag.PART_TWO)
	@Tag(TestTag.INPUT)
	@DisplayName(TestDisplayName.PART_TWO_INPUT)
	public void testSolvePart2Input() {
		
		System.out.print("2 | input  | ");
		
		// Read input file
		List<String> input = Util.readStringLines(INPUT_FOLDER, TestFilename.INPUT_FILE);
		Bingo bingo = new Bingo(input);
		
		assertEquals(21184, bingo.solve(new BingoLastWinStrategy()));
	}

}
