package com.pluginjam.util;

import com.pluginjam.PluginJam;
import com.xxmicloxx.NoteBlockAPI.model.Playlist;
import com.xxmicloxx.NoteBlockAPI.model.Song;
import com.xxmicloxx.NoteBlockAPI.songplayer.RadioSongPlayer;
import com.xxmicloxx.NoteBlockAPI.utils.NBSDecoder;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class Radio implements Listener {

    private Playlist backgroundMusic;
    private RadioSongPlayer radioSongPlayer;

    public void Radio(){
        //Todo: Iterate through songs dir
        Song song = NBSDecoder.parse(PluginJam.getInstance().getResource("nbs/music/harrypotter.nbs"));

        this.backgroundMusic = new Playlist(song);
        this.radioSongPlayer = new RadioSongPlayer(backgroundMusic);
    }

    @EventHandler
    void onPlayerJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();

        Song song = NBSDecoder.parse(PluginJam.getInstance().getResource("nbs/music/harrypotter.nbs"));

        this.backgroundMusic = new Playlist(song);
        this.radioSongPlayer = new RadioSongPlayer(backgroundMusic);

        radioSongPlayer.addPlayer(p);
        //radioSongPlayer.setPlaying(true);

        //TODO: Change spawn location to start room, start nbs playback.
    }

}
