package de.riotseb.worldguardzones;

import com.sk89q.worldguard.bukkit.WGBukkit;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import de.riotseb.worldguardzones.command.FindRegionCommand;
import de.riotseb.worldguardzones.listener.EntityListener;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public class Main extends JavaPlugin {

	public static final String MESSAGE_FILE = "messages.yml";

	public static final String COMMAND_FIND_REGIONS = "wgfind";

	public static final String PERM_COMMAND_FIND_REGIONS = "worldguardzones.find";

	public static final String ERROR_PLAYER_EXECUTE_ONLY = ChatColor.RED + "You must be a player to execute this command.";

	@Getter
	private static Main instance;

	@Getter
	private static WorldGuardPlugin worldGuard;

	@Override
	public void onEnable() {
		this.getLogger().log(Level.FINE, "Enabling plugin");

		this.saveResource(MESSAGE_FILE, false);

		Main.instance = this;
		Main.worldGuard = WGBukkit.getPlugin();
		registerCommands();
		registerEvents();

	}

	@Override
	public void onDisable() {
		this.getLogger().log(Level.FINE, "Disabling plugin");
	}

	private void registerCommands() {
		getCommand(COMMAND_FIND_REGIONS).setExecutor(new FindRegionCommand());
	}

	private void registerEvents() {
		Bukkit.getPluginManager().registerEvents(new EntityListener(), this);
	}

}
