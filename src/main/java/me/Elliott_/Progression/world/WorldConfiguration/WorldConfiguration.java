package me.Elliott_.Progression.world.WorldConfiguration;

import me.Elliott_.Progression.world.ProgressionWorld;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class WorldConfiguration {

    public static FileConfiguration getWorldConfiguration(File file) {
        return YamlConfiguration.loadConfiguration(file);
    }


    public static void generateSettings(ProgressionWorld world) {
        if (world.getFile() != null) {
            File config = world.getConfigFile();
            FileConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(config);
            fileConfiguration.addDefault("name", world);
            fileConfiguration.addDefault("owner", world.getOwner().getUniqueId());
            fileConfiguration.addDefault("builders", null);
            fileConfiguration.addDefault("viewers", null);
            fileConfiguration.addDefault("state", WorldState.PRIVATE.toString().toLowerCase());
            fileConfiguration.addDefault("settings.animals", "false");
            fileConfiguration.addDefault("settings.monsters", "false");
            fileConfiguration.addDefault("settings.fire", "false");
            fileConfiguration.addDefault("settings.physics", "true");
            fileConfiguration.addDefault("settings.world", "true");
            fileConfiguration.addDefault("settings.tnt", "false");
            saveSetting(world);
        }
    }

    public static void saveSetting(ProgressionWorld world) {
        try {
            world.getConfig().save(world.getConfigFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void modifySetting(ProgressionWorld world, WorldSetting setting, boolean value) {
        world.getConfig().set("settings." + setting.toString().toLowerCase(), value);
        saveSetting(world);
    }

    public static void addBuilder(ProgressionWorld world, Player player) {
        world.getConfig().getStringList("builders").add(player.getUniqueId().toString());
        saveSetting(world);
    }

    public static void addViewer(ProgressionWorld world, Player player) {
        world.getConfig().getStringList("viewers").add(player.getUniqueId().toString());
        saveSetting(world);
    }



}
