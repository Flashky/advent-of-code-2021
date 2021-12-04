package com.adventofcode.flashk.day4;

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

public class Day4Test {

	private final static String INPUT_FOLDER = "day4";
	private final static String INPUT_FILE = "data.input";
	private final static String INPUT_FILE_SAMPLE = "sample.input";
	
	private Timer timer = new Timer();
	
	@BeforeClass
	public static void beforeClass() {
		System.out.println();
		System.out.println("========= Day 04 ==========");
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
	public void testDay4Part1SolveA() throws URISyntaxException, IOException {
		
		System.out.print("1 | sample | ");
		
		// Read input file
		List<String> input = Util.readStringLines(INPUT_FOLDER, INPUT_FILE_SAMPLE);
		Bingo bingo = new Bingo(input);
		
		assertEquals(4512, bingo.solve(new BingoFirstWinStrategy()));
	}
	
	@Test
	public void testDay4Part1SolveB() throws URISyntaxException, IOException {
		
		System.out.print("1 | input  | ");
		
		// Read input file
		List<String> input = Util.readStringLines(INPUT_FOLDER, INPUT_FILE);
		Bingo bingo = new Bingo(input);
		
		assertEquals(38594, bingo.solve(new BingoFirstWinStrategy()));
	}
	

	@Test
	public void testDay4Part2SolveA() throws URISyntaxException, IOException {
		
		System.out.print("2 | sample | ");
		
		// Read input file
		List<String> input = Util.readStringLines(INPUT_FOLDER, INPUT_FILE_SAMPLE);
		Bingo bingo = new Bingo(input);
		
		assertEquals(1924, bingo.solve(new BingoLastWinStrategy()));
		
	}
	
	@Test
	public void testDay4Part2SolveB() throws URISyntaxException, IOException {
		
		System.out.print("2 | input  | ");
		
		// Read input file
		List<String> input = Util.readStringLines(INPUT_FOLDER, INPUT_FILE);
		Bingo bingo = new Bingo(input);
		
		assertEquals(21184, bingo.solve(new BingoLastWinStrategy()));
	}

}
