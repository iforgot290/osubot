package me.neildennis.osubot.commands;

import java.io.IOException;
import java.util.HashMap;

import org.pircbotx.User;

import me.neildennis.osuapi.Beatmap;
import me.neildennis.osuapi.OsuApi;
import me.neildennis.osuapi.UserScore;
import me.neildennis.osubot.utils.Log;

public class LookupCommand extends Command{

	private HashMap<String, String> lookups;
	
	public LookupCommand(){
		lookups = new HashMap<String, String>();
	}
	
	@Override
	public void handle(String[] args, User user) {
		String nick = user.getNick();
		if (args.length==1 && args[0].length()>1){
			Log.debug("if switch 1");
			lookups.put(nick, args[0]);
			user.send().message("User lookup scores set to [http://osu.ppy.sh/u/"+args[0]+" "+args[0]+"]");
		} else if (args.length>1){
			String lookup = lookups.get(user.getNick());
			if (lookup == null) lookup = user.getNick();
			
			try {
				UserScore score = OsuApi.getUserScore(lookup, true, args[0]);
				String reply = lookup + ": " + score.getPP() + "pp";
				user.send().message(reply);
			} catch (IOException e) {
				e.printStackTrace();
				user.send().message("Error looking up score for [http://osu.ppy.sh/u/"+lookup+" "+lookup+"]");
			}
		} else {
			Log.debug("if switch 3");
			lookups.remove(nick);
			user.send().message("User lookup scores set to [http://osu.ppy.sh/u/"+nick+" "+nick+"]");
		}
	}

}
