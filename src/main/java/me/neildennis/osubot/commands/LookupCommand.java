package me.neildennis.osubot.commands;

import java.io.IOException;
import java.util.HashMap;

import org.pircbotx.User;

import me.neildennis.osuapi.OsuApi;
import me.neildennis.osuapi.UserScore;
import me.neildennis.osubot.utils.Lang;

public class LookupCommand extends Command{

	private HashMap<String, String> lookups;
	private HashMap<String, String> lastmap;

	public LookupCommand(){
		lookups = new HashMap<String, String>();
		lastmap = new HashMap<String, String>();
	}
	
	public String getLastMap(String nick){
		return lastmap.get(nick);
	}
	
	public String getLastUser(String nick){
		return lookups.get(nick);
	}

	@Override
	public void handle(String[] args, User user) {
		String nick = user.getNick();
		String last = getLastMap(nick);
		
		if (args.length==1 && args[0].length()>1){
			String lookupname = args[0];
			lookups.put(nick, lookupname);

			if (last == null){
				user.send().message("User lookup scores set to " + Lang.getUserUrl(args[0]));
			} else {
				try {
					UserScore score = OsuApi.getUserScore(lookupname, true, last);
					user.send().message(lookupname + ": " + score.getPP() + "pp");
				} catch (IOException e) {
					e.printStackTrace();
					user.send().message(Lang.getString("error.apiconnect"));
				}
			}
		} else if (args.length>1){
			String lookup = getLastUser(nick);
			String bid = args[0];
			if (lookup == null) lookup = nick;

			try {
				UserScore score = OsuApi.getUserScore(lookup, true, bid);
				user.send().message(lookup + ": " + score.getPP() + "pp");
				lastmap.put(nick, bid);
			} catch (IOException e) {
				e.printStackTrace();
				user.send().message(Lang.getString("error.apiconnect"));
			}
		} else {
			lookups.remove(nick);

			if (last == null){
				user.send().message("User lookup scores set to " + Lang.getUserUrl(nick));
			} else {
				try {
					UserScore score = OsuApi.getUserScore(nick, true, last);
					user.send().message(nick + ": " + score.getPP() + "pp");
				} catch (IOException e) {
					e.printStackTrace();
					user.send().message(Lang.getString("error.apiconnect"));
				}
			}
		}
	}

}
