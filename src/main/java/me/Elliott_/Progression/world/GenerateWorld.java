package me.Elliott_.Progression.world;

import me.Elliott_.Progression.world.WorldConfiguration.WorldConfiguration;
import org.bukkit.*;
import org.bukkit.entity.Player;

import java.io.File;

public class GenerateWorld {

    public static void generateWorld(Player player, String name) {
        File file = new File("worlds/" + player.getName() + "/" + name);
        World world = new WorldCreator(file.getPath()).generator(new NullChunkGenerator()).createWorld();
        world.getBlockAt(0, 0, 0).setType(Material.STONE);
        world.setDifficulty(Difficulty.PEACEFUL);
        world.setPVP(false);
        world.setSpawnLocation(0, 1, 0);
        world.setGameRuleValue("doDaylightCycle", "false");
        player.sendMessage(ChatColor.GREEN + "World created use the menu to teleport to the world or type " + ChatColor.AQUA + "/world " + name);
    }


    public static void setWorldSpawn(World world, Location location) {
        world.setSpawnLocation(location.getBlockX(), location.getBlockY(), location.getBlockZ());
    }

}
