package com.pluginjam.mob.mobs;

import com.pluginjam.mob.DungeonMob;
import org.bukkit.entity.Creeper;
import org.jetbrains.annotations.NotNull;

public class CreeperMob extends DungeonMob<Creeper> {

    public CreeperMob(int level, @NotNull Class<Creeper> baseEntityClass) {
        super(level, baseEntityClass, true);
    }

    @Override
    public String getDisplayName() {
        return "Creeper";
    }
}
