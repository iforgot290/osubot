package me.neildennis.osubot.utils;

public class Log {

	private static boolean debug = true;

	private Log(){}

	public static void info(Object msg){
		System.out.println(msg);
	}

	public static void debug(Object msg){
		if (debug) System.out.println("[DEBUG] " + msg);
	}

}
