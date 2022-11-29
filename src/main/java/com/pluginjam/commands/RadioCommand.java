package com.pluginjam.commands;

import com.pluginjam.PluginJam;
import com.xxmicloxx.NoteBlockAPI.event.SongNextEvent;
import com.xxmicloxx.NoteBlockAPI.event.SongStoppedEvent;
import com.xxmicloxx.NoteBlockAPI.model.Playlist;
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
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class RadioCommand implements Listener, CommandExecutor, TabCompleter {
    private static final String[] COMMANDS = { "toggle", "volume"};
    private Playlist backgroundMusic;
    public static RadioSongPlayer radioSongPlayer;

    public RadioCommand(){
        //Todo: Iterate through songs dir
        Song ZeldaTheme = NBSDecoder.parse(PluginJam.getInstance().getResource("nbs/music/ZeldaTheme.nbs"));
        Song ScobyDoo = NBSDecoder.parse(PluginJam.getInstance().getResource("nbs/music/scoobydoo.nbs"));
        Song CatCradle = NBSDecoder.parse(PluginJam.getInstance().getResource("nbs/music/CatCradle.nbs"));
        Song TetrisB = NBSDecoder.parse(PluginJam.getInstance().getResource("nbs/music/TetrisB.nbs"));
        Song NoTime = NBSDecoder.parse(PluginJam.getInstance().getResource("nbs/music/igotnotime.nbs"));

        backgroundMusic = new Playlist(ScobyDoo, ZeldaTheme, TetrisB,CatCradle, NoTime);
        radioSongPlayer = new RadioSongPlayer(backgroundMusic);
        radioSongPlayer.setVolume((byte) Math.round(127 * 0.35f)); // 0.35f = 35%
        radioSongPlayer.setRepeatMode(RepeatMode.ALL);
        radioSongPlayer.setPlaying(true);
    }

    //TODO: make it on a per-player basis instead 1 general audio steam. Or some other way.

    @EventHandler
    void onPlayerJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();
        if(!(radioSongPlayer.getPlayerUUIDs().contains(p.getUniqueId()))) { //TODO: Set playback to where p left off
            radioSongPlayer.addPlayer(p.getUniqueId());
        }
    }
    @EventHandler
    public void onSongNext(SongNextEvent e){
        SongPlayer sp = e.getSongPlayer();
        Song nextSong = sp.getSong();
        for( UUID uuid : sp.getPlayerUUIDs()){
            if(uuid == null) continue;
            if(nextSong.getTitle() == null || nextSong.getTitle() == "") continue;
            if(Bukkit.getPlayer(uuid) == null) continue;
            Bukkit.getPlayer(uuid).sendMessage(ChatColor.YELLOW + "Next up: " + ChatColor.GREEN + nextSong.getTitle());
        }
    }

    @EventHandler
    void onSongStop(SongStoppedEvent e){
        SongPlayer sp = e.getSongPlayer();
        for( UUID uuid : sp.getPlayerUUIDs()){
            if(uuid == null) continue;
            Bukkit.getPlayer(uuid).sendMessage(ChatColor.RED + "Stopped the radio");
        }
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!(args.length > 0)) return false;
        if(sender instanceof Player p) {
            if (args[0].equals("toggle")) {
                    if (radioSongPlayer.getPlayerUUIDs().contains(p.getUniqueId())) {
                        removeFromRadio(p.getUniqueId());
                        //radioSongPlayer.setPlaying(false);
                        p.sendMessage(ChatColor.RED + "Toggled the radio off!");
                    } else {
                        radioSongPlayer.addPlayer(p.getUniqueId());
                        //radioSongPlayer.setPlaying(true);
                        p.sendMessage(ChatColor.GREEN + "Toggled the radio on!");
                    }
                        p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0f, 1.0f);
                    } else if (args[0].equals("volume")) {
                        if(args.length == 2){
                            float volume;
                            if (Integer.parseInt(args[1]) >= 100){
                                volume = 1.0f;
                            }else{
                                volume = Float.parseFloat(args[1]) / 100;
                            }

                            radioSongPlayer.setVolume((byte) Math.round(127 * volume));
                            p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0f, 1.0f);
                            p.sendMessage(ChatColor.GREEN + "Changed volume to: " + Math.round(volume * 100) + ChatColor.YELLOW + "%");
                            return true;
                        }else{
                            p.sendMessage(ChatColor.RED + "Missing volume argument!");
                            p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_NO, 1.0f, 1.0f);
                            return true;
                        }
            }
        } else {
            sender.sendMessage("&cPlayer only cmd, sry!");
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        final List<String> completions = new ArrayList<String>(Arrays.asList(COMMANDS));
        Collections.sort(completions);
        return completions;
    }

    public static RadioSongPlayer getBackGroundMusicPlayer(){
        return radioSongPlayer;
    }
    public static void removeFromRadio(UUID uuid){
        if (radioSongPlayer.getPlayerUUIDs().contains(uuid)){
            radioSongPlayer.removePlayer(uuid);
        }
    }
    public static void addToRadio(UUID uuid){
        if (!(radioSongPlayer.getPlayerUUIDs().contains(uuid))){
            radioSongPlayer.addPlayer(uuid);
        }
    }
}
