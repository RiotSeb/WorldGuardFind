package de.riotseb.worldguardzones.handler;

import de.riotseb.worldguardzones.Main;
import lombok.Getter;
import org.bukkit.ChatColor;
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

}
