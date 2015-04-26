package me.Elliott_.Progression.menus;

import me.Elliott_.Progression.Progression;
import me.Elliott_.Progression.util.ItemUtils;
import me.Elliott_.Progression.util.WorldUtil;
import me.Elliott_.Progression.world.GenerateWorld;
import org.bukkit.*;
import org.bukkit.conversations.Conversable;
import org.bukkit.conversations.ConversationFactory;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class Menus implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
            updateInventory(event.getPlayer());
    }

    private void updateInventory(Player player) {
        Inventory inventory = player.getInventory();
        inventory.setItem(0, ItemUtils.createItem(Material.COMPASS, 1, null));
        inventory.setItem(1, ItemUtils.createItem(Material.DIAMOND_PICKAXE, 1, ChatColor.GREEN + "Progression Menu"));
    }

    public void progressionMenu(Player player) {
        Inventory inventory = Bukkit.createInventory(null, 9, ChatColor.AQUA + "Progression Menu");
        inventory.setItem(2, ItemUtils.createItem(Material.GRASS, 1, ChatColor.GREEN + "Create World"));
        inventory.setItem(4, ItemUtils.createItem(Material.ANVIL, 1, (short) 0, ChatColor.GOLD + "World Manager", Arrays.asList(ChatColor.YELLOW + "Edit and configure world properties")));
        inventory.setItem(6, ItemUtils.createItem(Material.COMPASS, 1, ChatColor.RED + "Worlds"));
        player.openInventory(inventory);
    }

    @EventHandler
    public void onIneventoryClick(InventoryClickEvent event) {
        if (ChatColor.stripColor(event.getInventory().getName()).equalsIgnoreCase("Progression Menu")) {
            event.setCancelled(true);
            if (event.getCurrentItem() != null && event.getCurrentItem().getType() != Material.AIR && event.getCurrentItem().hasItemMeta()) {
                switch (event.getCurrentItem().getType()) {
                    case GRASS:
                        ConversationFactory factory = new ConversationFactory(Progression.getPlugin()).withModality(true).withFirstPrompt(new CreateWorldPrompt()).withEscapeSequence("quit").withTimeout(10).thatExcludesNonPlayersWithMessage("Only players can use this!");
                        if (event.getWhoClicked() != null) {
                            factory.buildConversation((Conversable) event.getWhoClicked()).begin();
                            if (event.getInventory() != null)
                                event.getWhoClicked().closeInventory();
                        }
                        break;
                    case COMPASS:
                        event.getWhoClicked().closeInventory();
                        worldsMenu((Player) event.getWhoClicked(), "World Menu");
                        break;
                    case ANVIL:
                        worldsMenu((Player) event.getWhoClicked(), "Click a world to edit");
                }
            } else event.getWhoClicked().closeInventory();
        } else if (ChatColor.stripColor(event.getInventory().getName()).equalsIgnoreCase("World Menu")) {
            event.setCancelled(true);
            if (event.getCurrentItem() != null && event.getCurrentItem().getType() != Material.AIR && event.getCurrentItem().hasItemMeta()) {
                if (Progression.getPlugin().getServer().getWorld(ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName())) != null) {
                    event.getWhoClicked().sendMessage(ChatColor.GOLD + "Teleporting to" + ChatColor.AQUA + ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()));
                    event.getWhoClicked().teleport(Progression.getPlugin().getServer().getWorld(ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName())).getSpawnLocation());
                }
            }
        } else if (ChatColor.stripColor(event.getInventory().getName()).equalsIgnoreCase("Click a world to edit")) {
            event.setCancelled(true);
            if (Progression.getPlugin().getServer().getWorld(ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName())) != null) {
                String name = ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName());
                event.getWhoClicked().closeInventory();
                worldEditor((Player) event.getWhoClicked(), Bukkit.getWorld(name));
            }
        } else if (ChatColor.stripColor(event.getInventory().getName()).startsWith("Editing")) {
            event.setCancelled(true);
            if (event.getCurrentItem() != null && event.getCurrentItem().getType() != Material.AIR && event.getCurrentItem().hasItemMeta()) {
                if (ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()).equals("Add owner")) {
                    event.getWhoClicked().closeInventory();
                    ConversationFactory factory = new ConversationFactory(Progression.getPlugin()).withModality(true).withFirstPrompt(new AddOwnerPromt()).withEscapeSequence("quit").withTimeout(10).thatExcludesNonPlayersWithMessage("Only players can use this!");
                    if (event.getWhoClicked() != null) {
                        factory.buildConversation((Conversable) event.getWhoClicked()).begin();
                        if (event.getInventory() != null)
                            event.getWhoClicked().closeInventory();
                    }
                } else if (ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()).equals("Add builder")) {
                    event.getWhoClicked().closeInventory();
                    ConversationFactory factory = new ConversationFactory(Progression.getPlugin()).withModality(true).withFirstPrompt(new AddBuilderPromt()).withEscapeSequence("quit").withTimeout(10).thatExcludesNonPlayersWithMessage("Only players can use this!");
                    if (event.getWhoClicked() != null) {
                        factory.buildConversation((Conversable) event.getWhoClicked()).begin();
                        if (event.getInventory() != null)
                            event.getWhoClicked().closeInventory();
                    }
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

    @EventHandler
    public void onPlayerRightClick(PlayerInteractEvent event) {
        if (!event.getAction().equals(Action.PHYSICAL) && event.getItem() != null && !event.getItem().getType().equals(Material.AIR)) {
            if (event.getItem().getType().equals(Material.DIAMOND_PICKAXE)) {
                progressionMenu(event.getPlayer());
            }
        }
    }

    private void worldEditor(Player player, World world) {
        Inventory inventory = Bukkit.createInventory(null, 27, ChatColor.GOLD + "Editing " + ChatColor.GREEN + world.getName());
        inventory.setItem(0, ItemUtils.createItem(Material.LEASH, 1, ChatColor.GOLD + "Add owner"));
        inventory.setItem(1, ItemUtils.createItem(Material.DIRT, 1, ChatColor.GOLD + "Add builder"));
        inventory.setItem(2, ItemUtils.createItem(Material.GLASS, 1, ChatColor.GOLD + "Add viewer"));
        player.openInventory(inventory);
    }


    private void worldsMenu(Player player, String name) {
        if (WorldUtil.getWorlds(player.getUniqueId().toString()).size() > 0) {
            Inventory inventory = Bukkit.createInventory(null, 27, ChatColor.AQUA + name);
            List<World> worlds = WorldUtil.getWorlds(player.getUniqueId().toString());
            int slot = 0;
            for (int i = 0; worlds.size() > i; i++) {
                if (worlds.get(i) != null) {
                    if (WorldUtil.isOwner(player, worlds.get(i))) {
                        inventory.setItem(slot, ItemUtils.createItem(Material.WOOL, 1, (short) 5, ChatColor.AQUA + worlds.get(i).getName(), Arrays.asList(ChatColor.GREEN + "Owner")));
                        slot++;
                    }
                    else if (WorldUtil.isBuilder(player, worlds.get(i))) {
                        inventory.setItem(slot, ItemUtils.createItem(Material.WOOL, 1, (short) 4, ChatColor.AQUA + worlds.get(i).getName(), Arrays.asList(ChatColor.YELLOW + "Builder")));
                        slot++;
                    }
                    else if (WorldUtil.isViewer(player, worlds.get(i))) {
                        inventory.setItem(slot, ItemUtils.createItem(Material.WOOL, 1, (short) 14, ChatColor.AQUA + worlds.get(i).getName(), Arrays.asList(ChatColor.RED + "Viewer")));
                        slot++;
                    }
                }
            }
            player.openInventory(inventory);
        } else player.sendMessage(ChatColor.RED + "No worlds that you own were found");
    }

}
