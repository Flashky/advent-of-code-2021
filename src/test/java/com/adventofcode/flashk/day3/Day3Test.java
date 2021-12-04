package com.adventofcode.flashk.day3;

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

public class Day3Test {

	private final static String INPUT_FOLDER = "day3";
	private final static String INPUT_FILE = "data.input";
	private final static String INPUT_FILE_SAMPLE = "sample.input";
	
	private Timer timer = new Timer();
	
	@BeforeClass
	public static void beforeClass() {
		System.out.println();
		System.out.println("========= Day 03 ==========");
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
	public void testDay3Part1SolveA() throws URISyntaxException, IOException {
		
		System.out.print("1 | sample | ");
		
		// Read input file
		List<String> input = Util.readStringLines(INPUT_FOLDER, INPUT_FILE_SAMPLE);
		
		BinaryDiagnostic binaryDiagnostic = new BinaryDiagnostic();
		
		int result = binaryDiagnostic.solvePowerConsumption(input);
		
		assertEquals(198, result);
	}
	
	@Test
	public void testDay3Part1SolveB() throws URISyntaxException, IOException {
		
		System.out.print("1 | input  | ");
		
		// Read input file
		List<String> input = Util.readStringLines(INPUT_FOLDER, INPUT_FILE);
		
		BinaryDiagnostic binaryDiagnostic = new BinaryDiagnostic();
		
		int result = binaryDiagnostic.solvePowerConsumption(input);
		
		//System.out.println(result);
		assertEquals(2967914, result);
	}
	

	
	@Test
	public void testDay3Part2SolveA() throws URISyntaxException, IOException {
		
		System.out.print("2 | sample | ");
		
		// Read input file
		List<String> input = Util.readStringLines(INPUT_FOLDER, INPUT_FILE_SAMPLE);
		
		BinaryDiagnostic binaryDiagnostic = new BinaryDiagnostic();
		
		int result = binaryDiagnostic.solveLifeSupportRating(input);
		
		assertEquals(230, result);
	}
	
	@Test
	public void testDay3Part2SolveB() throws URISyntaxException, IOException {
		
		System.out.print("2 | input  | ");
		
		// Read input file
		List<String> input = Util.readStringLines(INPUT_FOLDER, INPUT_FILE);
		
		BinaryDiagnostic binaryDiagnostic = new BinaryDiagnostic();
		
		int result = binaryDiagnostic.solveLifeSupportRating(input);
		assertEquals(7041258, result);
	}

}
