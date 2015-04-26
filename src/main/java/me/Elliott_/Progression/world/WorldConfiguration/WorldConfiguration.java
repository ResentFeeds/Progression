package me.Elliott_.Progression.world.WorldConfiguration;

import me.Elliott_.Progression.Progression;
import me.Elliott_.Progression.util.WorldUtil;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class WorldConfiguration {

    public static FileConfiguration getWorldConfiguration(File file) {
        return YamlConfiguration.loadConfiguration(file);
    }


    public static void generateSettings(World world, Player owner) {
        FileConfiguration fileConfiguration = Progression.getPlugin().getConfig();
        fileConfiguration.set("worlds." + world.getName() + ".owners", Arrays.asList(owner.getUniqueId().toString()));
        fileConfiguration.set("worlds." + world.getName() + ".builders", Arrays.asList(null));
        fileConfiguration.set("worlds." + world.getName() + ".viewers", Arrays.asList(null));
        fileConfiguration.set("worlds." + world.getName() + ".state", WorldState.PRIVATE.toString().toLowerCase());
        fileConfiguration.set("worlds." + world.getName() + ".settings.animals", false);
        fileConfiguration.set("worlds." + world.getName() + ".settings.monsters", false);
        fileConfiguration.set("worlds." + world.getName() + ".settings.fire", false);
        fileConfiguration.set("worlds." + world.getName() + ".settings.physics", false);
        fileConfiguration.set("worlds." + world.getName() + ".settings.world", false);
        fileConfiguration.set("worlds." + world.getName() + ".settings.tnt", false);
        Progression.getPlugin().saveConfig();
        owner.sendMessage(ChatColor.GREEN + "World created use the menu to teleport to the world or type " + ChatColor.AQUA + "/world " + world.getName());
    }


    public static void modifySetting(World world, WorldSetting setting, boolean value) {
        Progression.getPlugin().getConfig().set("worlds." + world.getName() + ".settings." + setting.toString().toLowerCase(), value);
        Progression.getPlugin().saveConfig();
    }

    public static void addOwner(World world, Player player) {
        Progression.getPlugin().getConfig().getStringList("worlds." + world.getName() + ".owners").add(player.getUniqueId().toString());
        if (WorldUtil.isBuilder(player, world))
            removeBuilder(world, player);
        if(WorldUtil.isViewer(player, world))
            removeViewer(world, player);
        Progression.getPlugin().saveConfig();
    }

    public static void addBuilder(World world, Player player) {
        Progression.getPlugin().getConfig().getStringList("worlds." + world.getName() + ".builders").add(player.getUniqueId().toString());
        if (WorldUtil.isOwner(player, world))
            removeOwner(world, player);
        if(WorldUtil.isViewer(player, world))
            removeViewer(world, player);
        Progression.getPlugin().saveConfig();
    }

    public static void addViewer(World world, Player player) {
        Progression.getPlugin().getConfig().getStringList("worlds." + world.getName() + ".viewers").add(player.getUniqueId().toString());
        if (WorldUtil.isBuilder(player, world))
            removeBuilder(world, player);
        if(WorldUtil.isOwner(player, world))
            removeOwner(world, player);
        Progression.getPlugin().saveConfig();
    }

    public static void removeOwner(World world, Player player) {
        Progression.getPlugin().getConfig().getStringList("worlds." + world.getName() + ".owners").remove(player.getUniqueId().toString());
        Progression.getPlugin().saveConfig();
    }
    public static void removeBuilder(World world, Player player) {
        Progression.getPlugin().getConfig().getStringList("worlds." + world.getName() + ".builders").remove(player.getUniqueId().toString());
        Progression.getPlugin().saveConfig();
    }
    public static void removeViewer(World world, Player player) {
        Progression.getPlugin().getConfig().getStringList("worlds." + world.getName() + ".viewers").remove(player.getUniqueId().toString());
        Progression.getPlugin().saveConfig();
    }




}
