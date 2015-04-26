package me.Elliott_.Progression.world;

import me.Elliott_.Progression.world.WorldConfiguration.WorldConfiguration;
import me.Elliott_.Progression.world.WorldConfiguration.WorldSetting;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.generator.ChunkGenerator;

import java.io.File;
import java.util.Random;

public class GenerateWorld {

    public static void generateWorld(Player player, String name) {
        File file = new File("worlds/" + player.getName() + "/" + name);
        WorldCreator wc = new WorldCreator(file.getPath()).generator(new NullChunkGenerator());
        World world = Bukkit.createWorld(wc);
        world.getBlockAt(0, 0, 0).setType(Material.STONE);
        world.setDifficulty(Difficulty.PEACEFUL);
        world.setPVP(false);
        world.setSpawnLocation(0, 1, 0);
        world.setGameRuleValue("doDaylightCycle", "false");
        WorldConfiguration.generateSettings(world, player);
    }


    public static void setWorldSpawn(World world, Location location) {
        world.setSpawnLocation(location.getBlockX(), location.getBlockY(), location.getBlockZ());
    }

}
