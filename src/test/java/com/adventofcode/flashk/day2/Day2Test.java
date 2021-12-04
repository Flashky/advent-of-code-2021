package com.adventofcode.flashk.day2;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.adventofcode.flashk.common.TimedTest;
import com.adventofcode.flashk.common.Util;

public class Day2Test extends TimedTest {

	private final static String INPUT_FOLDER = "day2";

	private final static String INPUT_FILE = "data.input";
	private final static String INPUT_FILE_SAMPLE = "sample.input";
	
	@BeforeAll
	public static void beforeClass() {
		System.out.println();
		System.out.println("========= Day 02 ==========");
		System.out.println("---------------------------");
		System.out.println("# | Input  | Elapsed time");
		System.out.println("---------------------------");
	}
	
	@Test
	public void testDay2Part1SolveA() {
		
		System.out.print("1 | sample | ");
		
		// Read input file
		List<String> input = Util.readStringLines(INPUT_FOLDER, INPUT_FILE_SAMPLE);
		
		Submarine submarine = new Submarine(new BasicMovementStrategy());
		int result = submarine.solve(input);
		
		assertEquals(150, result);
		assertEquals((Integer) 15, submarine.getMovementStrategy().getHorizontalPosition());
		assertEquals((Integer) 10, submarine.getMovementStrategy().getDepth());
		
	}
	
	@Test
	public void testDay2Part1SolveB() {
		
		System.out.print("1 | input  | ");
		
		// Read input file
		List<String> input = Util.readStringLines(INPUT_FOLDER, INPUT_FILE);
		
		Submarine submarine = new Submarine(new BasicMovementStrategy());
		int result = submarine.solve(input);

		assertEquals(1728414, result);
		
	}
	
	@Test
	public void testDay2Part2SolveA() {
		
		System.out.print("2 | sample | ");
		
		// Read input file
		List<String> input = Util.readStringLines(INPUT_FOLDER, INPUT_FILE_SAMPLE);
		
		Submarine submarine = new Submarine(new AimMovementStrategy());
		int result = submarine.solve(input);
		
		assertEquals(900, result);
		assertEquals((Integer) 15, submarine.getMovementStrategy().getHorizontalPosition());
		assertEquals((Integer) 60, submarine.getMovementStrategy().getDepth());
		
	}
	
	@Test
	public void testDay2Part2SolveB() {
		
		System.out.print("2 | input  | ");
		
		// Read input file
		List<String> input = Util.readStringLines(INPUT_FOLDER, INPUT_FILE);
		
		Submarine submarine = new Submarine(new AimMovementStrategy());
		int result = submarine.solve(input);

		assertEquals(1765720035, result);
		assertEquals((Integer) 2199, submarine.getMovementStrategy().getHorizontalPosition());
		assertEquals((Integer) 802965, submarine.getMovementStrategy().getDepth());
		
	}
	
}
