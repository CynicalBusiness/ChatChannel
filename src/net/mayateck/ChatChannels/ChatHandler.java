package net.mayateck.ChatChannels;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatHandler implements Listener{
	private ChatChannels plugin;
	public ChatHandler(ChatChannels plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onChatted(AsyncPlayerChatEvent e){
		Player p = e.getPlayer();
		String m = e.getMessage();
		e.setCancelled(true); // I'm overriding the event and faking a chat. Sorry other plug-ins!
	}
}
