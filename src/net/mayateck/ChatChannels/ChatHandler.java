package net.mayateck.ChatChannels;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class ChatHandler implements Listener{
	private ChatChannels plugin;
	public ChatHandler(ChatChannels plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onChatted(AsyncPlayerChatEvent e){
		e.setCancelled(true); // I'm overriding the event and faking a chat. Sorry other plug-ins!
		Player p = e.getPlayer();
		String n = p.getName();
		String m = e.getMessage();
		String c = plugin.getPlayersList().getString("players."+n+".channel");
		String t = plugin.getConfig().getString("channels."+c+".tag");
		String tc = plugin.getConfig().getString("channels."+c+".color");
		if (c.equalsIgnoreCase("admin")){
			Bukkit.getServer().broadcast("§"+tc+t+" "+n+": "+m, "chatchannels.group.admin");
		} else if (c.equalsIgnoreCase("mod")){
			Bukkit.getServer().broadcast("§"+tc+t+" "+n+": "+m, "chatchannels.group.admin");
			Bukkit.getServer().broadcast("§"+tc+t+" "+n+": "+m, "chatchannels.group.mod");
		} else {
			Bukkit.getServer().broadcastMessage("§"+tc+t+" "+n+": "+m);
		}
	}
	
	@EventHandler(priority=EventPriority.LOWEST)
	public void onPlayerJoin(PlayerJoinEvent e){
		Player p = e.getPlayer();
		String n = p.getName();
		String b = plugin.getConfig().getString("channels.broadcast.tag");
		String c = plugin.getConfig().getString("channels.broadcast.color");
		if (plugin.getPlayersList().contains("players."+n)){
			Bukkit.getServer().broadcastMessage("§"+c+b+" §i"+n+" §"+c+"has connected.");
		} else {
			plugin.getPlayersList().set("players."+n+".channel", "global");
			plugin.savePlayersList();
			Bukkit.getServer().broadcastMessage("§"+c+b+" §i"+n+" §"+c+"has connected for the first time.");
			Bukkit.getServer().broadcastMessage("§"+c+b+" Welcome §i"+n+" §"+c+"!");
		}
	}
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent e){
		Player p = e.getPlayer();
		String n = p.getName();
		String b = plugin.getConfig().getString("channels.broadcast.tag");
		String c = plugin.getConfig().getString("channels.broadcast.color");
		Bukkit.getServer().broadcastMessage("§"+c+b+" §i"+n+" §"+c+"has disconnected.");
	}
}
