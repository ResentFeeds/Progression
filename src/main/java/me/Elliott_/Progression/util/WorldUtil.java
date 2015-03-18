package me.Elliott_.Progression.util;

import me.Elliott_.Progression.world.ProgressionWorld;
import org.bukkit.entity.Player;

public class WorldUtil {

    public static boolean isBuilder(Player player, ProgressionWorld world) {
        return world.getConfig().getStringList("builders").contains(player.getUniqueId().toString());
    }

    public static boolean isViewver(Player player, ProgressionWorld world) {
        return world.getConfig().getStringList("viewers").contains(player.getUniqueId().toString());
    }

}
