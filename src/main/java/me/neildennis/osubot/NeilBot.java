package me.neildennis.osubot;

import org.pircbotx.Configuration;
import org.pircbotx.PircBotX;

import me.neildennis.osubot.commands.TestCommand;
import me.neildennis.osubot.handlers.CommandHandler;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class NeilBot {
	
	private String address;
	private String username;
	private String password;
	private String channel;
	
	private CommandHandler cmd;
	private PircBotX bot;
	
	public NeilBot(){
		this.address = "irc.ppy.sh";
		this.username = "-Neil";
		this.password = "";
		this.channel = "#osu";
	}
	
	public static void main(String[] args){
		
	}
	
	public void init(){
		cmd = new CommandHandler();
		cmd.registerCommand("test", new TestCommand());
		
		Configuration<PircBotX> config = new Configuration.Builder()
				.setName(username)
				.setServerPassword(password)
				.setServerHostname(address)
				.addAutoJoinChannel(channel)
				.addListener(new BotEvents())
				.buildConfiguration();
		
		bot = new PircBotX(config);
	}

}
