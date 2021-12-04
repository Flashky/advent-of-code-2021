package com.adventofcode.flashk.common;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Disabled // TODO REMOVE TO EXECUTE
public class DayXTestTemplate extends PuzzleTest {

	private final static String INPUT_FOLDER = "dayX";
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
		List<String> input = Util.readStringLines(INPUT_FOLDER, INPUT_FILE_SAMPLE);
		
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
		
	}

}
