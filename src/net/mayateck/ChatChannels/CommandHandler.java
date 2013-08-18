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
		return false;
	}

}
