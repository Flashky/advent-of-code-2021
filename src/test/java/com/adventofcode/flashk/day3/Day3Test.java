package com.adventofcode.flashk.day3;

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

public class Day3Test {

	private final static String INPUT_FOLDER = "day3/";
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
		List<String> input = readFile(INPUT_FILE_SAMPLE);
		
		BinaryDiagnostic binaryDiagnostic = new BinaryDiagnostic();
		
		int result = binaryDiagnostic.solvePowerConsumption(input);
		
		assertEquals(198, result);
	}
	
	@Test
	public void testDay3Part1SolveB() throws URISyntaxException, IOException {
		
		System.out.print("1 | input  | ");
		
		// Read input file
		List<String> input = readFile(INPUT_FILE);
		
		BinaryDiagnostic binaryDiagnostic = new BinaryDiagnostic();
		
		int result = binaryDiagnostic.solvePowerConsumption(input);
		
		//System.out.println(result);
		assertEquals(2967914, result);
	}
	

	
	@Test
	public void testDay3Part2SolveA() throws URISyntaxException, IOException {
		
		System.out.print("2 | sample | ");
		
		// Read input file
		List<String> input = readFile(INPUT_FILE_SAMPLE);
		
		BinaryDiagnostic binaryDiagnostic = new BinaryDiagnostic();
		
		int result = binaryDiagnostic.solveLifeSupportRating(input);
		
		assertEquals(230, result);
	}
	
	@Test
	public void testDay3Part2SolveB() throws URISyntaxException, IOException {
		
		System.out.print("2 | input  | ");
		
		// Read input file
		List<String> input = readFile(INPUT_FILE);
		
		BinaryDiagnostic binaryDiagnostic = new BinaryDiagnostic();
		
		int result = binaryDiagnostic.solveLifeSupportRating(input);
		assertEquals(7041258, result);
	}
	
	private List<String> readFile(String inputFile) {
		
		Stream<String> lines = null;
		List<String> input = null;
		
		try {
			
			Path path = Paths.get(getClass().getClassLoader().getResource(INPUT_FOLDER + inputFile).toURI());
			input = Files.lines(path).collect(Collectors.toList());
			
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
		
		return input;
	}

}
