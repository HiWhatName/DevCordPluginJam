package com.pluginjam.commands;

import com.pluginjam.PluginJam;
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
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!(args.length > 0)) return false;
        Player target = Bukkit.getPlayer(args[0]);
        if (target != null) {
        Song RickRollNBS = NBSDecoder.parse(PluginJam.getInstance().getResource("nbs/music/NeverGonnaGiveYouUp.nbs"));
        RadioSongPlayer sp = new RadioSongPlayer(RickRollNBS);
        sp.addPlayer(target);
        sp.setPlaying(true);
        sp.setAutoDestroy(true);
        if(sender instanceof Player p){
            p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0f, 1.0f);
        }
        sender.sendMessage(ChatColor.GREEN + "Started rick-rolling' " + ChatColor.GREEN + target.getDisplayName());
        } else {
            sender.sendMessage(ChatColor.RED + "Player '" + args[0] + "' does not exist!");
        }
        return true;
    }
    }
