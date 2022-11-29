package com.pluginjam.dungeon.danger;

import org.bukkit.Bukkit;

public class DangerManager {
    private float dangerLevel;
    private DangerChangeEvent dangerChangeEvent;

    public float getDangerLevel() {
        return dangerLevel;
    }

    public void setDangerLevel(float dangerLevel) {
        this.dangerLevel = dangerLevel;
    }

    public float increaseDangerLevel(float increase) {
        this.dangerChangeEvent = new DangerChangeEvent(increase, dangerLevel);
        Bukkit.getServer().getPluginManager().callEvent(dangerChangeEvent);
        this.dangerLevel += increase;
        return dangerLevel;
    }

    public float decreaseDangerLevel(float decrease) {
        this.dangerChangeEvent = new DangerChangeEvent(decrease, dangerLevel);
        Bukkit.getServer().getPluginManager().callEvent(dangerChangeEvent);
        this.dangerLevel -= decrease;
        return dangerLevel;
    }
}
