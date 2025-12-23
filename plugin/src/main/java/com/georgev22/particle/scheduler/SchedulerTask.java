package com.georgev22.particle.scheduler;

public interface SchedulerTask {

    void cancel();

    boolean isCancelled();

    int getTaskId();

    boolean isRunning();

}