package com.pluginjam.mob;

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

    private static AttributeInstance getAttribute(LivingEntity entity, Attribute attribute) {
        return Objects.requireNonNull(
                entity.getAttribute(attribute),
                "Entity " + entity + " doesn't have " + attribute + " attribute"
        );
    }
}