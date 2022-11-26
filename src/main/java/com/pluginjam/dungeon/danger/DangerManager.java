package com.pluginjam.dungeon.danger;

public class DangerManager {
    private float dangerLevel;

    public float getDangerLevel() {
        return dangerLevel;
    }

    public void setDangerLevel(float dangerLevel) {
        this.dangerLevel = dangerLevel;
    }

    public float increaseDangerLevel(float increase) {
        this.dangerLevel += increase;
        return dangerLevel;
    }

    public float decreaseDangerLevel(float increase) {
        this.dangerLevel -= increase;
        return dangerLevel;
    }
}
