package com.pluginjam;

import com.pluginjam.commands.GenDungeonCommand;
import com.pluginjam.listener.BreakListener;
import com.pluginjam.listener.JoinListener;
import com.pluginjam.util.Radio;
import com.sk89q.worldedit.regions.Region;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.logging.Logger;

public final class PluginJam extends JavaPlugin {
    public static PluginJam instance;
    Logger logger = super.getLogger();
    boolean NoteBlockAPI = true;

    @Override
    public void onEnable() {
        instance = this;

        //Command registration.
        this.getCommand("gendungeon").setExecutor(new GenDungeonCommand());

        //Event registration
        getServer().getPluginManager().registerEvents( new JoinListener(), this);
        getServer().getPluginManager().registerEvents( new BreakListener(), this);
        getServer().getPluginManager().registerEvents( new Radio(), this);

        if (!Bukkit.getPluginManager().isPluginEnabled("NoteBlockAPI")){
            getLogger().severe("-----------------------------------------------------");
            getLogger().severe("*** NoteBlockAPI is not installed or not enabled. ***");
            getLogger().severe("------------------------------------------------------");
            NoteBlockAPI = false;
        }

        //Radio
        Radio radio= new Radio();
        radio.loadNBS();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    public static PluginJam getInstance() {
        return instance;
    }
}
