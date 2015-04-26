package me.Elliott_.Progression.menus;

import me.Elliott_.Progression.Progression;
import me.Elliott_.Progression.menus.promts.AddBuilderPromt;
import me.Elliott_.Progression.menus.promts.AddOwnerPromt;
import me.Elliott_.Progression.menus.promts.AddViewerPromt;
import me.Elliott_.Progression.util.ItemUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.conversations.Conversable;
import org.bukkit.conversations.ConversationFactory;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class WorldEdititingMenu implements Listener{

    /** GUI for the editing menu */
    public static void worldEditor(Player player, World world) {
        Inventory inventory = Bukkit.createInventory(null, 27, ChatColor.GOLD + "Editing " + ChatColor.GREEN + world.getName());
        inventory.setItem(0, ItemUtils.createItem(Material.LEASH, 1, ChatColor.GOLD + "Add owner"));
        inventory.setItem(1, ItemUtils.createItem(Material.DIRT, 1, ChatColor.GOLD + "Add builder"));
        inventory.setItem(2, ItemUtils.createItem(Material.GLASS, 1, ChatColor.GOLD + "Add viewer"));
        player.openInventory(inventory);
    }

    @EventHandler
    public void onIneventoryClick(InventoryClickEvent event) {
        /**  Selecting a world to edit */
        if (ChatColor.stripColor(event.getInventory().getName()).equalsIgnoreCase("Click a world to edit")) {
            event.setCancelled(true);
            if (Progression.getPlugin().getServer().getWorld(ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName())) != null) {
                String name = ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName());
                event.getWhoClicked().closeInventory();
                worldEditor((Player) event.getWhoClicked(), Bukkit.getWorld(name));
            }
            /** Editing world */
        } else if (ChatColor.stripColor(event.getInventory().getName()).startsWith("Editing")) {
            event.setCancelled(true);
            if (event.getCurrentItem() != null && event.getCurrentItem().getType() != Material.AIR && event.getCurrentItem().hasItemMeta()) {
                /** adding a owner */
                if (ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()).equals("Add owner")) {
                    event.getWhoClicked().closeInventory();
                    ConversationFactory factory = new ConversationFactory(Progression.getPlugin()).withModality(true).withFirstPrompt(new AddOwnerPromt()).withEscapeSequence("quit").withTimeout(10).thatExcludesNonPlayersWithMessage("Only players can use this!");
                    if (event.getWhoClicked() != null) {
                        factory.buildConversation((Conversable) event.getWhoClicked()).begin();
                        if (event.getInventory() != null)
                            event.getWhoClicked().closeInventory();
                    }
                    /** adding a builder */
                } else if (ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()).equals("Add builder")) {
                    event.getWhoClicked().closeInventory();
                    ConversationFactory factory = new ConversationFactory(Progression.getPlugin()).withModality(true).withFirstPrompt(new AddBuilderPromt()).withEscapeSequence("quit").withTimeout(10).thatExcludesNonPlayersWithMessage("Only players can use this!");
                    if (event.getWhoClicked() != null) {
                        factory.buildConversation((Conversable) event.getWhoClicked()).begin();
                        if (event.getInventory() != null)
                            event.getWhoClicked().closeInventory();
                    }
                    /** adding a viewer */
                } else if (ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()).equals("Add viewer")) {
                    event.getWhoClicked().closeInventory();
                    ConversationFactory factory = new ConversationFactory(Progression.getPlugin()).withModality(true).withFirstPrompt(new AddViewerPromt()).withEscapeSequence("quit").withTimeout(10).thatExcludesNonPlayersWithMessage("Only players can use this!");
                    if (event.getWhoClicked() != null) {
                        factory.buildConversation((Conversable) event.getWhoClicked()).begin();
                        if (event.getInventory() != null)
                            event.getWhoClicked().closeInventory();
                    }
                }

            }
        }
    }

}
