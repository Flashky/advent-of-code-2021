package com.adventofcode.flashk.day18;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
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

@DisplayName(TestDisplayName.DAY_18)
@TestMethodOrder(OrderAnnotation.class)
@Disabled("Refactor day 18 test to use Gson")
public class Day18GsonTest extends PuzzleTest {

	private final static String INPUT_FOLDER = "day_18";
	
	private static final String INPUT_FILE_DEBUG_3488 = "sample_debug_3488.input";
	
	@BeforeAll
	public static void beforeAll() {
		Timer.printHeader(TestDisplayName.DAY_18);
	}


	@Test
	@Order(1)
	@Tag(TestTag.PART_ONE)
	@Tag(TestTag.DEBUG)
	@DisplayName(TestDisplayName.PART_ONE_DEBUG + " - (3488)")
	public void testSolvePart1Debug() {
		
		System.out.print("1 | debug | ");
		
		// Read input file
		List<String> inputs = Util.readStringLines(INPUT_FOLDER, INPUT_FILE_DEBUG_3488);
		
		SnailfishGson snailfish = new SnailfishGson(inputs);
		assertEquals(3488, snailfish.solveA());
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
		
		SnailfishGson snailfish = new SnailfishGson(inputs);
		assertEquals(4140, snailfish.solveA());
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
		
		SnailfishGson snailfish = new SnailfishGson(inputs);
		assertEquals(3494, snailfish.solveA());
		
	}
	
	@Test
	@Order(3)
	@Tag(TestTag.PART_TWO)
	@Tag(TestTag.SAMPLE)
	@DisplayName(TestDisplayName.PART_TWO_SAMPLE)
	public void testSolvePart2Sample() {
		
		System.out.print("2 | sample | ");
		
		// Read input file
		List<String> inputs = Util.readStringLines(INPUT_FOLDER, TestFilename.INPUT_FILE_SAMPLE);
		
		SnailfishGson snailfish = new SnailfishGson(inputs);
		assertEquals(3993, snailfish.solveB());
		
	}
	
	@Test
	@Order(4)
	@Tag(TestTag.PART_TWO)
	@Tag(TestTag.INPUT)
	@DisplayName(TestDisplayName.PART_TWO_INPUT)
	public void testSolvePart2Input() {
		
		System.out.print("2 | input  | ");
		
		// Read input file
		List<String> inputs = Util.readStringLines(INPUT_FOLDER, TestFilename.INPUT_FILE);
		
		SnailfishGson snailfish = new SnailfishGson(inputs);
		assertEquals(4712, snailfish.solveB());
		
	}

}
