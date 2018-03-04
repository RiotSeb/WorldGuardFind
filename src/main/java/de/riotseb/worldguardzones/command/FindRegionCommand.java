package de.riotseb.worldguardzones.command;

import com.google.common.collect.Lists;
import de.riotseb.worldguardzones.Main;
import de.riotseb.worldguardzones.handler.MessageHandler;
import de.riotseb.worldguardzones.handler.MessageKey;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.UUID;

public class FindRegionCommand implements CommandExecutor {

	public static List<UUID> findActivatedPlayers = Lists.newArrayList();

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		if (!(sender instanceof Player)) {
			sender.sendMessage(Main.ERROR_PLAYER_EXECUTE_ONLY);
			return true;
		}

		Player player = (Player) sender;

		if (!player.hasPermission(Main.PERM_COMMAND_FIND_REGIONS)) {
			player.sendMessage(MessageKey.NO_PERMISSIONS.getMessage());
			return true;
		}

		if (args.length == 0) {

			if (findActivatedPlayers.contains(player.getUniqueId())) {
				findActivatedPlayers.remove(player.getUniqueId());
				player.sendMessage(MessageKey.FIND_DISABLED.getMessage());
			} else {

				if (!player.getInventory().contains(Material.STICK)) {
					player.getInventory().addItem(new ItemStack(Material.STICK));
				}

				findActivatedPlayers.add(player.getUniqueId());
				player.sendMessage(MessageKey.FIND_ENABLED.getMessage());
			}

			return true;
		}
		player.spigot().sendMessage(MessageHandler.getInstance().getUsageSyntax(label));
		return true;
	}
}
