package com.pluginjam;

import com.pluginjam.commands.*;
import com.pluginjam.core.WeightManager;
import com.pluginjam.dungeon.danger.DangerListener;
import com.pluginjam.dungeon.danger.DangerManager;
import com.pluginjam.dungeon.generator.world.DungeonWorld;
import com.pluginjam.listener.JoinListener;
import com.pluginjam.util.rareplayermoveevent.RarePlayerMoveEventCaller;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.util.logging.Logger;

public final class PluginJam extends JavaPlugin {
    // |-------------------------------------------------------------------|
    // | A large potion of code in this plugin was written under immense
    // | time pressure, so feel free to refactor it.
    // |-------------------------------------------------------------------|
    public static PluginJam instance;
    Logger logger = super.getLogger();
    private WorldEditPlugin worldEditPlugin;
    private DungeonWorld dungeonWorld;
    @Override
    public void onEnable() {
        //Plugin/Object instances
        this.worldEditPlugin = (WorldEditPlugin) Bukkit.getPluginManager().getPlugin("WorldEdit");
        instance = this;

        //Check for dependencies //TODO: add holographic displays dep.
        if (!Bukkit.getPluginManager().isPluginEnabled("NoteBlockAPI") || !Bukkit.getPluginManager().isPluginEnabled("FastAsyncWorldEdit")) {
            getLogger().severe("-----------------------------------------------------");
            getLogger().severe("      *** NoteBlockAPI/FWE/HolographicDisplays ***");
            getLogger().severe("          *** are not installed/enabled ***");
            getLogger().severe("------------------------------------------------------");
            Bukkit.getServer().shutdown(); //rip
        }

        //Info banner
        getLogger().info("X----------------------------------------X");
        getLogger().info("   * Dungeons by HiWhatName && Earomc *");
        getLogger().info("X----------------------------------------X");
        //Bukkit.loadServerIcon()

        // Dungeon world generation - Creates a void world called "dungeon"
        this.dungeonWorld = new DungeonWorld("dungeon", this);
        DangerManager dangerManager = new DangerManager(dungeonWorld.getDungeonWorld().getDifficulty());

        // Unload vanilla worlds for wayyyy better performance
        Bukkit.unloadWorld("world", true); // Bukkit disallows unloading the world, oh well
        Bukkit.unloadWorld("world_nether", true);
        Bukkit.unloadWorld("world_the_end", true);
        System.gc(); // Clean up garbage left from the other dimensions
        logger.info(LoadedWorldsCommand.getLoadedWorlds().replace("\n", " ").replace("$e", ""));

        //Command registration.
        this.getCommand("gendungeon").setExecutor(new GenDungeonCommand());
        this.getCommand("radio").setExecutor(new RadioCommand());
        this.getCommand("rickroll").setExecutor(new RickRollCommand());
        this.getCommand("loadedworlds").setExecutor(new LoadedWorldsCommand());
        this.getCommand("summondungeonmob").setExecutor(new SummonDungeonMobCommand());

        //Events
        getServer().getPluginManager().registerEvents(new JoinListener(dungeonWorld), this);
        getServer().getPluginManager().registerEvents(new RadioCommand(), this);
        getServer().getPluginManager().registerEvents(new WeightManager(), this);
        //getServer().getPluginManager().registerEvents(new DangerListener(dangerManager), this); Moved to @link DungeonWorld.java to allow multiple instances of it

        RarePlayerMoveEventCaller rarePlayerMoveEventCaller = new RarePlayerMoveEventCaller(this, 5);

        //Music stuff
        RadioCommand radio = new RadioCommand();


    }

    private void copySchemsToFolder() {
    }

    @Override
    public void onDisable() {
        Bukkit.getWorld("dungeon").getWorldFolder().deleteOnExit(); // TODO: Does not work, so find an alternative
        logger.info("Goodbye ;)");
    }
    public WorldEditPlugin getWorldEditPlugin(){
        return this.worldEditPlugin;
    }
    public DungeonWorld getDungeonWorld(){
        return this.dungeonWorld;
    }

    public static PluginJam getInstance() {
        return instance;
    }
}
