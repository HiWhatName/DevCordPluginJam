package com.pluginjam.dungeon.generator;

import com.pluginjam.PluginJam;
import com.sk89q.worldedit.extent.clipboard.Clipboard;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormat;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormats;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public abstract class DungeonPiece {

    private final File schem;
    private Clipboard clipboard;
    protected ClipboardFormat format;

    public DungeonPiece(String name) {
        this(new File(PluginJam.getInstance().getDataFolder() + "/schem/" + name + ".schem"));
    }

    public DungeonPiece(File schem) {
        this.schem = schem;
        this.format = ClipboardFormats.findByFile(schem);
    }

    public Clipboard getClipboard() {
        Clipboard clipboard;
        try (ClipboardReader reader = format.getReader(new FileInputStream(schem))) {
            clipboard = reader.read();
            System.out.println(clipboard);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return clipboard;
    }

}
