package de.riotseb.worldguardzones.util;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class BeaconUtils {

	public static void createFakeBeacon(Player player, Location location) {

		int x = location.getBlockX();
		int y = location.getBlockY() - 30;
		int z = location.getBlockZ();

		for (int i = 2; i <= 255; i++) {
			player.sendBlockChange(new Location(location.getWorld(), x, i, z), Material.AIR, (byte) 0);
		}

		for (int xP = x - 1; xP <= x + 1; xP++) {

			for (int zP = z - 1; zP <= z + 1; zP++) {

				player.sendBlockChange(new Location(location.getWorld(), xP, y, zP), Material.DIAMOND_BLOCK, (byte) 0);

			}

		}

		player.sendBlockChange(new Location(location.getWorld(), x, y + 1, z), Material.BEACON, (byte) 0);

	}

	public static void undoFakeBeacon(Player player, Location location) {

		int x = location.getBlockX();
		int y = location.getBlockY() - 30;
		int z = location.getBlockZ();

		for (int i = 2; i <= 255; i++) {

			Location loc = new Location(location.getWorld(), x, i, z);

			player.sendBlockChange(loc, location.getWorld().getBlockAt(loc).getType(), (byte) 0);
		}

		for (int xP = x - 1; xP <= x + 1; xP++) {

			for (int zP = z - 1; zP <= z + 1; zP++) {

				Location loc = new Location(location.getWorld(), xP, y, zP);

				player.sendBlockChange(loc, location.getWorld().getBlockAt(loc).getType(), (byte) 0);

			}

		}

		Location loc = new Location(location.getWorld(), x, y + 1, z);

		player.sendBlockChange(loc, location.getWorld().getBlockAt(loc).getType(), (byte) 0);

	}

}
