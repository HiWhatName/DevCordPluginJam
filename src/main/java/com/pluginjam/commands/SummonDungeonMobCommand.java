package com.pluginjam.commands;

import com.pluginjam.PluginJam;
import com.pluginjam.mob.MobSpawner;
import com.pluginjam.mob.SpawnMeta;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SummonDungeonMobCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player p) {
            if(p.hasPermission("dungeon.summon")){
                p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f);
                MobSpawner mobSpawner = new MobSpawner(PluginJam.getInstance().getDungeonWorld().getDangerManager());
                mobSpawner.spawnRandomDungeonMob(new SpawnMeta(p.getLocation()));
                p.sendMessage(ChatColor.GREEN + "Summond random dungeonmob");
            }else{
                p.sendMessage(ChatColor.RED + "No permission!");
            }
        }else{
            sender.sendMessage(ChatColor.RED + "Player only command, sry!");
        }
        return true;
    }
}
