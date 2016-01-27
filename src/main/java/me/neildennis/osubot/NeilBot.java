package me.neildennis.osubot;

import java.util.Properties;

import org.pircbotx.Configuration;
import org.pircbotx.PircBotX;

import me.neildennis.osubot.commands.HelpCommand;
import me.neildennis.osubot.commands.LookupCommand;
import me.neildennis.osubot.commands.TestCommand;
import me.neildennis.osubot.handlers.CommandHandler;
import me.neildennis.osubot.utils.Config;
import me.neildennis.osubot.utils.Log;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class NeilBot {
	
	private String address;
	private String username;
	private String password;
	private String channel;
	private int port;
	
	private CommandHandler cmd;
	private PircBotX bot;
	
	public NeilBot(){
		this.address = Config.getString("irc.address");
		this.username = Config.getString("irc.user");
		this.password = Config.getString("irc.password");
		this.channel = Config.getString("irc.channel");
		this.port = Integer.parseInt(Config.getString("irc.port"));
	}
	
	public static void main(String[] args){
		Log.debug("Generating configs");
		Config.generateConfig();
		
		//set osu api key as system property
		Properties sysprop = System.getProperties();
		sysprop.setProperty("osu.apikey", Config.getString("osu.apikey"));
		
		Log.debug("Starting bot");
		NeilBot botimpl = new NeilBot();
		botimpl.init();
	}
	
	public void init(){
		cmd = new CommandHandler();
		cmd.registerCommand("test", new TestCommand());
		cmd.registerCommand("help", new HelpCommand());
		cmd.registerCommand("lookup", new LookupCommand());
		
		Configuration<PircBotX> config = new Configuration.Builder()
				.setName(username)
				.setServerPassword(password)
				.setServerHostname(address)
				.setServerPort(port)
				.addAutoJoinChannel(channel)
				.addListener(new BotEvents(cmd))
				.buildConfiguration();
		
		bot = new PircBotX(config);
		
		try {
			bot.startBot();
		} catch (Exception e) {
			Log.info("Error connecting to server");
			Log.debug(e.getMessage());
			e.printStackTrace();
		}
	}

}
