package com.pluginjam.util;

import com.pluginjam.PluginJam;
import com.xxmicloxx.NoteBlockAPI.model.Playlist;
import com.xxmicloxx.NoteBlockAPI.model.RepeatMode;
import com.xxmicloxx.NoteBlockAPI.model.Song;
import com.xxmicloxx.NoteBlockAPI.songplayer.RadioSongPlayer;
import com.xxmicloxx.NoteBlockAPI.utils.NBSDecoder;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.checkerframework.checker.units.qual.N;

public class Radio implements Listener {

    private Playlist backgroundMusic;
    public static RadioSongPlayer radioSongPlayer;

    public Radio(){
        //Todo: Iterate through songs dir
        Song ZeldaTheme = NBSDecoder.parse(PluginJam.getInstance().getResource("nbs/music/ZeldaTheme.nbs"));
        Song CatCradle = NBSDecoder.parse(PluginJam.getInstance().getResource("nbs/music/CatCradle.nbs"));
        Song NoTime = NBSDecoder.parse(PluginJam.getInstance().getResource("nbs/music/igotnotime.nbs"));

        backgroundMusic = new Playlist(ZeldaTheme, CatCradle, NoTime);
        radioSongPlayer = new RadioSongPlayer(backgroundMusic);
        radioSongPlayer.setVolume((byte) 35);
        radioSongPlayer.setRepeatMode(RepeatMode.ALL);
    }

    @EventHandler
    void onPlayerJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();

        radioSongPlayer.addPlayer(p.getUniqueId());
        radioSongPlayer.setPlaying(true);

        //TODO: Change spawn location to start room, start nbs playback.
    }

    public static RadioSongPlayer getBackGroundMusicPlayer(){
        return radioSongPlayer;
    }

}
