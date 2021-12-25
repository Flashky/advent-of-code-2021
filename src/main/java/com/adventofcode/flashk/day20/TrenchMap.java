package com.adventofcode.flashk.day20;

import java.util.List;

import com.adventofcode.flashk.common.BaseUtil;
import com.adventofcode.flashk.common.Vector2;

public class TrenchMap {
	
	private int[] imageEnhancer;
	
	// If evaluationImage is 7x7, enhancedImage will be 11x11
	// In other words:
	// - enhancedImage size will be evaluation image size + imageSizeIncrement.
	private int[][] evaluationImage;
	private int[][] enhancedImage;
	
	private int imageSizeIncrement;
	private int enhancedImageSizeIncrement;
	private int evaluationImageOffset;
	
	
	//private int enhancedImageSizeX;
	//private int enhancedImageSizeY;
	
	private int litPixelCount = 0;
	
	public TrenchMap( List<String> inputs, int sampleSize) {
		
		// Initialize image enhancer data
		char[] imageEnhancementValues = inputs.get(0).toCharArray();
		imageEnhancer = new int[imageEnhancementValues.length];
		for(int i = 0; i < imageEnhancementValues.length; i++) {
			imageEnhancer[i] = (imageEnhancementValues[i] == '#') ? 1 : 0;
		}
		
		inputs.remove(0);
		inputs.remove(0);
		
		// Set increment and offset values which are based on sample size
		this.imageSizeIncrement = 2 * sampleSize - 2;
		this.enhancedImageSizeIncrement = 2 * (sampleSize /2);
		this.evaluationImageOffset = sampleSize / 2;
		
		// Eval image size
		int initialImageSize = inputs.get(0).length();
		
		int evaluationImageSize = initialImageSize + imageSizeIncrement;
		this.evaluationImage = new int[evaluationImageSize][evaluationImageSize];
		
		// Outer image size
		int enhancedImageSize = evaluationImageSize + enhancedImageSizeIncrement;
		enhancedImage = new int[enhancedImageSize][enhancedImageSize];
		
		// Initialize evaluationImage pixels
		int row = enhancedImageSizeIncrement;
		int col = enhancedImageSizeIncrement;
		for(String imageRow : inputs) {

			char[] pixels = imageRow.toCharArray();
			
			for(char pixel : pixels) {	
				int value = (pixel == '#') ? 1 : 0;
				evaluationImage[row][col] = value;
				col++;
			}
			col = enhancedImageSizeIncrement;
			row++;
		}
		
	}
	
	public int solveA(int iterations) {

		for(int i = 0; i < iterations; i++) {
			
			litPixelCount = 0;
			int offsetStart = evaluationImageOffset;
			int offsetEnd = evaluationImage.length - evaluationImageOffset;
			
			for(int y = offsetStart; y < offsetEnd; y++) {

				int enhancedImageOffsetY = y + evaluationImageOffset;
				
				for(int x = offsetStart; x < offsetEnd; x++) {
					
					int enhancedImageOffsetX = x + evaluationImageOffset;
					int imageEnhancementIndex = getImageEnhancementIndex(y,x);
					int pixelValue = imageEnhancer[imageEnhancementIndex];			
					
					enhancedImage[enhancedImageOffsetY][enhancedImageOffsetX] = pixelValue;
					
					if(pixelValue == 1) {
						litPixelCount++;
					}
				
				}
			}
			printImage();
			increaseEnhancedImageSize();
		}
		
		printImage();
		return litPixelCount;
	}
	
	private int getImageEnhancementIndex(int y, int x) {

		StringBuilder imageEnhancementCode = new StringBuilder();
		for(int row = y-1; row <= y+1; row++) {
			for(int col = x-1; col <= x+1; col++) {
				imageEnhancementCode.append(evaluationImage[row][col]);
			}
		}
		
		return BaseUtil.binaryToDecInteger(imageEnhancementCode.toString());
	}

	private void increaseEnhancedImageSize() {
		
		this.evaluationImage = enhancedImage;
		
		int enhancedImageSize = evaluationImage.length + enhancedImageSizeIncrement;
		enhancedImage = new int[enhancedImageSize][enhancedImageSize];
	}
	
	private void printImage() {
		System.out.println();
		for(int row = 0; row < evaluationImage.length; row++) {
			for(int col = 0; col < evaluationImage.length; col++) {
				if(evaluationImage[row][col] == 1) {
					System.out.print("#");
				} else {
					System.out.print(".");
				}
			}
			System.out.println();
		}
		System.out.println();
	}
}
