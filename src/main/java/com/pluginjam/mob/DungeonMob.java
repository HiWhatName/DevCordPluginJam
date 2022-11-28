package com.pluginjam.mob;

import com.pluginjam.util.Util;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Mob;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

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
        // Set stats depended on level

        spawned.addPotionEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST, 99999, Math.round(level / 3), false, false)); //we cant use .setHealth()
        spawned.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(spawned.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getBaseValue() * level / 5);
        Random rnd = new Random();
        int i = rnd.nextInt(65);
        if(i <= level){
            spawned.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(spawned.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getBaseValue() * level / 4);
            spawned.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 99999, 1, false, false));
            spawned.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 9999, 1, false, false));
            spawned.setCustomName(ChatColor.RED + Util.generateMobName(this));

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