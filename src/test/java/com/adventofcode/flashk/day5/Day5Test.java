package com.adventofcode.flashk.day5;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.adventofcode.flashk.common.TimedTest;
import com.adventofcode.flashk.common.Util;

public class Day5Test extends TimedTest {

	private final static String INPUT_FOLDER = "day5/";
	private final static String INPUT_FILE = "data.input";
	private final static String INPUT_FILE_SAMPLE = "sample.input";
	
	@BeforeAll
	public static void beforeClass() {
		System.out.println();
		System.out.println("========= Day 05 ==========");
		System.out.println("---------------------------");
		System.out.println("# | Input  | Elapsed time");
		System.out.println("---------------------------");
	}
	
	@Test
	public void testDay5Part1SolveA() throws URISyntaxException, IOException {
		
		System.out.print("1 | sample | ");
		
		// Read input file
		List<String> input = Util.readStringLines(INPUT_FOLDER, INPUT_FILE_SAMPLE);
		
	}
	
	@Test
	public void testDay5Part1SolveB() throws URISyntaxException, IOException {
		
		System.out.print("1 | input  | ");
		
		// Read input file
		List<String> input = Util.readStringLines(INPUT_FOLDER, INPUT_FILE);
		
	}
	
	@Test
	public void testDay5Part2SolveA() throws URISyntaxException, IOException {
		
		System.out.print("2 | sample | ");
		
		// Read input file
		List<String> input = Util.readStringLines(INPUT_FOLDER, INPUT_FILE_SAMPLE);
		
	}
	
	@Test
	public void testDay5Part2SolveB() throws URISyntaxException, IOException {
		
		System.out.print("2 | input  | ");
		
		// Read input file
		List<String> input = Util.readStringLines(INPUT_FOLDER, INPUT_FILE);
		
	}
	
}
