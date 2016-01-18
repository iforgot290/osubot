package me.neildennis.osubot.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class Config {
	
	private static Properties prop;
	
	public static void generateConfig(){
		File file = new File("config.properties");
		Log.debug(file.getAbsolutePath());
		prop = new Properties();
		if (file.exists()) {
			try {
				InputStream input = new FileInputStream(file);
				prop.load(input);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}
		
		try {
			OutputStream output = new FileOutputStream(file);
			
			prop.setProperty("irc.user", "user");
			prop.setProperty("irc.password", "password");
			prop.setProperty("irc.address", "irc.ppy.sh");
			prop.setProperty("irc.channel", "#osu");
			prop.setProperty("irc.port", "6667");
			
			prop.setProperty("osu.apikey", "key");
			
			prop.store(output, null);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String getString(String str){
		return prop.getProperty(str);
	}
	
	public static void setString(String str, String value){
		prop.setProperty(str, value);
		
		try {
			OutputStream output = new FileOutputStream("config.properties");
			prop.store(output, null);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
