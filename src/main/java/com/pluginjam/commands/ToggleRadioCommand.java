package com.pluginjam.commands;

import com.pluginjam.util.Radio;
import com.xxmicloxx.NoteBlockAPI.songplayer.RadioSongPlayer;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class ToggleRadioCommand implements CommandExecutor {

    RadioSongPlayer rsp = Radio.getBackGroundMusicPlayer();
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
       if(sender instanceof Player p){
           if(rsp.getPlayerUUIDs().contains(p.getUniqueId())){
               rsp.removePlayer(p.getUniqueId());
               p.sendMessage(ChatColor.RED + "Toggled the radio off!");
           }else{
               rsp.addPlayer(p.getUniqueId());
               p.sendMessage(ChatColor.GREEN + "Toggled the radio on!");
           }
           System.out.println(rsp.getPlayerUUIDs());
       } else {
           sender.sendMessage("&cPlayer only cmd, sry!");
       }
        return false;
    }
}
