package de.riotseb.worldguardzones.util;

import de.riotseb.worldguardzones.Main;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.command.CommandSender;

public abstract class Command {

	@Getter
	private String label;
	@Getter
	private String permission;
	@Getter
	private Main plugin;

	@Setter
	@Getter
	private String usageString;


	public Command(String label, String permission, Main plugin, String usage){
		this.label = label;
		this.permission = permission;
		this.plugin = plugin;
		this.usageString = usage;
	}

	public abstract boolean execute(CommandSender sender, String[] args);

}
