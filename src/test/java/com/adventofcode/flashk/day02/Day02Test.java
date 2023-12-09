package com.adventofcode.flashk.day02;

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

@DisplayName(TestDisplayName.DAY_02)
@TestMethodOrder(OrderAnnotation.class)
public class Day02Test extends PuzzleTest {

	private final static String INPUT_FOLDER = "day_02";
	
	@BeforeAll
	public static void beforeClass() {
		Timer.printHeader(TestDisplayName.DAY_02);
	}
	
	@Test
	@Order(1)
	@Tag(TestTag.PART_ONE)
	@Tag(TestTag.SAMPLE)
	@DisplayName(TestDisplayName.PART_ONE_SAMPLE)
	public void testSolvePart1Sample() {
		
		System.out.print("1 | sample | ");
		
		// Read input file
		List<String> input = Input.readStringLines(INPUT_FOLDER, TestFilename.INPUT_FILE_SAMPLE);
		
		Submarine submarine = new Submarine(new BasicMovementStrategy());
		int result = submarine.solve(input);
		
		assertEquals(150, result);
		
	}
	
	@Test
	@Order(2)
	@Tag(TestTag.PART_ONE)
	@Tag(TestTag.INPUT)
	@DisplayName(TestDisplayName.PART_ONE_INPUT)
	public void testSolvePart1Input() {
		
		System.out.print("1 | input  | ");
		
		// Read input file
		List<String> input = Input.readStringLines(INPUT_FOLDER, TestFilename.INPUT_FILE);
		
		Submarine submarine = new Submarine(new BasicMovementStrategy());
		int result = submarine.solve(input);

		assertEquals(1728414, result);
		
	}
	
	@Test
	@Order(3)
	@Tag(TestTag.PART_TWO)
	@Tag(TestTag.SAMPLE)
	@DisplayName(TestDisplayName.PART_TWO_SAMPLE)
	public void testSolvePart2Sample() {
		
		System.out.print("2 | sample | ");
		
		// Read input file
		List<String> input = Input.readStringLines(INPUT_FOLDER, TestFilename.INPUT_FILE_SAMPLE);
		
		Submarine submarine = new Submarine(new AimMovementStrategy());
		int result = submarine.solve(input);
		
		assertEquals(900, result);
		
	}
	
	@Test
	@Order(4)
	@Tag(TestTag.PART_TWO)
	@Tag(TestTag.INPUT)
	@DisplayName(TestDisplayName.PART_TWO_INPUT)
	public void testSolvePart2Input() {
		
		System.out.print("2 | input  | ");
		
		// Read input file
		List<String> input = Input.readStringLines(INPUT_FOLDER, TestFilename.INPUT_FILE);
		
		Submarine submarine = new Submarine(new AimMovementStrategy());
		int result = submarine.solve(input);

		assertEquals(1765720035, result);
		
	}
	
}
