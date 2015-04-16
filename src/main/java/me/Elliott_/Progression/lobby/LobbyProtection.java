package me.Elliott_.Progression.lobby;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerPickupExperienceEvent;

public class LobbyProtection implements Listener {


    @EventHandler
    public void onBlockChange(BlockPlaceEvent event) {
        if (Lobby.getLobby() != null) {
            if (event.getPlayer().getWorld().equals(Lobby.getLobby()))
                event.setCancelled(true);
        }
    }

    @EventHandler
    public void onBlockPlace(BlockBreakEvent event) {
        if (Lobby.getLobby() != null) {
            if (event.getPlayer().getWorld().equals(Lobby.getLobby()))
                event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (Lobby.getLobby() != null) {
            if (event.getPlayer().getWorld().equals(Lobby.getLobby()))
                event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerPickupExperience(PlayerPickupExperienceEvent event) {
        if (Lobby.getLobby() != null) {
            if (event.getPlayer().getWorld().equals(Lobby.getLobby()))
                event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent event) {
        if (Lobby.getLobby() != null) {
            if (event.getPlayer().getWorld().equals(Lobby.getLobby()))
                event.getItemDrop().remove();
        }
    }

}
