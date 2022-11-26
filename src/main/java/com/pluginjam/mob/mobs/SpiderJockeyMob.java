package com.pluginjam.mob.mobs;

import com.pluginjam.mob.DungeonMob;
import com.pluginjam.mob.SpawnMeta;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Spider;

public class SpiderJockeyMob extends DungeonMob<Spider> {
    @Override
    public String getDisplayName() {
        return "Spider Jockey";
    }

    public SpiderJockeyMob(int level) {
        super(level, Spider.class);
    }

    @Override
    public Spider spawn(SpawnMeta spawnMeta) {
        Spider spider = super.spawn(spawnMeta);
        spider.addPassenger(spawnSkeleton(spawnMeta));
        return spider;
    }

    private Skeleton spawnSkeleton(SpawnMeta spawnMeta) {
        Skeleton skeleton = spawnMeta.world().spawn(spawnMeta.location(), Skeleton.class);
        //TODO: add equipment later maybe
        return skeleton;
    }
}
