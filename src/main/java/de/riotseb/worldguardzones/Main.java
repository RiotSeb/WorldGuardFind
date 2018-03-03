package de.riotseb.worldguardzones;

import de.riotseb.worldguardzones.command.FindRegionCommand;
import de.riotseb.worldguardzones.handler.CommandHandler;
import lombok.Getter;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public class Main extends JavaPlugin {

	public static final String MESSAGE_FILE = "messages.yml";

	public static final String COMMAND_FIND_REGIONS = "find";

	public static final String PERM_COMMAND_FIND_REGIONS = "worldguardzones.find";

	public static final String ERROR_PLAYER_EXECUTE_ONLY = ChatColor.RED + "You must be a player to execute this command.";

	@Getter
	private static Main instance;

	@Override
	public void onEnable() {
		this.getLogger().log(Level.FINE, "Enabling plugin");

		Main.instance = this;
		registerCommands();


		this.saveResource("messages.yml", false);

	}

	@Override
	public void onDisable() {
		this.getLogger().log(Level.FINE, "Disabling plugin");
	}

	private void registerCommands() {
		CommandHandler.registerCommand(new FindRegionCommand());
	}


	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		CommandHandler.onCommand(sender, command, label, args);
		return true;
	}
}
