package com.pluginjam.util;

import com.xxmicloxx.NoteBlockAPI.model.Playlist;
import com.xxmicloxx.NoteBlockAPI.model.Song;
import com.xxmicloxx.NoteBlockAPI.songplayer.RadioSongPlayer;
import com.xxmicloxx.NoteBlockAPI.utils.NBSDecoder;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.File;

public class Radio implements Listener {
    public void loadNBS(){
        //Todo: Iterate through songs dir
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();
        Song song = NBSDecoder.parse(new File(PluginJam.getInstance().getDataFolder() + "/nbs/harrypotter.nbs"));
        RadioSongPlayer radioSongPlayer = new RadioSongPlayer(song);
        Playlist playlist = new Playlist(song);
        radioSongPlayer.addPlayer(p);
        radioSongPlayer.setPlaying(true);

        //TODO: Change spawn location to start room, start nbs playback.
    }

}
