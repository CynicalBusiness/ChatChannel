package net.mayateck.ChatChannels;

import net.mayateck.ChatChannels.ChatChannels;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandHandler implements CommandExecutor {
	private ChatChannels plugin;
	
	public CommandHandler(ChatChannels plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender s, Command cmd, String l, String[] args) {
		if (cmd.getName().equalsIgnoreCase("channel")){
			if (args.length==1){
				String c = args[0].toLowerCase();
				if (!c.equalsIgnoreCase("broadcast") && s.hasPermission("chatchannels.channel."+c) && plugin.getConfig().contains("channels."+c)){
					plugin.getPlayersList().set("players."+s.getName()+".channel", c);
					plugin.savePlayersList();
					s.sendMessage(ChatChannels.head+" Successfully joined '"+c+"'.");
					return true;
				} else if (args[0].equalsIgnoreCase("broadcast")) {
					s.sendMessage(ChatChannels.head+" You can't join the broadcast channel!");
					return true;
				} else if (!s.hasPermission("chatchannels.channel."+c)){
					s.sendMessage(ChatChannels.head+" You don't have permission for that channel!");
					return true;
				} else {
					s.sendMessage(ChatChannels.head+" That channel doesn't exist.");
					return true;
				}
			} else {
				s.sendMessage(ChatChannels.head+" Invalid argument number.");
				return true;
			}
		} else if (cmd.getName().equalsIgnoreCase("chatchannels")){
			if (args.length==0){
				s.sendMessage(ChatChannels.head+"§7#§c===§7# §9ChatChannels §fby §6Wehttam664 §7#§c===§7#");
				s.sendMessage(ChatChannels.head+"Current Version: §ev"+plugin.getDescription().getVersion()+"§f.");
				s.sendMessage(ChatChannels.head+"Looking for help? Try §7/chatchannels help§f.");
				return true;
			} else if(args.length==1){
				if (args[0].equalsIgnoreCase("relaod")){
					plugin.reloadConfig();
					plugin.reloadPlayersList();
					s.sendMessage(ChatChannels.head+"Configuration §ereloaded§f.");
					return true;
				}
			}
		}
		return false;
	}

}
