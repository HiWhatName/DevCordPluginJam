package com.pluginjam.commands;

import com.pluginjam.PluginJam;
import com.xxmicloxx.NoteBlockAPI.model.RepeatMode;
import com.xxmicloxx.NoteBlockAPI.model.Song;
import com.xxmicloxx.NoteBlockAPI.songplayer.RadioSongPlayer;
import com.xxmicloxx.NoteBlockAPI.songplayer.SongPlayer;
import com.xxmicloxx.NoteBlockAPI.utils.NBSDecoder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class RickRollCommand implements CommandExecutor {
    Song RickRollNBS = NBSDecoder.parse(PluginJam.getInstance().getResource("nbs/music/NeverGonnaGiveYouUp.nbs"));
    RadioSongPlayer sp = new RadioSongPlayer(RickRollNBS);
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!(args.length > 0)) return false;
        Player target = Bukkit.getPlayer(args[0]);
        if (target != null) {
            if(sender.hasPermission("dungeon.rick")) {
                RadioCommand.removeFromRadio(target.getUniqueId());
                if(!(sp.getPlayerUUIDs().contains(target.getUniqueId()))) sp.addPlayer(target);
                sp.setRepeatMode(RepeatMode.NO);
                sp.setAutoDestroy(true);
                sp.setPlaying(true);

                if(sender instanceof Player p){ p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0f, 1.0f);}

                if(!(sender.equals(target))){
                    target.sendMessage(ChatColor.GREEN + "Say 'thanks' to: " + ChatColor.YELLOW + sender.getName());
                    sender.sendMessage(ChatColor.GREEN + "Started rick-rolling' " + ChatColor.GREEN + target.getDisplayName());
                }else{ sender.sendMessage(ChatColor.GREEN + "Well. u wanted it, didn't u? " + ChatColor.YELLOW + sender.getName()); }
            }
        } else {
            sender.sendMessage(ChatColor.RED + "Player '" + args[0] + "' does not exist!");
        }
        return true;
    }
    }
