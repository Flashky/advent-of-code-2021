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

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class SonarSweepTest {

	private final static String INPUT_FOLDER = "day1/";
	private final static String INPUT_FILE = "data.input";
	private final static String INPUT_FILE_SAMPLE = "sample.input";
	private final static String INPUT_FILE_ONE_MEASUREMENT = "test1.input";
	private final static String INPUT_FILE_TWO_MEASUREMENTS_WITH_INCREMENT = "test2.input";
	private final static String INPUT_FILE_TWO_MEASUREMENTS_NO_INCREMENT = "test3.input";
	private final static String INPUT_FILE_THREE_MEASUREMENTS_WITH_INCREMENTS = "test4.input";
	private final static String INPUT_FILE_FOUR_MEASUREMENTS_WITH_INCREMENTS = "test5.input";
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testSolve() throws URISyntaxException, IOException {
		
		// Read input file
		List<Integer> input = readFile(INPUT_FILE);
		
		SonarSweep sonarSweep = new SonarSweep();
		
		int result = sonarSweep.solve(input);
		//System.out.println(result);
		assertEquals(1342, result);
	}

	@Test
	public void testSolveSample() throws URISyntaxException, IOException {
		
		// Read input file
		List<Integer> input = readFile(INPUT_FILE_SAMPLE);
		
		SonarSweep sonarSweep = new SonarSweep();
		
		int result = sonarSweep.solve(input);
		
		assertEquals(7, result);
	}
	
	@Test
	public void testSolveOneMeasurement() throws URISyntaxException, IOException {
		
		// Read input file
		List<Integer> input = readFile(INPUT_FILE_ONE_MEASUREMENT);
		
		SonarSweep sonarSweep = new SonarSweep();
		
		int result = sonarSweep.solve(input);
		
		assertEquals(0, result);
	}
	
	@Test
	public void testSolveTwoMeasurementsWithIncrement() throws URISyntaxException, IOException {
		
		// Read input file
		List<Integer> input = readFile(INPUT_FILE_TWO_MEASUREMENTS_WITH_INCREMENT);
		
		SonarSweep sonarSweep = new SonarSweep();
		
		int result = sonarSweep.solve(input);
		
		assertEquals(1, result);
	}
	
	@Test
	public void testSolveTwoMeasurementsWithNoIncrement() throws URISyntaxException, IOException {
		
		// Read input file
		List<Integer> input = readFile(INPUT_FILE_TWO_MEASUREMENTS_NO_INCREMENT);
		
		SonarSweep sonarSweep = new SonarSweep();
		
		int result = sonarSweep.solve(input);
		
		assertEquals(0, result);
	}

	@Test
	public void testSolveWindow() throws URISyntaxException, IOException {
		
		// Read input file
		List<Integer> input = readFile(INPUT_FILE);
		
		SonarSweep sonarSweep = new SonarSweep();
		
		int result = sonarSweep.solve(input, 3);
		//System.out.println(result); // gives 1378
		assertEquals(1378, result);
	}
	
	@Test
	public void testSolveWindowSample() throws URISyntaxException, IOException {
		
		// Read input file
		List<Integer> input = readFile(INPUT_FILE_SAMPLE);
		
		SonarSweep sonarSweep = new SonarSweep();
		
		int result = sonarSweep.solve(input, 3);
		
		assertEquals(5, result);
	}
	
	@Test
	public void testSolveWindowZeroMeasurementGroupsOneItem() throws URISyntaxException, IOException {
		
		// Read input file
		List<Integer> input = readFile(INPUT_FILE_ONE_MEASUREMENT);
		
		SonarSweep sonarSweep = new SonarSweep();
		
		int result = sonarSweep.solve(input, 3);
		
		assertEquals(0, result);
	}
	
	@Test
	public void testSolveWindowZeroMeasurementGroupsTwoItems() throws URISyntaxException, IOException {
		
		// Read input file
		List<Integer> input = readFile(INPUT_FILE_TWO_MEASUREMENTS_WITH_INCREMENT);
		
		SonarSweep sonarSweep = new SonarSweep();
		
		int result = sonarSweep.solve(input, 3);
		
		assertEquals(0, result);
	}
	
	@Test
	public void testSolveWindowOneMeasurementGroup() throws URISyntaxException, IOException {
		
		// Read input file
		List<Integer> input = readFile(INPUT_FILE_THREE_MEASUREMENTS_WITH_INCREMENTS);
		
		SonarSweep sonarSweep = new SonarSweep();
		
		int result = sonarSweep.solve(input, 3);
		
		assertEquals(0, result);
	}
	
	@Test
	public void testSolveWindowTwoMeasurementGroups() throws URISyntaxException, IOException {
		
		// Read input file
		List<Integer> input = readFile(INPUT_FILE_FOUR_MEASUREMENTS_WITH_INCREMENTS);
		
		SonarSweep sonarSweep = new SonarSweep();
		
		int result = sonarSweep.solve(input, 3);
		
		assertEquals(1, result);
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
