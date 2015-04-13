package me.Elliott_.Progression.util;

import me.Elliott_.Progression.Progression;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class WorldUtil {

    public static List<String> getOwners(World world) {
        return Progression.getPlugin().getConfig().getStringList("worlds." + world.getName() + ".owners");
    }

    public static List<String> getBuilders(World world) {
        return Progression.getPlugin().getConfig().getStringList("worlds." + world.getName() + ".builders");
    }

    public static List<String> getViewers(World world) {
        return Progression.getPlugin().getConfig().getStringList("worlds." + world.getName() + ".viewers");
    }

    public static boolean isOwner(Player player, World world) {
        return getOwners(world).contains(player.getName());
    }

    public static boolean isBuilder(Player player, World world) {
        return getBuilders(world).contains(player.getName());
    }

    public static boolean isViewer(Player player, World world) {
        return getViewers(world).contains(player.getName());
    }

    public static List<World> getWorlds(Player player) {
        List<World> worlds = new ArrayList<>();
        for (String key : Progression.getPlugin().getConfig().getConfigurationSection("worlds").getKeys(false)) {
            if (Progression.getPlugin().getConfig().getString("worlds." + key + "owners").contains(player.getName()))
                worlds.add(Progression.getPlugin().getServer().getWorld(key));
            if (Progression.getPlugin().getConfig().getString("worlds." + key + "builders").contains(player.getName()))
                worlds.add(Progression.getPlugin().getServer().getWorld(key));
            if (Progression.getPlugin().getConfig().getString("worlds." + key + "viewers").contains(player.getName()))
                worlds.add(Progression.getPlugin().getServer().getWorld(key));

        }
        return worlds;
    }

}
