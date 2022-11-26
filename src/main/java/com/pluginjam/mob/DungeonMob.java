package com.pluginjam.mob;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Mob;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class DungeonMob<T extends Mob> {
    protected int level;
    @Nullable
    protected T entity;

    /**
     * indicates whether the Mob should use the default mob name found in {@link Util} or not.
     * true: uses the default mob name,
     * false: makes it possible to set own name
     */
    private final boolean useDefaultName;

    @NotNull
    protected Class<T> baseEntityClass;

    public DungeonMob(int level, @NotNull Class<T> baseEntityClass, boolean useDefaultName) {
        this.level = level;
        this.baseEntityClass = baseEntityClass;
        this.entity = null;
        this.useDefaultName = useDefaultName;
    }
    public DungeonMob(int level, @NotNull Class<T> baseEntityClass) {
        this(level, baseEntityClass, true);
    }

    public T spawn(SpawnMeta spawnMeta) {
        World world = spawnMeta.world();
        Location location = spawnMeta.location();
        T spawned = world.spawn(location, baseEntityClass);
        if (useDefaultName) {
            spawned.setCustomName(Util.generateMobName(this));
            spawned.setCustomNameVisible(true);
        }

        return spawned;
    }

    public abstract String getDisplayName();

    public int getLevel() {
        return level;
    }

    public @NotNull Class<T> getBaseEntityClass() {
        return baseEntityClass;
    }

    public @Nullable T getEntity() {
        return entity;
    }
}