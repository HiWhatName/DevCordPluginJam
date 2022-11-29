package com.pluginjam.mob;

import com.pluginjam.util.Util;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Creeper;
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
    private final boolean miniBoss;
    @NotNull
    protected Class<T> baseEntityClass;

    public DungeonMob(int level, @NotNull Class<T> baseEntityClass, boolean miniBoss) {
        this.level = level;
        this.baseEntityClass = baseEntityClass;
        this.entity = null;
        this.miniBoss = miniBoss;
    }
    
    public DungeonMob(int level, @NotNull Class<T> baseEntityClass) {
        this(level, baseEntityClass, false);
    }

    public T spawn(SpawnMeta spawnMeta) {
        World world = spawnMeta.world();
        Location location = spawnMeta.location();
        Random rnd = new Random();
        T spawned = world.spawn(location, baseEntityClass);

        if(miniBoss){
            spawned.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, Integer.MAX_VALUE, 1, false, false));
            spawned.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 1, false, false)); // + 130% Dmg
            spawned.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(spawned.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getBaseValue() * 1.15f); // 15% Faster
            spawned.getAttribute(Attribute.GENERIC_ARMOR).setBaseValue(spawned.getAttribute(Attribute.GENERIC_ARMOR).getBaseValue() * 1.25f); // 25% More armor since we won't add more hp.
            spawned.setCustomName(ChatColor.RED + Util.generateMobName(this, true, rnd));
        }else{
            spawned.setCustomName(Util.generateMobName(this));
        }

        //AttributeInstance dmgAttribute = Util.get.... After 5hp dmg, we'll only add 1/2
        double baseDMG = spawned.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getBaseValue();
        if(baseDMG * level / 4 > baseDMG + 5f){ spawned.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(5f + ((baseDMG * level / 3.5) - 5f) / 2);
        }else{ spawned.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(baseDMG * level / 3.5); } //TODO: Change this

        Util.setHealth(spawned, spawned.getHealth() + (level / 1.75));

        if(spawned instanceof Creeper creeper){
            creeper.setExplosionRadius((creeper.getExplosionRadius() + Math.round(level / 8.5f))); // Lvl. 20: +2.3 radius
            creeper.setMaxFuseTicks(creeper.getMaxFuseTicks() - level); //Lvl. 10: -0.5sec
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