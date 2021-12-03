package com.adventofcode.flashk.day1;

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

public class Day1Test {

	private final static String INPUT_FOLDER = "day1/";
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
		List<Integer> input = readFile(INPUT_FILE_SAMPLE);
		
		SonarSweep sonarSweep = new SonarSweep();
		
		int result = sonarSweep.solve(input);
		
		assertEquals(7, result);
	}
	
	@Test
	public void testDay1Part1SolveB() throws URISyntaxException, IOException {
		
		System.out.print("1 | input  | ");
		
		// Read input file
		List<Integer> input = readFile(INPUT_FILE);
		
		SonarSweep sonarSweep = new SonarSweep();
		
		int result = sonarSweep.solve(input);
		//System.out.println(result);
		assertEquals(1342, result);
	}
	
	@Test
	public void testDay1Part2SolveA() throws URISyntaxException, IOException {
		
		System.out.print("2 | sample | ");
		
		// Read input file
		List<Integer> input = readFile(INPUT_FILE_SAMPLE);
		
		SonarSweep sonarSweep = new SonarSweep();
		
		int result = sonarSweep.solve(input, 3);
		
		assertEquals(5, result);
	}
	
	@Test
	public void testDay1Part2SolveB() throws URISyntaxException, IOException {
		
		System.out.print("2 | input  | ");
		
		// Read input file
		List<Integer> input = readFile(INPUT_FILE);
		
		SonarSweep sonarSweep = new SonarSweep();
		
		int result = sonarSweep.solve(input, 3);
		//System.out.println(result); // gives 1378
		assertEquals(1378, result);
	}
	
	/**
	 * @return
	 * @throws URISyntaxException
	 * @throws IOException
	 */
	private List<Integer> readFile(String inputFile) {
		
		Stream<String> lines = null;
		List<Integer> input = null;
		
		try {
			
			Path path = Paths.get(getClass().getClassLoader().getResource(INPUT_FOLDER + inputFile).toURI());
			input = Files.lines(path).map(Integer::parseInt).collect(Collectors.toList());
			
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
