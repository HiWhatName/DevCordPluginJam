package com.pluginjam.util;

import com.pluginjam.mob.DungeonMob;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.LivingEntity;

import java.util.Objects;
import java.util.Random;

public class Util {

    /** Function used to generate mob names depending on its criteria.
     * @param dungeonMob Mob which name should be generated
     * @param rnd Random instance. [Random rnd = new Random();]
     * @return String usable as the mob's display name
     */
    public static String generateMobName(DungeonMob<?> dungeonMob, Random rnd) { // miniBoss name gen.
        if(dungeonMob.isMiniBoss()){
            String[] miniBossPrefix = {"Almighty", "Ancient", "Ethereal"}; // TODO: Don't initialize these when the function gets called
            String[] miniBossSuffix = {"from hell", "of Doom", "", ""};
            return miniBossPrefix[rnd.nextInt(miniBossPrefix.length)] + " " + dungeonMob.getDisplayName() + " " +
                    dungeonMob.getLevel() + " " + miniBossSuffix[rnd.nextInt(miniBossSuffix.length)] + " | LVL";
        }else{
            return dungeonMob.getDisplayName() + " | LVL" + dungeonMob.getLevel();
        }
    }

    public static void setHealth(LivingEntity entity, double health) {
        AttributeInstance maxHealthAttribute = getAttribute(entity, Attribute.GENERIC_MAX_HEALTH);
        maxHealthAttribute.setBaseValue(health);
        entity.setHealth(health);
    }

    /**
     * Function used to randomly generate a miniBoss probability
     * @param level Current level of the dungeonMob entity
     * @param rnd Random instance. [Random rnd = new Random();]
     * @return Returns whether the dungeonMob should be a miniBoss
     */
    public static boolean genMiniBossChance(int level, Random rnd){
        return 1 * (1f + level / 3.25f) > (rnd.nextFloat(1.5f,25f));
    }

    public static AttributeInstance getAttribute(LivingEntity entity, Attribute attribute) {
        return Objects.requireNonNull(
                entity.getAttribute(attribute),
                "Entity " + entity + " doesn't have " + attribute + " attribute"
        );
    }

    /**
     * A linear function mapping the float danger level to an integer used by dungeon mobs.
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