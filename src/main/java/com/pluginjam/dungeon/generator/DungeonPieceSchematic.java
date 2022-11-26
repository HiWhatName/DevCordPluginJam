package com.pluginjam.dungeon.generator;

import com.pluginjam.PluginJam;
import com.sk89q.worldedit.extent.clipboard.Clipboard;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormat;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormats;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class DungeonPieceSchematic {

    private final File schemFile;
    private Clipboard clipboard;
    protected ClipboardFormat format;

    public DungeonPieceSchematic(String label) {
        this.schemFile = getSchemFile(label);
        this.format = ClipboardFormats.findByFile(schemFile);
    }


    private File getSchemFile(String label) {
        PluginJam instance = PluginJam.getInstance();

        return new File(instance.getDataFolder() + "/schem/" + label + ".schem");
    }

    public Clipboard loadSchemFile() {
        Clipboard clipboard;
        try (ClipboardReader reader = format.getReader(new FileInputStream(schemFile))) {
            clipboard = reader.read();
            System.out.println(clipboard);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return clipboard;
    }

}
