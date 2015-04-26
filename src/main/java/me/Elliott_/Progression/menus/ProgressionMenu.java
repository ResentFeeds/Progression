package me.Elliott_.Progression.menus;

import me.Elliott_.Progression.Progression;
import me.Elliott_.Progression.menus.promts.CreateWorldPrompt;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.conversations.Conversable;
import org.bukkit.conversations.ConversationFactory;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class ProgressionMenu implements Listener {

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
                        Menus.worldsMenu((Player) event.getWhoClicked(), "World Menu");
                        break;
                    case ANVIL:
                        Menus.worldsMenu((Player) event.getWhoClicked(), "Click a world to edit");
                }
            } else event.getWhoClicked().closeInventory();
        }
    }


}
