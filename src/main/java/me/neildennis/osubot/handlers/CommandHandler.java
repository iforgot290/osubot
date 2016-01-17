package me.neildennis.osubot.handlers;

import java.util.HashMap;

import me.neildennis.osubot.commands.Command;

public class CommandHandler {
	
	private HashMap<String, Command> commands;
	
	public CommandHandler(){
		commands = new HashMap<String, Command>();
	}
	
	public void registerCommand(String str, Command cmd){
		commands.put(str.toLowerCase(), cmd);
	}
	
	public Command getHandler(String str){
		return commands.get(str.toLowerCase());
	}
	
}
