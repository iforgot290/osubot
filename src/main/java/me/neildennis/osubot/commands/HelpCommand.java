package me.neildennis.osubot.commands;

import org.pircbotx.User;

public class HelpCommand extends Command{

	@Override
	public void handle(String[] args, User user) {
		user.send().message("Full commands list here: [http://osu.neildennis.me/commands http://osu.neildennis.me/commands]");
	}

}
