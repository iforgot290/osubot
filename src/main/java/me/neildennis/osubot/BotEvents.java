package me.neildennis.osubot;

import org.pircbotx.hooks.Event;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.PrivateMessageEvent;

import me.neildennis.osubot.utils.Log;

@SuppressWarnings("rawtypes")
public class BotEvents extends ListenerAdapter{
	
	@Override
	public void onPrivateMessage(PrivateMessageEvent e){
		Log.info(e.getUser().getNick()+": "+e.getMessage());
		if (e.getMessage().startsWith("!")){
			String msg = e.getMessage().substring(1).trim();
		}
	}
	
	private BotUser getUser(final String nick, final Event event){
		return new BotUser(){

			public String getNick() {
				return nick;
			}

			public boolean message(String str) {
				return false;
			}
			
		};
	}

}
