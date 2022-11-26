package com.pluginjam.mob.mobs;

import com.pluginjam.mob.DungeonMob;
import com.pluginjam.mob.SpawnMeta;
import org.bukkit.entity.Spider;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class SpiderMob extends DungeonMob<Spider> {

    private static final String DISPLAY_NAME = "Spider";
    public SpiderMob(int level) {
        super(level, Spider.class);
    }

    @Override
    public @NotNull Spider spawn(SpawnMeta spawnMeta) {
        return super.spawn(spawnMeta);
    }

    @Override
    public String getDisplayName() {
        return DISPLAY_NAME;
    }
}
