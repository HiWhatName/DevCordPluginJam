package com.pluginjam;

import com.pluginjam.commands.GenDungeonCommand;
import com.pluginjam.commands.ToggleRadioCommand;
import com.pluginjam.core.WeightManager;
import com.pluginjam.listener.JoinListener;
import com.pluginjam.util.Radio;
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
        RarePlayerMoveEventCaller rarePlayerMoveEventCaller = new RarePlayerMoveEventCaller(this, 5);

        //copySchemsToFolder();

        //Radio stuff
        Radio radio = new Radio();

        //Command registration.
        this.getCommand("gendungeon").setExecutor(new GenDungeonCommand());
        this.getCommand("toggleradio").setExecutor(new ToggleRadioCommand());

        //Events
        getServer().getPluginManager().registerEvents(new JoinListener(), this);
        getServer().getPluginManager().registerEvents(new Radio(), this);
        getServer().getPluginManager().registerEvents(new WeightManager(), this);

        //Check for dependencies
        if (!Bukkit.getPluginManager().isPluginEnabled("NoteBlockAPI") || !Bukkit.getPluginManager().isPluginEnabled("FastAsyncWorldEdit")) {
            getLogger().severe("-----------------------------------------------------");
            getLogger().severe("*** NoteBlockAPI/FWE are not installed/enabled    ***");
            getLogger().severe("------------------------------------------------------");
            Bukkit.getServer().shutdown(); //rip
        }

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
