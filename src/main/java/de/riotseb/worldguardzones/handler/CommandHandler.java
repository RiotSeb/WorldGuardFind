package de.riotseb.worldguardzones.handler;

import com.google.common.collect.Lists;
import de.riotseb.worldguardzones.util.Command;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import org.bukkit.command.CommandSender;

import java.util.List;

public class CommandHandler {

	private static List<Command> commands = Lists.newArrayList();

	public static void registerCommand(Command command) {
		commands.add(command);
	}

	public static void onCommand(CommandSender sender, org.bukkit.command.Command cmd, String label, String[] args) {

		commands.forEach(command -> {
			if (label.equals(command.getLabel())) {

				if (sender.hasPermission(command.getPermission())) {

					if (!command.execute(sender, args)) {

						BaseComponent[] components = new ComponentBuilder(ChatColor.RED + "Usage: ")
								.append("\n\n")
								.append("     \u00BB " + command.getUsageString())
								.color(ChatColor.GREEN)
								.event(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, command.getUsageString()))
								.create();

						sender.spigot().sendMessage(components);

					}

				} else {
					sender.sendMessage(MessageHandler.getInstance().getMessage("no perms"));
				}

			}
		});

	}
}
