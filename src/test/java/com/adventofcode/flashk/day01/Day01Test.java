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

import com.adventofcode.flashk.common.test.constants.TestDisplayName;
import com.adventofcode.flashk.common.test.constants.TestFilename;
import com.adventofcode.flashk.common.test.constants.TestTag;
import com.adventofcode.flashk.common.test.utils.PuzzleTest;
import com.adventofcode.flashk.common.test.utils.Timer;
import com.adventofcode.flashk.common.test.utils.Input;

@TestMethodOrder(OrderAnnotation.class)
@DisplayName(TestDisplayName.DAY_01)
public class Day01Test extends PuzzleTest {

	private final static String INPUT_FOLDER = "day_01";
	
	@BeforeAll
	public static void beforeClass() {
		Timer.printHeader(TestDisplayName.DAY_01);
	}

	@Test
	@Order(1)
	@Tag(TestTag.PART_ONE)
	@Tag(TestTag.SAMPLE)
	@DisplayName(TestDisplayName.PART_ONE_SAMPLE)
	public void testSolvePart1Sample() {
		
		System.out.print("1 | sample | ");
		
		// Read input file
		List<Integer> input = Input.readIntegerLines(INPUT_FOLDER, TestFilename.INPUT_FILE_SAMPLE);
		
		SonarSweep sonarSweep = new SonarSweep();
		
		int result = sonarSweep.solve(input);
		
		assertEquals(7, result);
	}
	
	
	@Test
	@Order(2)
	@Tag(TestTag.PART_ONE)
	@Tag(TestTag.INPUT)
	@DisplayName(TestDisplayName.PART_ONE_INPUT)
	public void testSolvePart1Input() {
		
		System.out.print("1 | input  | ");
		
		// Read input file
		List<Integer> input = Input.readIntegerLines(INPUT_FOLDER, TestFilename.INPUT_FILE);
		
		SonarSweep sonarSweep = new SonarSweep();
		
		int result = sonarSweep.solve(input);
		
		assertEquals(1342, result);
	}
	
	
	@Test
	@Order(3)
	@Tag(TestTag.PART_TWO)
	@Tag(TestTag.SAMPLE)
	@DisplayName(TestDisplayName.PART_TWO_SAMPLE)
	public void testSolvePart2Sample() {
		
		System.out.print("2 | sample | ");
		
		// Read input file
		List<Integer> input =  Input.readIntegerLines(INPUT_FOLDER, TestFilename.INPUT_FILE_SAMPLE);
		
		SonarSweep sonarSweep = new SonarSweep();
		
		int result = sonarSweep.solve(input, 3);

		assertEquals(5, result);
	}
	
	
	@Test
	@Order(4)
	@Tag(TestTag.PART_TWO)
	@Tag(TestTag.INPUT)
	@DisplayName(TestDisplayName.PART_TWO_INPUT)
	public void testSolvePart2Input() {
		
		System.out.print("2 | input  | ");
		
		// Read input file
		List<Integer> input = Input.readIntegerLines(INPUT_FOLDER, TestFilename.INPUT_FILE);
		
		SonarSweep sonarSweep = new SonarSweep();
		
		int result = sonarSweep.solve(input, 3);
		//System.out.println(result); // gives 1378
		assertEquals(1378, result);
	}

}
