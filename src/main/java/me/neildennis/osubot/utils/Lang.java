package me.neildennis.osubot.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import me.neildennis.osuapi.Beatmap;

public class Lang {
	
	private static Properties lang;
	
	public static String getUserUrl(String str){
		return "[http://osu.ppy.sh/u/" + str + " " + str + "]";
	}
	
	public static String getBeatmapUrl(Beatmap beatmap){
		return "[http://osu.ppy.sh/b/" + beatmap.getId() + " " + beatmap.getArtist() + " - " + beatmap.getTitle() +
				" [" + beatmap.getVersion() + "]";
	}
	
	public static void generateLang(){
		File file = new File("lang.properties");
		lang = new Properties();
		if (file.exists()) {
			try {
				InputStream input = new FileInputStream(file);
				lang.load(input);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}
		
		try {
			OutputStream output = new FileOutputStream(file);
			
			lang.setProperty("error.unkowncmd", "Unkown command ([http://osu.neildennis.me/commands http://osu.neildennis.me/commands])");
			lang.setProperty("error.apiconnect", "Error connecting to osu!api");
			
			lang.store(output, null);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String getString(String key){
		return lang.getProperty(key);
	}

}
