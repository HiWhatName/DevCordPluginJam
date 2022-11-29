package com.pluginjam.mob.mobs;

import com.pluginjam.mob.DungeonMob;
import org.bukkit.entity.Skeleton;

public class SkeletonMob extends DungeonMob<Skeleton> {
    public SkeletonMob(int level, boolean miniBoss) {
        super(level, Skeleton.class, miniBoss);
    }

    @Override
    public String getDisplayName() {
        return "Skeleton";
    }
}
