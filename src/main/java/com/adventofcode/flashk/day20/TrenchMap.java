package com.adventofcode.flashk.day20;

import java.util.List;

import com.adventofcode.flashk.common.BaseUtil;

public class TrenchMap {
	
	private static final int INFINITE_SIZE_EVEN = 188;
	private static final int INFINITE_SIZE_ODD = 189;
	
	private int[] imageEnhancer;

	private int[][] evaluationImage;
	private int[][] enhancedImage;
	
	public TrenchMap( List<String> inputs) {
		
		// Initialize image enhancer data
		char[] imageEnhancementValues = inputs.get(0).toCharArray();
		imageEnhancer = new int[imageEnhancementValues.length];
		for(int i = 0; i < imageEnhancementValues.length; i++) {
			imageEnhancer[i] = (imageEnhancementValues[i] == '#') ? 1 : 0;
		}
		
		inputs.remove(0);
		inputs.remove(0);
		
		// Initialize arrays sizes
		int inputImageSize = inputs.get(0).length();
		boolean isEven = inputImageSize % 2 == 0;
		int infiniteSize;
		
		
		if(isEven) {
			infiniteSize = INFINITE_SIZE_EVEN;
		} else {
			infiniteSize = INFINITE_SIZE_ODD;
		}
		
		evaluationImage = new int[infiniteSize][infiniteSize];
		enhancedImage  = new int[infiniteSize][infiniteSize];
		
		// Initialize evaluationImage pixels
		int evaluationImageOffset = (infiniteSize - inputImageSize) / 2;

		int row = evaluationImageOffset;
		int col = evaluationImageOffset;
		for(String imageRow : inputs) {

			char[] pixels = imageRow.toCharArray();
			
			for(char pixel : pixels) {	
				int value = (pixel == '#') ? 1 : 0;
				evaluationImage[row][col] = value;
				col++;
			}
			col = evaluationImageOffset;
			row++;
		}
		
	}
	
	public int solveA(int iterations) {

		int litPixelCount = 0;

		for(int i = 0; i < iterations; i++) {

			litPixelCount = 0;
			
			for(int y = 0; y < evaluationImage.length; y++) {
				for(int x = 0; x < evaluationImage.length; x++) {
					int imageEnhancementIndex = getImageEnhancementIndex(y,x);
					int pixelValue = imageEnhancer[imageEnhancementIndex];	
					
					enhancedImage[y][x] = pixelValue;
					
					if(pixelValue == 1) {
						litPixelCount++;
					}
				}
			}
			
			increaseEnhancedImageSize();
		}
		
		return litPixelCount;
	}
	
	private int getImageEnhancementIndex(int y, int x) {

		StringBuilder imageEnhancementCode = new StringBuilder();
		for(int row = y-1; row <= y+1; row++) {
			
			// Works as it was an sphere or a cylinder, if the index is out of bounds
			// then it search for the index at the opposite site of the array.
			
			int finalY;
			if(row == -1) {
				finalY = evaluationImage.length - 1;
			} else if(row == evaluationImage.length) {
				finalY = 0;
			} else {
				finalY = row;
			}
			
			for(int col = x-1; col <= x+1; col++) {
				
				int finalX; 
				if(col == -1) {
					finalX = evaluationImage.length - 1;
				} else if(col == evaluationImage.length) {
					finalX = 0;
				} else {
					finalX = col;
				}
				
				imageEnhancementCode.append(evaluationImage[finalY][finalX]);
			}
		}
		
		return BaseUtil.binaryToDecInteger(imageEnhancementCode.toString());
	}

	private void increaseEnhancedImageSize() {
		
		this.evaluationImage = enhancedImage;
		enhancedImage = new int[evaluationImage.length][evaluationImage.length];
	}

}
