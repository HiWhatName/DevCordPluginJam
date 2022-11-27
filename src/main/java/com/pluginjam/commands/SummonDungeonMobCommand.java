package com.pluginjam.commands;

import com.pluginjam.mob.SpawnMeta;
import com.pluginjam.mob.mobs.CreeperMob;
import com.pluginjam.mob.mobs.SpiderMob;
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
        //TODO: add
        if(sender instanceof Player p) {
            if(p.hasPermission("dungeon.summon")){
                if(args.length >= 1) {
                    if(args[0] != null) {
                        p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f);
                        p.sendMessage(ChatColor.GREEN + "Summond a dungeonmob level: " + args[0]);
                        SpiderMob spiderMob = new SpiderMob(Integer.parseInt(args[0]));
                        spiderMob.spawn(new SpawnMeta(p.getLocation()));
                    }else{
                        return false;
                    }
                }else{
                    p.sendMessage(ChatColor.YELLOW + "Missing level arg!");
                }
            }else{
                p.sendMessage(ChatColor.RED + "No permission!");
            }
        }else{
            sender.sendMessage(ChatColor.RED + "Player only command, sry!");
        }
        return true;
    }
}
