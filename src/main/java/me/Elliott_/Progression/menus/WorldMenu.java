package me.Elliott_.Progression.menus;

import me.Elliott_.Progression.Progression;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class WorldMenu implements Listener {

    @EventHandler
    public void onIneventoryClick(InventoryClickEvent event) {
        if (ChatColor.stripColor(event.getInventory().getName()).equalsIgnoreCase("World Menu")) {
            event.setCancelled(true);
            if (event.getCurrentItem() != null && event.getCurrentItem().getType() != Material.AIR && event.getCurrentItem().hasItemMeta()) {
                if (Progression.getPlugin().getServer().getWorld(ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName())) != null) {
                    event.getWhoClicked().sendMessage(ChatColor.GOLD + "Teleporting to" + ChatColor.AQUA + ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()));
                    event.getWhoClicked().teleport(Progression.getPlugin().getServer().getWorld(ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName())).getSpawnLocation());
                }
            }
        }
    }

}
