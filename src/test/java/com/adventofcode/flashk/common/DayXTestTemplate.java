package com.adventofcode.flashk.common;

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
import org.junit.Ignore;
import org.junit.Test;

@Ignore // REMOVE TO EXECUTE
public class DayXTestTemplate {

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
	public void testDayXPart1SolveA() throws URISyntaxException, IOException {
		
		System.out.print("1 | sample | ");
		
		// Read input file
		List<String> input = readFile(INPUT_FILE_SAMPLE);
		
	}
	
	@Test
	public void testDayXPart1SolveB() throws URISyntaxException, IOException {
		
		System.out.print("1 | input  | ");
		
		// Read input file
		List<String> input = readFile(INPUT_FILE);
		
	}
	

	
	@Test
	public void testDayXPart2SolveA() throws URISyntaxException, IOException {
		
		System.out.print("2 | sample | ");
		
		// Read input file
		List<String> input = readFile(INPUT_FILE_SAMPLE);
		
	}
	
	@Test
	public void testDayXPart2SolveB() throws URISyntaxException, IOException {
		
		System.out.print("2 | input  | ");
		
		// Read input file
		List<String> input = readFile(INPUT_FILE);
		
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
