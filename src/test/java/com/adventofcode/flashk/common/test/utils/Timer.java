package com.adventofcode.flashk.common.test.utils;

public class Timer {

	private Long startTime = null;
	private boolean running = false;
	
	public void start() {
		
		this.running = true;
		this.startTime = System.currentTimeMillis();
	}
	
	public void stop() {
		
		if(!running) {
			throw new IllegalStateException("Timer is not running.");
		}

		Long stopTime = System.currentTimeMillis();
		Long elapsedTime = stopTime - startTime;
		running = false;		
		
		System.out.println(elapsedTime + "ms");
		
	}
	
	public static void printHeader(String day) {
		System.out.println();
		System.out.println("========= "+ day + " ==========");
		System.out.println("---------------------------");
		System.out.println("# | Input  | Elapsed time");
		System.out.println("---------------------------");
	}
}
