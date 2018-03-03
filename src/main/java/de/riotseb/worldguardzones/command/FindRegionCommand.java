package de.riotseb.worldguardzones.command;

import com.google.common.collect.Lists;
import de.riotseb.worldguardzones.Main;
import de.riotseb.worldguardzones.handler.MessageHandler;
import de.riotseb.worldguardzones.util.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.UUID;

public class FindRegionCommand extends Command {

	private  List<UUID> findActivatedPlayers = Lists.newArrayList();

	public FindRegionCommand() {
		super(Main.COMMAND_FIND_REGIONS, Main.PERM_COMMAND_FIND_REGIONS, Main.getInstance(), "/find");
	}

	@Override
	public boolean execute(CommandSender sender, String[] args) {

		if (!(sender instanceof Player)){
			sender.sendMessage(Main.ERROR_PLAYER_EXECUTE_ONLY);
			return true;
		}

		Player player = (Player) sender;

		if (args.length == 0) {

			if (findActivatedPlayers.contains(player.getUniqueId())){
				findActivatedPlayers.remove(player.getUniqueId());
				player.sendMessage(MessageHandler.getInstance().getMessage("find enabled"));
			} else {
				findActivatedPlayers.add(player.getUniqueId());
				player.sendMessage(MessageHandler.getInstance().getMessage("find disabled"));
			}

			return true;
		}
		return false;
	}


}
