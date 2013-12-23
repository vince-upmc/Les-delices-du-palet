package com.delices.utils;


public class Logger {

	public static void writeLog(String entry) {
		entry = "[" + Timing.getCurrentTime() + "] - " + entry;
		System.out.println(entry);
	}

}
