package me.neildennis.osubot;

import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.PrivateMessageEvent;

import me.neildennis.osubot.commands.Command;
import me.neildennis.osubot.handlers.CommandHandler;
import me.neildennis.osubot.utils.Log;

@SuppressWarnings("rawtypes")
public class BotEvents extends ListenerAdapter{
	
	private CommandHandler cmdhandle;
	
	public BotEvents(CommandHandler cmdhandle){
		this.cmdhandle = cmdhandle;
	}
	
	@Override
	public void onPrivateMessage(PrivateMessageEvent e){
		Log.debug(e.getUser().getNick() + ": " + e.getMessage());
		if (e.getMessage().startsWith("!")){
			String[] cmdstr = e.getMessage().substring(1).trim().split(" ");
			if (cmdstr.length==0) {
				e.respond("Invalid command");
				return;
			}
			
			Command cmd = cmdhandle.getHandler(cmdstr[0]);
			
			if (cmd==null) {
				e.respond("Invalid command");
				return;
			}
			
			String[] args = e.getMessage().substring(1).replace(cmdstr[0], "").trim().split(" ");
			
			cmd.handle(args, e.getUser());
		}
	}

}
