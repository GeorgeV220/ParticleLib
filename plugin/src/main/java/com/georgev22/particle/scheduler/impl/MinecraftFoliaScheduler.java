package com.georgev22.particle.scheduler.impl;

import com.georgev22.particle.MainPlugin;
import com.georgev22.particle.scheduler.MinecraftScheduler;
import com.georgev22.particle.scheduler.SchedulerTask;
import io.papermc.paper.threadedregions.scheduler.ScheduledTask;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

@ApiStatus.NonExtendable
public class MinecraftFoliaScheduler implements MinecraftScheduler {

    private final MainPlugin plugin;

    public MinecraftFoliaScheduler(MainPlugin plugin) {
        this.plugin = plugin;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SchedulerTask runTask(Runnable task) {
        return new FoliaSchedulerTask(Bukkit.getGlobalRegionScheduler().run(plugin, (scheduledTask) -> task.run()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> CompletableFuture<T> runTask(Supplier<T> task) {
        CompletableFuture<T> future = new CompletableFuture<>();
        Bukkit.getGlobalRegionScheduler().run(plugin, scheduledTask -> {
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
        return new FoliaSchedulerTask(Bukkit.getAsyncScheduler().runNow(plugin, (scheduledTask) -> task.run()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> CompletableFuture<T> runAsyncTask(Supplier<T> task) {
        CompletableFuture<T> future = new CompletableFuture<>();
        Bukkit.getAsyncScheduler().runNow(plugin, scheduledTask -> {
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
        return new FoliaSchedulerTask(Bukkit.getGlobalRegionScheduler().runDelayed(plugin, (scheduledTask) -> task.run(), delay));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> CompletableFuture<T> createDelayedTask(Supplier<T> task, long delay) {
        CompletableFuture<T> future = new CompletableFuture<>();
        Bukkit.getGlobalRegionScheduler().runDelayed(plugin, scheduledTask -> {
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
        return new FoliaSchedulerTask(Bukkit.getGlobalRegionScheduler().runAtFixedRate(plugin, (scheduledTask) -> task.run(), delay, period));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SchedulerTask createAsyncDelayedTask(Runnable task, long delay) {
        return new FoliaSchedulerTask(Bukkit.getAsyncScheduler().runDelayed(plugin, (scheduledTask) -> task.run(), (delay / 20), TimeUnit.SECONDS));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> CompletableFuture<T> createAsyncDelayedTask(Supplier<T> task, long delay) {
        CompletableFuture<T> future = new CompletableFuture<>();
        Bukkit.getAsyncScheduler().runDelayed(plugin, scheduledTask -> {
            try {
                T result = task.get();
                future.complete(result);
            } catch (Exception e) {
                future.completeExceptionally(e);
            }
        }, (delay / 20), TimeUnit.SECONDS);
        return future;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SchedulerTask createAsyncRepeatingTask(Runnable task, long delay, long period) {
        return new FoliaSchedulerTask(Bukkit.getAsyncScheduler().runAtFixedRate(plugin, (scheduledTask) -> task.run(), (delay / 20), (period / 20), TimeUnit.SECONDS));
    }

    /**
     * {@inheritDoc}
     */
    public SchedulerTask createDelayedTaskForWorld(Runnable task, World world, @NotNull Chunk chunk, long delay) {
        return new FoliaSchedulerTask(Bukkit.getRegionScheduler().runDelayed(plugin, world, chunk.getX(), chunk.getZ(), (scheduledTask) -> task.run(), delay));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> CompletableFuture<T> createDelayedTaskForWorld(Supplier<T> task, World world, @NotNull Chunk chunk, long delay) {
        CompletableFuture<T> future = new CompletableFuture<>();
        Bukkit.getRegionScheduler().runDelayed(
                plugin,
                world,
                chunk.getX(),
                chunk.getZ(),
                scheduledTask -> {
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
    public SchedulerTask createDelayedForLocation(Runnable task, Location location, long delay) {
        return new FoliaSchedulerTask(Bukkit.getRegionScheduler().runDelayed(plugin, location, (scheduledTask) -> task.run(), delay));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> CompletableFuture<T> createDelayedForLocation(Supplier<T> task, Location location, long delay) {
        CompletableFuture<T> future = new CompletableFuture<>();
        Bukkit.getRegionScheduler().runDelayed(plugin, location, scheduledTask -> {
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
    public SchedulerTask createDelayedForEntity(Runnable task, Runnable retired, @NotNull Entity entity, long delay) {
        return new FoliaSchedulerTask(entity.getScheduler().runDelayed(plugin, (scheduledTask) -> task.run(), retired, delay));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> CompletableFuture<T> createDelayedForEntity(Supplier<T> task, Runnable retired, @NotNull Entity entity, long delay) {
        CompletableFuture<T> future = new CompletableFuture<>();
        entity.getScheduler().runDelayed(plugin, scheduledTask -> {
            try {
                T result = task.get();
                future.complete(result);
            } catch (Exception e) {
                future.completeExceptionally(e);
            }
        }, retired, delay);
        return future;
    }

    /**
     * {@inheritDoc}
     */
    public SchedulerTask createTaskForWorld(Runnable task, World world, @NotNull Chunk chunk) {
        return new FoliaSchedulerTask(Bukkit.getRegionScheduler().run(plugin, world, chunk.getX(), chunk.getZ(), (scheduledTask) -> task.run()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> CompletableFuture<T> createTaskForWorld(Supplier<T> task, World world, @NotNull Chunk chunk) {
        CompletableFuture<T> future = new CompletableFuture<>();
        Bukkit.getRegionScheduler().run(plugin, world, chunk.getX(), chunk.getZ(), scheduledTask -> {
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
    public SchedulerTask createTaskForLocation(Runnable task, Location location) {
        return new FoliaSchedulerTask(Bukkit.getRegionScheduler().run(plugin, location, (scheduledTask) -> task.run()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> CompletableFuture<T> createTaskForLocation(Supplier<T> task, Location location) {
        CompletableFuture<T> future = new CompletableFuture<>();
        Bukkit.getRegionScheduler().run(plugin, location, scheduledTask -> {
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
    public SchedulerTask createTaskForEntity(Runnable task, Runnable retired, @NotNull Entity entity) {
        return new FoliaSchedulerTask(entity.getScheduler().run(plugin, (scheduledTask) -> task.run(), retired));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> CompletableFuture<T> createTaskForEntity(Supplier<T> task, Runnable retired, @NotNull Entity entity) {
        CompletableFuture<T> future = new CompletableFuture<>();
        entity.getScheduler().run(plugin, scheduledTask -> {
            try {
                T result = task.get();
                future.complete(result);
            } catch (Exception e) {
                future.completeExceptionally(e);
            }
        }, retired);
        return future;
    }

    /**
     * {@inheritDoc}
     */
    public SchedulerTask createRepeatingTaskForWorld(Runnable task, World world, @NotNull Chunk chunk, long delay, long period) {
        return new FoliaSchedulerTask(Bukkit.getRegionScheduler().runAtFixedRate(plugin, world, chunk.getX(), chunk.getZ(), (scheduledTask) -> task.run(), delay, period));
    }

    /**
     * {@inheritDoc}
     */
    public SchedulerTask createRepeatingTaskForLocation(Runnable task, Location location, long delay, long period) {
        return new FoliaSchedulerTask(Bukkit.getRegionScheduler().runAtFixedRate(plugin, location, (scheduledTask) -> task.run(), delay, period));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SchedulerTask createRepeatingTaskForEntity(Runnable task, Runnable retired, @NotNull Entity entity, long delay, long period) {
        return new FoliaSchedulerTask(entity.getScheduler().runAtFixedRate(plugin, (scheduledTask) -> task.run(), retired, delay, period));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void cancelTasks() {
        Bukkit.getGlobalRegionScheduler().cancelTasks(plugin);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MinecraftScheduler getScheduler() {
        return this;
    }

    public static class FoliaSchedulerTask implements SchedulerTask {

        private final ScheduledTask scheduledTask;

        public FoliaSchedulerTask(ScheduledTask scheduledTask) {
            this.scheduledTask = scheduledTask;
        }

        @Override
        public void cancel() {
            scheduledTask.cancel();
        }

        @Override
        public boolean isCancelled() {
            return scheduledTask.isCancelled();
        }

        @Override
        public int getTaskId() {
            return 0;
        }

        @Override
        public boolean isRunning() {
            return switch (scheduledTask.getExecutionState()) {
                case IDLE, RUNNING -> true;
                default -> false;
            };
        }
    }
}