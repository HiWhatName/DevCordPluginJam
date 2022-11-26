package com.pluginjam.util;

import com.pluginjam.mob.DungeonMob;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.LivingEntity;

import java.util.Objects;

public class Util {
    public static String generateMobName(DungeonMob<?> dungeonMob) {
        return dungeonMob.getDisplayName() + " | LVL" + dungeonMob.getLevel();
    }

    public static void setHealth(LivingEntity entity, double health) {
        AttributeInstance maxHealthAttribute = getAttribute(entity, Attribute.GENERIC_MAX_HEALTH);
        maxHealthAttribute.setBaseValue(health);
        entity.setHealth(health);
    }

    public static AttributeInstance getAttribute(LivingEntity entity, Attribute attribute) {
        return Objects.requireNonNull(
                entity.getAttribute(attribute),
                "Entity " + entity + " doesn't have " + attribute + " attribute"
        );
    }

    /**
     * A linear function mapping the float danger level to an integer to be displayed for the mobs.
     * @param danger The danger level you want to convert from
     * @param a The multiplicator
     * @return The level usable for mobs
     */
    public static int dangerToMobLevel(float danger, float a) {
        return (int) Math.ceil((danger - 1) * a + 1);
    }

    public static boolean isInBetween(int i, int i1, int i2) {
        return (i1 <= i && i <= i2) || (i1 >= i && i >= i2);
    }

    public static boolean isInBetween(double i, double i1, double i2) {
        return (i1 <= i && i <= i2) || (i1 >= i && i >= i2);
    }
}