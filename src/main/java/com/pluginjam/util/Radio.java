package com.pluginjam.util;

import com.pluginjam.PluginJam;
import com.xxmicloxx.NoteBlockAPI.event.SongNextEvent;
import com.xxmicloxx.NoteBlockAPI.model.Playlist;
import com.xxmicloxx.NoteBlockAPI.model.RepeatMode;
import com.xxmicloxx.NoteBlockAPI.model.Song;
import com.xxmicloxx.NoteBlockAPI.songplayer.RadioSongPlayer;
import com.xxmicloxx.NoteBlockAPI.songplayer.SongPlayer;
import com.xxmicloxx.NoteBlockAPI.utils.NBSDecoder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.checkerframework.checker.units.qual.N;

import java.util.UUID;

public class Radio implements Listener {

    private Playlist backgroundMusic;
    public static RadioSongPlayer radioSongPlayer;

    public Radio(){
        //Todo: Iterate through songs dir
        Song ZeldaTheme = NBSDecoder.parse(PluginJam.getInstance().getResource("nbs/music/ZeldaTheme.nbs"));
        Song ScobyDoo = NBSDecoder.parse(PluginJam.getInstance().getResource("nbs/music/scoobydoo.nbs"));
        Song CatCradle = NBSDecoder.parse(PluginJam.getInstance().getResource("nbs/music/CatCradle.nbs"));
        Song TetrisB = NBSDecoder.parse(PluginJam.getInstance().getResource("nbs/music/TetrisB.nbs"));
        Song NoTime = NBSDecoder.parse(PluginJam.getInstance().getResource("nbs/music/igotnotime.nbs"));


        backgroundMusic = new Playlist(ScobyDoo, ZeldaTheme, TetrisB,CatCradle, NoTime);
        radioSongPlayer = new RadioSongPlayer(backgroundMusic);
        radioSongPlayer.setVolume((byte) 20);
        radioSongPlayer.setRepeatMode(RepeatMode.ALL);
        radioSongPlayer.setPlaying(true);
    }

    @EventHandler
    void onPlayerJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();
        radioSongPlayer.addPlayer(p.getUniqueId());

        //TODO: Change spawn location to start room, start nbs playback.
    }
    @EventHandler
    public void onSongNext(SongNextEvent e){
        SongPlayer sp = e.getSongPlayer(); //Gives you SongPlayer
        Song nextSong = sp.getSong(); //Gives you player Song
        for( UUID uuid : sp.getPlayerUUIDs()){
            Bukkit.getPlayer(uuid).sendMessage(ChatColor.YELLOW + "Next up:" + ChatColor.GREEN + nextSong.getTitle());
        }
    }

    public static RadioSongPlayer getBackGroundMusicPlayer(){
        return radioSongPlayer;
    }

}
