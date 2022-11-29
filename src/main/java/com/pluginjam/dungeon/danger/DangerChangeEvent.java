package com.pluginjam.dungeon.danger;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class DangerChangeEvent extends Event {

    private float difference;
    private static final HandlerList HANDLERS_LIST = new HandlerList();
    private float currentDanger;

    public DangerChangeEvent(float difference, float currentDanger){
        this.difference = difference;
        this.currentDanger = currentDanger;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS_LIST;
    }

    public float getDifference(){ //Once: getIncreaseAmount
        return this.difference;
    }
    public float getDanger(){
        return this.currentDanger;
    }

    public float getDangerBefore(){
        return this.currentDanger - this.difference;
    }
}
