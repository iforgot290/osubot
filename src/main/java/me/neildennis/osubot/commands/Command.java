package me.neildennis.osubot.commands;

import org.pircbotx.User;

public abstract class Command {
	
	public abstract void handle(String[] args, User user);

}
