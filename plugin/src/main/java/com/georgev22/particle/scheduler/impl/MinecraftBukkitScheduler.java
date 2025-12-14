package com.georgev22.particle.scheduler.impl;

import com.georgev22.particle.MainPlugin;
import com.georgev22.particle.scheduler.MinecraftScheduler;
import com.georgev22.particle.scheduler.SchedulerTask;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.scheduler.BukkitTask;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

@ApiStatus.NonExtendable
public class MinecraftBukkitScheduler implements MinecraftScheduler {

    private final MainPlugin plugin;

    public MinecraftBukkitScheduler(MainPlugin mainPlugin) {
        this.plugin = mainPlugin;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SchedulerTask runTask(Runnable task) {
        return new BukkitSchedulerTask(Bukkit.getScheduler().runTask(plugin, task));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> CompletableFuture<T> runTask(Supplier<T> task) {
        CompletableFuture<T> future = new CompletableFuture<>();
        Bukkit.getScheduler().runTask(plugin, () -> {
            try {
                T result = task.get();
                future.complete(result);
            } catch (Exception e) {
                future.completeExceptionally(e);
            }
        });

        return future;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SchedulerTask runAsyncTask(Runnable task) {
        return new BukkitSchedulerTask(Bukkit.getScheduler().runTaskAsynchronously(plugin, task));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> CompletableFuture<T> runAsyncTask(Supplier<T> task) {
        CompletableFuture<T> future = new CompletableFuture<>();
        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
            try {
                T result = task.get();
                future.complete(result);
            } catch (Exception e) {
                future.completeExceptionally(e);
            }
        });

        return future;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SchedulerTask createDelayedTask(Runnable task, long delay) {
        return new BukkitSchedulerTask(Bukkit.getScheduler().runTaskLater(plugin, task, delay));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> CompletableFuture<T> createDelayedTask(Supplier<T> task, long delay) {
        CompletableFuture<T> future = new CompletableFuture<>();
        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            try {
                T result = task.get();
                future.complete(result);
            } catch (Exception e) {
                future.completeExceptionally(e);
            }
        }, delay);

        return future;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SchedulerTask createRepeatingTask(Runnable task, long delay, long period) {
        return new BukkitSchedulerTask(Bukkit.getScheduler().runTaskTimer(plugin, task, delay, period));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SchedulerTask createAsyncDelayedTask(Runnable task, long delay) {
        return new BukkitSchedulerTask(Bukkit.getScheduler().runTaskLaterAsynchronously(plugin, task, delay));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> CompletableFuture<T> createAsyncDelayedTask(Supplier<T> task, long delay) {
        CompletableFuture<T> future = new CompletableFuture<>();
        Bukkit.getScheduler().runTaskLaterAsynchronously(plugin, () -> {
            try {
                T result = task.get();
                future.complete(result);
            } catch (Exception e) {
                future.completeExceptionally(e);
            }
        }, delay);

        return future;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SchedulerTask createAsyncRepeatingTask(Runnable task, long delay, long period) {
        return new BukkitSchedulerTask(Bukkit.getScheduler().runTaskTimerAsynchronously(plugin, task, delay, period));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SchedulerTask createDelayedTaskForWorld(Runnable task, World world, @NotNull Chunk chunk, long delay) {
        return new BukkitSchedulerTask(Bukkit.getScheduler().runTaskLater(plugin, task, delay));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> CompletableFuture<T> createDelayedTaskForWorld(Supplier<T> task, World world, @NotNull Chunk chunk, long delay) {
        return this.createDelayedTask(task, delay);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SchedulerTask createDelayedForLocation(Runnable task, Location location, long delay) {
        return new BukkitSchedulerTask(Bukkit.getScheduler().runTaskLater(plugin, task, delay));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> CompletableFuture<T> createDelayedForLocation(Supplier<T> task, Location location, long delay) {
        return this.createDelayedTask(task, delay);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SchedulerTask createDelayedForEntity(Runnable task, Runnable retired, Entity entity, long delay) {
        return new BukkitSchedulerTask(Bukkit.getScheduler().runTaskLater(plugin, task, delay));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> CompletableFuture<T> createDelayedForEntity(Supplier<T> task, Runnable retired, Entity entity, long delay) {
        return this.createDelayedTask(task, delay);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SchedulerTask createTaskForWorld(Runnable task, World world, @NotNull Chunk chunk) {
        return new BukkitSchedulerTask(Bukkit.getScheduler().runTask(plugin, task));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> CompletableFuture<T> createTaskForWorld(Supplier<T> task, World world, @NotNull Chunk chunk) {
        return this.runTask(task);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SchedulerTask createTaskForLocation(Runnable task, Location location) {
        return new BukkitSchedulerTask(Bukkit.getScheduler().runTask(plugin, task));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> CompletableFuture<T> createTaskForLocation(Supplier<T> task, Location location) {
        return this.runTask(task);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SchedulerTask createTaskForEntity(Runnable task, Runnable retired, Entity entity) {
        return new BukkitSchedulerTask(Bukkit.getScheduler().runTask(plugin, task));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> CompletableFuture<T> createTaskForEntity(Supplier<T> task, Runnable retired, Entity entity) {
        return this.runTask(task);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SchedulerTask createRepeatingTaskForWorld(Runnable task, World world, @NotNull Chunk chunk, long delay, long period) {
        return new BukkitSchedulerTask(Bukkit.getScheduler().runTaskTimer(plugin, task, delay, period));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SchedulerTask createRepeatingTaskForLocation(Runnable task, Location location, long delay, long period) {
        return new BukkitSchedulerTask(Bukkit.getScheduler().runTaskTimer(plugin, task, delay, period));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SchedulerTask createRepeatingTaskForEntity(Runnable task, Runnable retired, Entity entity, long delay, long period) {
        return new BukkitSchedulerTask(Bukkit.getScheduler().runTaskTimer(plugin, task, delay, period));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void cancelTasks() {
        Bukkit.getScheduler().cancelTasks(plugin);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MinecraftScheduler getScheduler() {
        return this;
    }

    public static class BukkitSchedulerTask implements SchedulerTask {

        private final BukkitTask bukkitTask;

        public BukkitSchedulerTask(BukkitTask bukkitTask) {
            this.bukkitTask = bukkitTask;
        }

        @Override
        public void cancel() {
            bukkitTask.cancel();
        }

        @Override
        public boolean isCancelled() {
            return bukkitTask.isCancelled();
        }

        @Override
        public int getTaskId() {
            return bukkitTask.getTaskId();
        }

        @Override
        public boolean isRunning() {
            return Bukkit.getScheduler().isCurrentlyRunning(bukkitTask.getTaskId());
        }
    }
}