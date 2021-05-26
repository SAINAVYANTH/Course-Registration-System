package com.flipkart.input;

import java.io.PrintStream;
import java.util.Scanner;

public class IO {
	public Scanner input;
	public PrintStream output;
	private static IO instance = null;
	
	private IO() {
		input = new Scanner(System.in);
		output = System.out;
	}
	
	public static IO getInstance() {
		if(instance != null) {
			instance = new IO();
		}
		return instance;
	}

}
