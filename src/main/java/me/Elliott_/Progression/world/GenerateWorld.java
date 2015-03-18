package me.Elliott_.Progression.world;

import me.Elliott_.Progression.world.WorldConfiguration.WorldConfiguration;
import org.bukkit.*;
import org.bukkit.entity.Player;

import java.io.File;

public class GenerateWorld {

    public static ProgressionWorld generateWorld(Player player, String name) {
        File file = new File("worlds/" + player.getName() + "/" + name);
        World world = new WorldCreator(file.getPath()).generator(new NullChunkGenerator()).createWorld();
        world.getBlockAt(0, 0, 0).setType(Material.STONE);
        world.setDifficulty(Difficulty.PEACEFUL);
        world.setPVP(false);
        world.setSpawnLocation(0, 0, 0);
        world.setGameRuleValue("doDaylightCycle", "false");
        return new ProgressionWorld(name, player, file, WorldConfiguration.getWorldConfiguration(file), world);
    }


    public static void setWorldSpawn(ProgressionWorld world, Location location) {
        world.getWorld().setSpawnLocation(location.getBlockX(), location.getBlockY(), location.getBlockZ());
    }



}
