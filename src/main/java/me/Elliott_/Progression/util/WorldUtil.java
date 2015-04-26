package me.Elliott_.Progression.util;

import me.Elliott_.Progression.Progression;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class WorldUtil {

    public static List<String> getOwners(World world) {
        List<String> owners = new ArrayList<>();
        if (world != null) {
            owners = Progression.getPlugin().getConfig().getStringList("worlds." + world.getName() + ".owners");
        }
        return owners;
    }

    public static List<String> getBuilders(World world) {
        List<String> builders = new ArrayList<>();
        if (world != null)
            builders = Progression.getPlugin().getConfig().getStringList("worlds." + world.getName() + ".builders");
        return builders;
    }

    public static List<String> getViewers(World world) {
        List<String> viewers = new ArrayList<>();
        if (world != null)
            viewers = Progression.getPlugin().getConfig().getStringList("worlds." + world.getName() + ".viewers");
        return viewers;
    }

    public static boolean isOwner(Player player, World world) {
        boolean owner = false;
        if (getOwners(world) != null)
            owner = getOwners(world).contains(player.getUniqueId().toString());
        return owner;
    }

    public static boolean isBuilder(Player player, World world) {
        boolean builder = false;
        if (getBuilders(world) != null)
            builder = getBuilders(world).contains(player.getUniqueId().toString());
        return builder;
    }

    public static boolean isViewer(Player player, World world) {
        boolean viewer = false;
            viewer = getViewers(world).contains(player.getUniqueId().toString());
        return viewer;
    }

    public static List<World> getWorlds(String uuid) {
        List<World> worlds = new ArrayList<>();
        if (Progression.getPlugin().getConfig().getConfigurationSection("worlds") != null) {
            for (String key : Progression.getPlugin().getConfig().getConfigurationSection("worlds").getKeys(false)) {

                if (Progression.getPlugin().getConfig().getString("worlds." + key + ".owners") != null) {
                    if (Progression.getPlugin().getConfig().getString("worlds." + key + ".owners").contains(uuid))
                        worlds.add(Progression.getPlugin().getServer().getWorld(key));
                }
                if (Progression.getPlugin().getConfig().getString("worlds." + key + ".builders") != null) {
                    if (Progression.getPlugin().getConfig().getString("worlds." + key + ".builders").contains(uuid))
                        worlds.add(Progression.getPlugin().getServer().getWorld(key));
                }
                if (Progression.getPlugin().getConfig().getString("worlds." + key + ".viewers") != null) {
                    if (Progression.getPlugin().getConfig().getString("worlds." + key + ".viewers").contains(uuid))
                        worlds.add(Progression.getPlugin().getServer().getWorld(key));
                }
            }
        }
        return worlds;
    }

}
