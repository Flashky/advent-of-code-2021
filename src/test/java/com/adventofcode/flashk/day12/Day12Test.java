package com.adventofcode.flashk.day12;

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

@DisplayName(TestDisplayName.DAY_12)
@TestMethodOrder(OrderAnnotation.class)
public class Day12Test extends PuzzleTest {

	private final static String INPUT_FOLDER = "day_12";
	private final static String INPUT_FILE_MEDIUM_SAMPLE = "sample_medium.input";
	private final static String INPUT_FILE_LARGE_SAMPLE = "sample_large.input";
	
	@BeforeAll
	public static void beforeAll() {
		Timer.printHeader(TestDisplayName.DAY_12);
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
		PassagePathing passagePathing = new PassagePathing(inputs);
		
		assertEquals(10, passagePathing.solve(false));
	}
	
	@Test
	@Order(2)
	@Tag(TestTag.PART_ONE)
	@Tag(TestTag.SAMPLE)
	@DisplayName(TestDisplayName.PART_ONE_SAMPLE)
	public void testSolvePart1SampleMedium() {
		
		System.out.print("1 | sample | ");
		
		// Read input file
		List<String> inputs = Input.readStringLines(INPUT_FOLDER, INPUT_FILE_MEDIUM_SAMPLE);
		PassagePathing passagePathing = new PassagePathing(inputs);
		
		assertEquals(19, passagePathing.solve(false));
		
	}
	
	@Test
	@Order(3)
	@Tag(TestTag.PART_ONE)
	@Tag(TestTag.SAMPLE)
	@DisplayName(TestDisplayName.PART_ONE_SAMPLE)
	public void testSolvePart1SampleLarge() {
		
		System.out.print("1 | sample | ");
		
		// Read input file
		List<String> inputs = Input.readStringLines(INPUT_FOLDER, INPUT_FILE_LARGE_SAMPLE);
		PassagePathing passagePathing = new PassagePathing(inputs);
		
		assertEquals(226, passagePathing.solve(false));
		
	}
	
	@Test
	@Order(4)
	@Tag(TestTag.PART_ONE)
	@Tag(TestTag.INPUT)
	@DisplayName(TestDisplayName.PART_ONE_INPUT)
	public void testSolvePart1Input() {
		
		System.out.print("1 | input  | ");
		
		// Read input file
		List<String> inputs = Input.readStringLines(INPUT_FOLDER, TestFilename.INPUT_FILE);
		PassagePathing passagePathing = new PassagePathing(inputs);
		
		assertEquals(3761, passagePathing.solve(false));
	}
	
	@Test
	@Order(5)
	@Tag(TestTag.PART_TWO)
	@Tag(TestTag.SAMPLE)
	@DisplayName(TestDisplayName.PART_TWO_SAMPLE)
	public void testSolvePart2Sample() {
		
		System.out.print("2 | sample | ");
		
		// Read input file
		List<String> inputs = Input.readStringLines(INPUT_FOLDER, TestFilename.INPUT_FILE_SAMPLE);
		PassagePathing passagePathing = new PassagePathing(inputs);
		
		assertEquals(36, passagePathing.solve(true));

	}
	
	@Test
	@Order(6)
	@Tag(TestTag.PART_TWO)
	@Tag(TestTag.SAMPLE)
	@DisplayName(TestDisplayName.PART_TWO_SAMPLE)
	public void testSolvePart2SampleMedium() {
		
		System.out.print("2 | sample medium | ");
		
		// Read input file
		List<String> inputs = Input.readStringLines(INPUT_FOLDER, INPUT_FILE_MEDIUM_SAMPLE);
		
		PassagePathing passagePathing = new PassagePathing(inputs);
		assertEquals(103, passagePathing.solve(true));

	}
	
	
	@Test
	@Order(7)
	@Tag(TestTag.PART_TWO)
	@Tag(TestTag.SAMPLE)
	@DisplayName(TestDisplayName.PART_TWO_SAMPLE)
	public void testSolvePart2SampleLarge() {
		
		System.out.print("2 | sample large | ");
		
		// Read input file
		List<String> inputs = Input.readStringLines(INPUT_FOLDER, INPUT_FILE_LARGE_SAMPLE);
		
		PassagePathing passagePathing = new PassagePathing(inputs);
		assertEquals(3509, passagePathing.solve(true));

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
		
		PassagePathing passagePathing = new PassagePathing(inputs);
		assertEquals(99138, passagePathing.solve(true));
		
	}

}
