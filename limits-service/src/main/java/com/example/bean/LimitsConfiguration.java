package com.example.bean;

public class LimitsConfiguration {

	private int maxinum;
	private int minimum;
	
	protected LimitsConfiguration() {
		
	}

	public LimitsConfiguration(int maxinum, int minimum) {
		super();
		this.maxinum = maxinum;
		this.minimum = minimum;
	}

	public int getMaxinum() {
		return maxinum;
	}

	public int getMinimum() {
		return minimum;
	}
	
}
