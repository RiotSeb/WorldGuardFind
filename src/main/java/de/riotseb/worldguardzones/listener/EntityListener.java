package de.riotseb.worldguardzones.listener;

import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import de.riotseb.worldguardzones.Main;
import de.riotseb.worldguardzones.handler.MessageKey;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
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
import java.util.stream.Collectors;

public class EntityListener implements Listener {

	@EventHandler
	public void onQuit(PlayerQuitEvent event) {

		Player player = event.getPlayer();

		if (player.hasMetadata("find")) {
			player.removeMetadata("find", Main.getInstance());
		}

	}

	@EventHandler
	public void onInteract(PlayerInteractEvent event) {

		Player player = event.getPlayer();

		if (event.getHand() != EquipmentSlot.HAND) {
			return;
		}

		if (event.getMaterial() != Material.STICK) {
			return;
		}

		if (player.hasMetadata("find")) {
			onFind(event);
		}

	}

	private void onFind(PlayerInteractEvent event) {

		Player player = event.getPlayer();

		if (event.getAction() != Action.RIGHT_CLICK_BLOCK) {
			return;
		}

		RegionManager regionManager = Main.getWorldGuard().getRegionManager(player.getWorld());
		Set<ProtectedRegion> regions = regionManager.getApplicableRegions(event.getClickedBlock().getLocation()).getRegions();

		event.setCancelled(true);

		if (regions.isEmpty()) {
			player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(MessageKey.NO_REGION_FOUND.getMessage()));
			return;
		}

		List<String> regionNames = regions.stream().map(ProtectedRegion::getId).collect(Collectors.toList());
		BaseComponent[] message = TextComponent.fromLegacyText(MessageKey.REGION_FOUND.getReplacedMessage("%region%", String.join(ChatColor.GRAY + ", " + ChatColor.GOLD, regionNames)));
		player.spigot().sendMessage(ChatMessageType.ACTION_BAR, message);

	}

}
