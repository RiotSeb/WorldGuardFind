package de.riotseb.worldguardzones.handler;

import de.riotseb.worldguardzones.Main;
import lombok.Getter;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class MessageHandler {

	@Getter(lazy = true)
	private static final MessageHandler instance = new MessageHandler();
	private static FileConfiguration messageConfig;

	private static final String ERROR_MESSAGE_NOT_FOUND = ChatColor.RED + "Message not found in config. Please contact an administrator. (%s)";

	public MessageHandler() {

		File messageFile = new File(Main.getInstance().getDataFolder() + File.separator + Main.MESSAGE_FILE);
		messageConfig = YamlConfiguration.loadConfiguration(messageFile);

	}

	public String getMessage(String key) {

		String message = messageConfig.getString(key);

		if (message == null) {
			return String.format(ERROR_MESSAGE_NOT_FOUND, key);
		}

		return ChatColor.translateAlternateColorCodes('&', message);
	}

	public BaseComponent[] getUsageSyntax(String label) {
		label = "/" + label;
		return new ComponentBuilder(MessageKey.USAGE.getMessage())
				.color(ChatColor.RED)
				.append("\n\n")
				.append("      \u00BB " + label)
				.color(ChatColor.GREEN)
				.event(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, label))
				.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText(MessageKey.USAGE_HOVER.getMessage())))
				.append("\n")
				.create();
	}

}
