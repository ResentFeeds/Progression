package me.Elliott_.Progression.world.WorldConfiguration;

import me.Elliott_.Progression.Progression;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;

public class WorldConfiguration {

    public static FileConfiguration getWorldConfiguration(File file) {
        return YamlConfiguration.loadConfiguration(file);
    }


    public static void generateSettings(World world, Player owner) {
        FileConfiguration fileConfiguration = Progression.getPlugin().getConfig();
        fileConfiguration.set("worlds." + world.getName() + ".owners", owner.getUniqueId());
        fileConfiguration.set("worlds." + world.getName() + ".builders", null);
        fileConfiguration.set("worlds." + world.getName() + ".viewers", null);
        fileConfiguration.set("worlds." + world.getName() + ".state", WorldState.PRIVATE.toString().toLowerCase());
        fileConfiguration.set("worlds." + world.getName() + ".settings.animals", false);
        fileConfiguration.set("worlds." + world.getName() + ".settings.monsters", false);
        fileConfiguration.set("worlds." + world.getName() + ".settings.fire", false);
        fileConfiguration.set("worlds." + world.getName() + ".settings.physics", false);
        fileConfiguration.set("worlds." + world.getName() + ".settings.world", false);
        fileConfiguration.set("worlds." + world.getName() + ".settings.tnt", false);
        Progression.getPlugin().saveConfig();
    }


    public static void modifySetting(World world, WorldSetting setting, boolean value) {
        Progression.getPlugin().getConfig().set("worlds." + world.getName() + ".settings." + setting.toString().toLowerCase(), value);
        Progression.getPlugin().saveConfig();
    }

    public static void addOwner(World world, Player player) {
        Progression.getPlugin().getConfig().getStringList("worlds." + world.getName() + ".builders").add(player.getUniqueId().toString());
        Progression.getPlugin().saveConfig();
    }

    public static void addBuilder(World world, Player player) {
        Progression.getPlugin().getConfig().getStringList("worlds." + world.getName() + ".builders").add(player.getUniqueId().toString());
        Progression.getPlugin().saveConfig();
    }

    public static void addViewer(World world, Player player) {
        Progression.getPlugin().getConfig().getStringList("worlds." + world.getName() + ".viewers").add(player.getUniqueId().toString());
        Progression.getPlugin().saveConfig();
    }



}
