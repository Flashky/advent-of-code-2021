package com.adventofcode.flashk.day4;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.adventofcode.flashk.common.Timer;

public class Day4Test {

	private final static String INPUT_FOLDER = "day4/";
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
		Bingo input = readFile(INPUT_FILE_SAMPLE);
		
		assertEquals(4512, input.solve(new BingoFirstWinStrategy()));
	}
	
	@Test
	public void testDay4Part1SolveB() throws URISyntaxException, IOException {
		
		System.out.print("1 | input  | ");
		
		// Read input file
		Bingo input = readFile(INPUT_FILE);
		
		assertEquals(38594, input.solve(new BingoFirstWinStrategy()));
	}
	

	@Test
	public void testDay4Part2SolveA() throws URISyntaxException, IOException {
		
		System.out.print("2 | sample | ");
		
		// Read input file
		Bingo input = readFile(INPUT_FILE_SAMPLE);
		
		assertEquals(1924, input.solve(new BingoLastWinStrategy()));
		
	}
	
	@Test
	public void testDay4Part2SolveB() throws URISyntaxException, IOException {
		
		System.out.print("2 | input  | ");
		
		// Read input file
		Bingo input = readFile(INPUT_FILE);
		
		assertEquals(21184, input.solve(new BingoLastWinStrategy()));
	}
	
	
	private Bingo readFile(String inputFile) {
		
		Stream<String> lines = null;
		List<String> input = null;
		Bingo bingo = null;
		
		try {
			
			Path path = Paths.get(getClass().getClassLoader().getResource(INPUT_FOLDER + inputFile).toURI());
			input = Files.lines(path).collect(Collectors.toList());			
			
			bingo = new Bingo(input);
			
			
			
		} catch (IOException e) {
			input = new ArrayList<>();
			e.printStackTrace();
		} catch (URISyntaxException e) {
			input = new ArrayList<>();
			e.printStackTrace();
		} finally {
			if(lines != null) {
				lines.close();
			}
		}

		return bingo;
	}

}
