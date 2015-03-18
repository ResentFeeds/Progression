package me.Elliott_.Progression.world;

import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.List;

public class ProgressionWorld {

    private final String name;
    private final Player owner;
    private final File file;
    private final FileConfiguration config;
    private final World world;

    /**
     * @param name The name of the world
     * @param owner The owner of the world
     * @param file File location of the world
     * @param config World configuration file
     * @param world Bukkit world
     */

    public ProgressionWorld(String name, Player owner, File file, FileConfiguration config, World world) {
        this.name = name;
        this.owner = owner;
        this.file = file;
        this.config = config;
        this.world = world;
    }

    /**
     * @return Returns the name of the world
     */
    public String getName() {
        return this.name;
    }

    /**
     * @return Returns the owner of the world
     */
    public Player getOwner() {
        return this.owner;
    }

    /**
     * @return Returns the file that the world is located in
     */
    public File getFile() {
        return this.file;
    }

    /**
     * @return Returns the world configuration file
     */
    public FileConfiguration getConfig() {
        return this.config;
    }

    /**
     * @return Returns the Bukkit world
     */
    public World getWorld() {
        return this.world;
    }

    public File getConfigFile() {
        return new File(this.file + "config.yml");
    }







}
