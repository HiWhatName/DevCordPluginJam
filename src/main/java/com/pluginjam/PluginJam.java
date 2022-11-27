package com.pluginjam;

import com.pluginjam.commands.GenDungeonCommand;
import com.pluginjam.commands.LoadedWorldsCommand;
import com.pluginjam.commands.RickRollCommand;
import com.pluginjam.core.WeightManager;
import com.pluginjam.dungeon.generator.world.DungeonWorld;
import com.pluginjam.listener.JoinListener;
import com.pluginjam.commands.RadioCommand;
import com.pluginjam.util.rareplayermoveevent.RarePlayerMoveEventCaller;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.util.logging.Logger;

public final class PluginJam extends JavaPlugin {
    public static PluginJam instance;
    Logger logger = super.getLogger();

    @Override
    public void onEnable() {
        instance = this;
        //Check for dependencies
        if (!Bukkit.getPluginManager().isPluginEnabled("NoteBlockAPI") || !Bukkit.getPluginManager().isPluginEnabled("FastAsyncWorldEdit")) {
            getLogger().severe("-----------------------------------------------------");
            getLogger().severe("*** NoteBlockAPI/FWE are not installed/enabled    ***");
            getLogger().severe("------------------------------------------------------");
            Bukkit.getServer().shutdown(); //rip
        }
        //Info banner
        getLogger().info("X----------------------------------------X");
        getLogger().info("   * Dungeons by HiWhatName && Earomc *");
        getLogger().info("X----------------------------------------X");

        // Dungeon world generation - Creates a void world called "dungeon"
        DungeonWorld dungeonWorld = new DungeonWorld("dungeon");

        // Unload vanilla worlds for wayyyy better performance
        Bukkit.unloadWorld("world", true);
        Bukkit.unloadWorld("world_nether", true);
        Bukkit.unloadWorld("world_the_end", true);
        System.gc(); // Clean up garbage left from the other dimensions

        logger.info(LoadedWorldsCommand.getLoadedWorlds().replace("\n", " ").replace("$e", ""));


        //Command registration.
        this.getCommand("gendungeon").setExecutor(new GenDungeonCommand());
        this.getCommand("radio").setExecutor(new RadioCommand());
        this.getCommand("rickroll").setExecutor(new RickRollCommand());
        this.getCommand("loadedworlds").setExecutor(new LoadedWorldsCommand());

        //Events
        getServer().getPluginManager().registerEvents(new JoinListener(dungeonWorld), this);
        getServer().getPluginManager().registerEvents(new RadioCommand(), this);
        getServer().getPluginManager().registerEvents(new WeightManager(), this);

        RarePlayerMoveEventCaller rarePlayerMoveEventCaller = new RarePlayerMoveEventCaller(this, 5);

        //Music stuff
        RadioCommand radio = new RadioCommand();


    }

    private void copySchemsToFolder() {
        InputStream schem = getResource("schem");
        /*
        try (FileInputStream in = new FileInputStream(schem)) {
        }
        FileUtils.copyDirectory(schem, this.getDataFolder() + "/schem");
         */
    }

    @Override
    public void onDisable() {
        logger.info("Goodbye ;)");
    }

    public static PluginJam getInstance() {
        return instance;
    }
}
