package com.pluginjam.mob.mobs;

import com.pluginjam.mob.DungeonMob;
import org.bukkit.entity.Witch;
import org.jetbrains.annotations.NotNull;

public class WitchMob extends DungeonMob<Witch> {
    public WitchMob(int level, boolean miniBoss) {
        super(level, Witch.class, miniBoss);
    }

    @Override
    public String getDisplayName() {
        return "Witch";
    }
}
