package com.pluginjam.dungeon.generator;

import com.sk89q.worldedit.extent.clipboard.Clipboard;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormat;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormats;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public abstract class DungeonPiece {

    public File schem;
    public Clipboard clipboard;
    ClipboardFormat format;
    public Clipboard getClipboard(){
        Clipboard clipboard = null;
        try (ClipboardReader reader = format.getReader(new FileInputStream(schem))) {
            clipboard = reader.read();
            System.out.println(clipboard);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return clipboard;
    }

}
