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
import org.junit.Test;

import com.adventofcode.flashk.common.Timer;

public class BinaryDiagnosticTest {

	private final static String INPUT_FOLDER = "day3/";
	private final static String INPUT_FILE = "data.input";
	private final static String INPUT_FILE_SAMPLE = "sample.input";
	
	private Timer timer = new Timer();
	
	@Before
	public void before() {
		timer.start();
	}
	
	@After
	public void after() {
		timer.stop();
		System.out.println();
	}
	
	@Test
	public void testSolvePowerConsumption() throws URISyntaxException, IOException {
		
		System.out.println("Day 3 - Part 1 input timing");
		
		// Read input file
		List<String> input = readFile(INPUT_FILE);
		
		BinaryDiagnostic binaryDiagnostic = new BinaryDiagnostic();
		
		int result = binaryDiagnostic.solvePowerConsumption(input);
		
		//System.out.println(result);
		assertEquals(2967914, result);
	}
	
	@Test
	public void testSolvePowerConsumptionSample() throws URISyntaxException, IOException {
		
		System.out.println("Day 3 - Part 1 sample timing");
		
		// Read input file
		List<String> input = readFile(INPUT_FILE_SAMPLE);
		
		BinaryDiagnostic binaryDiagnostic = new BinaryDiagnostic();
		
		int result = binaryDiagnostic.solvePowerConsumption(input);
		
		assertEquals(198, result);
	}
	
	@Test
	public void testSolveLifeSupportRatingSample() throws URISyntaxException, IOException {
		
		System.out.println("Day 3 - Part 2 sample timing");
		
		// Read input file
		List<String> input = readFile(INPUT_FILE_SAMPLE);
		
		BinaryDiagnostic binaryDiagnostic = new BinaryDiagnostic();
		
		int result = binaryDiagnostic.solveLifeSupportRating(input);
		
		assertEquals(230, result);
	}
	
	@Test
	public void testSolveLifeSupportRating() throws URISyntaxException, IOException {
		
		System.out.println("Day 3 - Part 2 input timing");
		
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
