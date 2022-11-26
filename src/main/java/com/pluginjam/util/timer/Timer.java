package com.pluginjam.util.timer;

public interface Timer {

    boolean start();
    boolean stop();
    boolean isRunning();
    default boolean isStopped() {
        return !isRunning();
    }

}
