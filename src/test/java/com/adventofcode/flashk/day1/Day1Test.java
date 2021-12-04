package com.adventofcode.flashk.day1;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.adventofcode.flashk.common.Timer;
import com.adventofcode.flashk.common.Util;

public class Day1Test {

	private final static String INPUT_FOLDER = "day1";
	private final static String INPUT_FILE = "data.input";
	private final static String INPUT_FILE_SAMPLE = "sample.input";
	
	private Timer timer = new Timer();
	
	@BeforeClass
	public static void beforeClass() {
		System.out.println();
		System.out.println("========= Day 01 ==========");
		System.out.println("---------------------------");
		System.out.println("# | Input  | Elapsed time");
		System.out.println("---------------------------");
	}
	
	@Before
	public void before() {
		timer.start();
	}
	
	@After
	public void after() {
		timer.stop();
	}

	@Test
	public void testDay1Part1SolveA() throws URISyntaxException, IOException {
		
		System.out.print("1 | sample | ");
		
		// Read input file
		List<Integer> input = Util.readIntegerLines(INPUT_FOLDER, INPUT_FILE_SAMPLE);
		
		SonarSweep sonarSweep = new SonarSweep();
		
		int result = sonarSweep.solve(input);
		
		assertEquals(7, result);
	}
	
	@Test
	public void testDay1Part1SolveB() throws URISyntaxException, IOException {
		
		System.out.print("1 | input  | ");
		
		// Read input file
		List<Integer> input = Util.readIntegerLines(INPUT_FOLDER, INPUT_FILE);
		
		SonarSweep sonarSweep = new SonarSweep();
		
		int result = sonarSweep.solve(input);
		
		assertEquals(1342, result);
	}
	
	@Test
	public void testDay1Part2SolveA() throws URISyntaxException, IOException {
		
		System.out.print("2 | sample | ");
		
		// Read input file
		List<Integer> input =  Util.readIntegerLines(INPUT_FOLDER, INPUT_FILE_SAMPLE);
		
		SonarSweep sonarSweep = new SonarSweep();
		
		int result = sonarSweep.solve(input, 3);

		assertEquals(5, result);
	}
	
	@Test
	public void testDay1Part2SolveB() throws URISyntaxException, IOException {
		
		System.out.print("2 | input  | ");
		
		// Read input file
		List<Integer> input = Util.readIntegerLines(INPUT_FOLDER, INPUT_FILE);
		
		SonarSweep sonarSweep = new SonarSweep();
		
		int result = sonarSweep.solve(input, 3);
		//System.out.println(result); // gives 1378
		assertEquals(1378, result);
	}

}
