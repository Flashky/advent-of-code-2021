package com.adventofcode.flashk.day2;

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

public class Day2Test {

	private final static String INPUT_FOLDER = "day2/";

	private final static String INPUT_FILE = "data.input";
	private final static String INPUT_FILE_SAMPLE = "sample.input";
	
	private Timer timer = new Timer();
	
	@BeforeClass
	public static void beforeClass() {
		System.out.println();
		System.out.println("========= Day 02 ==========");
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
	public void testDay2Part1SolveA() {
		
		System.out.print("1 | sample | ");
		
		// Read input file
		List<String> input = readFile(INPUT_FILE_SAMPLE);
		
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
		List<String> input = readFile(INPUT_FILE);
		
		Submarine submarine = new Submarine(new BasicMovementStrategy());
		int result = submarine.solve(input);

		assertEquals(1728414, result);
		
	}
	
	@Test
	public void testDay2Part2SolveA() {
		
		System.out.print("2 | sample | ");
		
		// Read input file
		List<String> input = readFile(INPUT_FILE_SAMPLE);
		
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
		List<String> input = readFile(INPUT_FILE);
		
		Submarine submarine = new Submarine(new AimMovementStrategy());
		int result = submarine.solve(input);

		assertEquals(1765720035, result);
		assertEquals((Integer) 2199, submarine.getMovementStrategy().getHorizontalPosition());
		assertEquals((Integer) 802965, submarine.getMovementStrategy().getDepth());
		
	}
	
	/**
	 * @return
	 * @throws URISyntaxException
	 * @throws IOException
	 */
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