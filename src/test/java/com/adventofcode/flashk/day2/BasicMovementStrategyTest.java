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

import org.junit.Test;

public class BasicMovementStrategyTest {

	private final static String INPUT_FOLDER = "day2/";

	private final static String INPUT_FILE = "data.input";
	private final static String INPUT_FILE_SAMPLE = "sample.input";
	
	@Test
	public void testSolve() {
		
		// Read input file
		List<String> input = readFile(INPUT_FILE);
		
		Submarine submarine = new Submarine(new BasicMovementStrategy());
		int result = submarine.solve(input);

		assertEquals(1728414, result);
		
	}

	@Test
	public void testSolveSample() {
		
		// Read input file
		List<String> input = readFile(INPUT_FILE_SAMPLE);
		
		Submarine submarine = new Submarine(new BasicMovementStrategy());
		int result = submarine.solve(input);
		
		assertEquals(150, result);
		assertEquals((Integer) 15, submarine.getMovementStrategy().getHorizontalPosition());
		assertEquals((Integer) 10, submarine.getMovementStrategy().getDepth());
		
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
