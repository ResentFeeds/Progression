package me.Elliott_.Progression.lobby;

import me.Elliott_.Progression.Progression;
import me.Elliott_.Progression.util.ItemUtils;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;

import java.util.Set;

public class Lobby implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (getLobby() != null) {
            event.getPlayer().sendMessage(ChatColor.GREEN + "Teleporting you to the lobby...");
            event.getPlayer().teleport(getLobby().getSpawnLocation());
            updateInventory(event.getPlayer());
            event.getPlayer().setGameMode(GameMode.CREATIVE);
        }
    }

    public static World getLobby() {
        if (Progression.getPlugin().getConfig().getConfigurationSection("worlds") != null) {
            Set<String> worlds = Progression.getPlugin().getConfig().getConfigurationSection("worlds").getKeys(false);
            for (String world : worlds) {
                if (Progression.getPlugin().getConfig().get("worlds." + world + ".lobby") != null) {
                    if (Progression.getPlugin().getConfig().getString("worlds." + world + ".lobby").equals("true"))
                        return Bukkit.getWorld(world);
                }
            }
        }
        return Bukkit.getWorlds().get(0); //return default world if there is no lobby
    }

    private void updateInventory(Player player) {
        Inventory inventory = player.getInventory();
        inventory.setItem(0, ItemUtils.createItem(Material.COMPASS, 1, null));
        inventory.setItem(1, ItemUtils.createItem(Material.DIAMOND_PICKAXE, 1, ChatColor.GREEN + "Progression Menu"));
    }

}
