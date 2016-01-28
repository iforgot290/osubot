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

	@Override
	public void handle(String[] args, User user) {
		String nick = user.getNick();
		if (args.length==1 && args[0].length()>1){
			lookups.put(nick, args[0]);
			String last = lastmap.get(nick);

			if (last == null){
				user.send().message("User lookup scores set to " + Lang.getUserUrl(args[0]));
			} else {
				try {
					UserScore score = OsuApi.getUserScore(args[0], true, last);
					user.send().message(args[0] + ": " + score.getPP() + "pp");
				} catch (IOException e) {
					e.printStackTrace();
					user.send().message(Lang.getString("error.apiconnect"));
				}
			}
		} else if (args.length>1){
			String lookup = lookups.get(user.getNick());
			if (lookup == null) lookup = user.getNick();

			try {
				UserScore score = OsuApi.getUserScore(lookup, true, args[0]);
				user.send().message(lookup + ": " + score.getPP() + "pp");
				lastmap.put(nick, args[0]);
			} catch (IOException e) {
				e.printStackTrace();
				user.send().message(Lang.getString("error.apiconnect"));
			}
		} else {
			lookups.remove(nick);
			String last = lastmap.get(nick);

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
