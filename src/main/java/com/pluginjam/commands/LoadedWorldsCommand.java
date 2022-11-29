package com.pluginjam.commands;

import com.pluginjam.PluginJam;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class LoadedWorldsCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender.hasPermission("dungeon.loaded"))
        sender.sendMessage(getLoadedWorlds());
        return true;
    }
    public static String getLoadedWorlds(){
        return ChatColor.YELLOW + "List of (all) worlds with their load status: \n"
                + ChatColor.YELLOW + " - world: " + ChatColor.GREEN + (Bukkit.getWorld("world") != null) + "\n"
                + ChatColor.YELLOW + " - the_nether: " + ChatColor.GREEN + (Bukkit.getWorld("the_nether") != null) + "\n"
                + ChatColor.YELLOW + " - the_end: " + ChatColor.GREEN + (Bukkit.getWorld("the_end") != null) + "\n"
                + ChatColor.YELLOW + " - dungeon: " + ChatColor.GREEN + (Bukkit.getWorld("dungeon") != null);
    }
}
