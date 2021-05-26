package com.flipkart.input;

import java.util.Scanner;

public class IO {
	public Scanner input;
	private static IO instance = null;
	
	private IO() {
		input = new Scanner(System.in);
	}
	
	public static IO getInstance() {
		if(instance == null) {
			instance = new IO();
		}
		return instance;
	}

}
