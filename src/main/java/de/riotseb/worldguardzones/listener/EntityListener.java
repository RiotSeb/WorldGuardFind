package de.riotseb.worldguardzones.listener;

import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import de.riotseb.worldguardzones.Main;
import de.riotseb.worldguardzones.command.FindRegionCommand;
import de.riotseb.worldguardzones.handler.MessageKey;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.EquipmentSlot;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class EntityListener implements Listener {

	private static List<UUID> findActivatedPlayers = FindRegionCommand.findActivatedPlayers;

	@EventHandler
	public void onQuit(PlayerQuitEvent event) {

		Player player = event.getPlayer();
		UUID playerId = player.getUniqueId();

		if (findActivatedPlayers.contains(playerId)) {
			findActivatedPlayers.remove(playerId);
		}

	}

	@EventHandler
	public void onInteract(PlayerInteractEvent event) {

		Player player = event.getPlayer();
		UUID playerId = player.getUniqueId();

		if (event.getAction() != Action.RIGHT_CLICK_BLOCK) {
			return;
		}

		if (!findActivatedPlayers.contains(playerId)) {
			return;
		}

		if (event.getHand() != EquipmentSlot.HAND) {
			return;
		}

		if (event.getMaterial() != Material.STICK) {
			return;
		}

		RegionManager regionManager = Main.getWorldGuard().getRegionManager(player.getWorld());
		Set<ProtectedRegion> regions = regionManager.getApplicableRegions(event.getClickedBlock().getLocation()).getRegions();

		event.setCancelled(true);

		if (regions.isEmpty()) {
			player.sendMessage(MessageKey.NO_REGION_FOUND.getMessage());
			return;
		}

		List<String> regionNames = regions.stream().map(ProtectedRegion::getId).collect(Collectors.toList());
		player.sendMessage(MessageKey.REGION_FOUND.getReplacedMessage("%region%", String.join(ChatColor.GRAY + ", " + ChatColor.GOLD, regionNames)));

	}

}
