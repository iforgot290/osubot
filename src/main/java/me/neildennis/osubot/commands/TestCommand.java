package me.neildennis.osubot.commands;

import org.pircbotx.User;

public class TestCommand extends Command {

	@Override
	public void handle(String[] args, User user) {
		StringBuilder build = new StringBuilder();
		for (int i = 1; i < args.length; i++){
			build.append(args[i]);
			if (i!=args.length)
				build.append(" ");
		}
		
		user.send().message(build.toString());
	}

}
