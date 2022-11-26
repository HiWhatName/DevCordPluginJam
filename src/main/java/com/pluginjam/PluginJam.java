package com.pluginjam;

import com.pluginjam.commands.GenDungeonCommand;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

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

        if (!Bukkit.getPluginManager().isPluginEnabled("NoteBlockAPI")){
            getLogger().severe("-----------------------------------------------------");
            getLogger().severe("*** NoteBlockAPI is not installed or not enabled. ***");
            getLogger().severe("------------------------------------------------------");
            NoteBlockAPI = false;
            return;
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    public static PluginJam getInstance() {
        return instance;
    }
}
