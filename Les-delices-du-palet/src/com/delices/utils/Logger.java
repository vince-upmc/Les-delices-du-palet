package com.delices.utils;

import javax.servlet.http.HttpServlet;
/**
 * classe pour l'enregistrement des logs
 * @author yoyo
 *
 */
public class Logger {

	public static void writeLog(String entry) {
		entry = "[" + Timing.getCurrentTime() + "] - " + entry;
		System.out.println(entry);
	}

	public static void writeLog(HttpServlet serv, String entry) {
		entry = "[" + Timing.getCurrentTime() + "] - " + entry;
		serv.log(entry);
		System.out.println(entry);
	}

}
