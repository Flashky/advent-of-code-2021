package com.adventofcode.flashk.common;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class TimedTest {

	private Timer timer = new Timer();
	
	@BeforeEach
	public void before() {
		timer.start();
	}
	
	@AfterEach
	public void after() {
		timer.stop();
	}
}
