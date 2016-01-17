package me.neildennis.osubot;

import java.io.IOException;

import org.pircbotx.Configuration;
import org.pircbotx.PircBotX;
import org.pircbotx.exception.IrcException;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.types.GenericMessageEvent;

/**
 * Hello world!
 *
 */
@SuppressWarnings("rawtypes")
public class App extends ListenerAdapter{

	@Override
	public void onGenericMessage(GenericMessageEvent event){
		if (event.getMessage().startsWith("!asdf"))
			event.respond("Hello world!");
	}

	@SuppressWarnings({ "unchecked" })
	public static void main(String[] args) {
		Configuration configuration = new Configuration.Builder()
				.setName("NeilBot") //Set the nick of the bot. CHANGE IN YOUR CODE
				.setServerHostname("irc.freenode.net") //Join the freenode network
				.addAutoJoinChannel("#pircbotx") //Join the official #pircbotx channel
				.addListener(new App()) //Add our listener that will be called on Events
				.buildConfiguration();

		//Create our bot with the configuration
		PircBotX bot = new PircBotX(configuration);
		//Connect to the server
		try {
			bot.startBot();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (IrcException e) {
			e.printStackTrace();
		}
	}
}
