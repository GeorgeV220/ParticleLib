package com.georgev22.particle.scheduler;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

/**
 * A non-extendable interface representing a scheduler for task scheduling and cancellation.
 * Tasks can be scheduled synchronously (on the main server thread) or asynchronously,
 * with optional delays, repetition, and context-specific execution (world, chunk, location, entity).
 */
public interface MinecraftScheduler {

    /**
     * Schedules a synchronous task to run on the main server thread.
     *
     * @param task the task to run
     * @return a {@link SchedulerTask} instance representing the scheduled task
     */
    SchedulerTask runTask(Runnable task);

    /**
     * Schedules a synchronous task that returns a result via a {@link CompletableFuture}.
     * The supplier runs on the main server thread.
     *
     * @param task the supplier providing the result
     * @param <T>  the type of the result
     * @return a {@link CompletableFuture} that will be completed with the supplier's result
     */
    <T> CompletableFuture<T> runTask(Supplier<T> task);

    /**
     * Schedules an asynchronous task to run off the main server thread.
     *
     * @param task the task to run
     * @return a {@link SchedulerTask} instance representing the scheduled task
     */
    SchedulerTask runAsyncTask(Runnable task);

    /**
     * Schedules an asynchronous task that returns a result via a {@link CompletableFuture}.
     * The supplier runs off the main server thread.
     *
     * @param task the supplier providing the result
     * @param <T>  the type of the result
     * @return a {@link CompletableFuture} that will be completed with the supplier's result
     */
    <T> CompletableFuture<T> runAsyncTask(Supplier<T> task);

    /**
     * Schedules a synchronous delayed task to run after the specified delay.
     *
     * @param task  the task to run
     * @param delay the delay in ticks before the task executes
     * @return a {@link SchedulerTask} instance representing the scheduled task
     */
    SchedulerTask createDelayedTask(Runnable task, long delay);

    /**
     * Schedules a synchronous delayed task that returns a result via a {@link CompletableFuture}.
     * The supplier runs after the delay on the main server thread.
     *
     * @param task  the supplier providing the result
     * @param delay the delay in ticks before the supplier executes
     * @param <T>   the type of the result
     * @return a {@link CompletableFuture} that will be completed with the supplier's result
     */
    <T> CompletableFuture<T> createDelayedTask(Supplier<T> task, long delay);

    /**
     * Schedules a synchronous repeating task with an initial delay and subsequent periods.
     *
     * @param task   the task to run
     * @param delay  the delay in ticks before the first execution
     * @param period the period in ticks between subsequent executions
     * @return a {@link SchedulerTask} instance representing the scheduled task
     */
    SchedulerTask createRepeatingTask(Runnable task, long delay, long period);

    /**
     * Schedules an asynchronous delayed task to run after the specified delay.
     *
     * @param task  the task to run
     * @param delay the delay in ticks before the task executes
     * @return a {@link SchedulerTask} instance representing the scheduled task
     */
    SchedulerTask createAsyncDelayedTask(Runnable task, long delay);

    /**
     * Schedules an asynchronous delayed task that returns a result via a {@link CompletableFuture}.
     * The supplier runs after the delay off the main server thread.
     *
     * @param task  the supplier providing the result
     * @param delay the delay in ticks before the supplier executes
     * @param <T>   the type of the result
     * @return a {@link CompletableFuture} that will be completed with the supplier's result
     */
    <T> CompletableFuture<T> createAsyncDelayedTask(Supplier<T> task, long delay);

    /**
     * Schedules an asynchronous repeating task with an initial delay and subsequent periods.
     *
     * @param task   the task to run
     * @param delay  the delay in ticks before the first execution
     * @param period the period in ticks between subsequent executions
     * @return a {@link SchedulerTask} instance representing the scheduled task
     */
    SchedulerTask createAsyncRepeatingTask(Runnable task, long delay, long period);

    /**
     * Schedules a synchronous delayed task to run in the specified world and chunk context.
     * The task will only execute if the chunk is loaded.
     *
     * @param task  the task to run
     * @param world the world associated with the task
     * @param chunk the chunk associated with the task (must not be null)
     * @param delay the delay in ticks before the task executes
     * @return a {@link SchedulerTask} instance representing the scheduled task
     */
    SchedulerTask createDelayedTaskForWorld(Runnable task, World world, @NotNull Chunk chunk, long delay);

    /**
     * Schedules a synchronous delayed task in the specified world and chunk context that returns a result.
     * The supplier runs on the main server thread if the chunk is loaded.
     *
     * @param task  the supplier providing the result
     * @param world the world associated with the task
     * @param chunk the chunk associated with the task (must not be null)
     * @param delay the delay in ticks before the supplier executes
     * @param <T>   the type of the result
     * @return a {@link CompletableFuture} that will be completed with the supplier's result
     */
    <T> CompletableFuture<T> createDelayedTaskForWorld(Supplier<T> task, World world, @NotNull Chunk chunk, long delay);

    /**
     * Schedules a synchronous delayed task associated with a specific location.
     * The task runs on the main thread when the location's chunk is loaded.
     *
     * @param task     the task to run
     * @param location the location associated with the task
     * @param delay    the delay in ticks before the task executes
     * @return a {@link SchedulerTask} instance representing the scheduled task
     */
    SchedulerTask createDelayedForLocation(Runnable task, Location location, long delay);

    /**
     * Schedules a synchronous delayed task associated with a location that returns a result.
     * The supplier runs on the main server thread when the location's chunk is loaded.
     *
     * @param task     the supplier providing the result
     * @param location the location associated with the task
     * @param delay    the delay in ticks before the supplier executes
     * @param <T>      the type of the result
     * @return a {@link CompletableFuture} that will be completed with the supplier's result
     */
    <T> CompletableFuture<T> createDelayedForLocation(Supplier<T> task, Location location, long delay);

    /**
     * Schedules a synchronous delayed task associated with an entity. If the entity is retired (removed),
     * the retired runnable is executed.
     *
     * @param task    the task to run
     * @param retired the runnable to execute if the entity is retired before the task runs
     * @param entity  the entity associated with the task
     * @param delay   the delay in ticks before the task executes
     * @return a {@link SchedulerTask} instance representing the scheduled task
     */
    SchedulerTask createDelayedForEntity(Runnable task, Runnable retired, Entity entity, long delay);

    /**
     * Schedules a synchronous delayed task associated with an entity that returns a result.
     * If the entity is retired, the retired runnable is executed.
     *
     * @param task    the supplier providing the result
     * @param retired the runnable to execute if the entity is retired
     * @param entity  the entity associated with the task
     * @param delay   the delay in ticks before the supplier executes
     * @param <T>     the type of the result
     * @return a {@link CompletableFuture} that will be completed with the supplier's result
     */
    <T> CompletableFuture<T> createDelayedForEntity(Supplier<T> task, Runnable retired, Entity entity, long delay);

    /**
     * Schedules a synchronous task to run in the specified world and chunk context.
     * The task executes immediately if the chunk is loaded.
     *
     * @param task  the task to run
     * @param world the world associated with the task
     * @param chunk the chunk associated with the task (must not be null)
     * @return a {@link SchedulerTask} instance representing the scheduled task
     */
    SchedulerTask createTaskForWorld(Runnable task, World world, @NotNull Chunk chunk);

    /**
     * Schedules a synchronous task in the specified world and chunk context that returns a result.
     * The supplier runs on the main server thread if the chunk is loaded.
     *
     * @param task  the supplier providing the result
     * @param world the world associated with the task
     * @param chunk the chunk associated with the task (must not be null)
     * @param <T>   the type of the result
     * @return a {@link CompletableFuture} that will be completed with the supplier's result
     */
    <T> CompletableFuture<T> createTaskForWorld(Supplier<T> task, World world, @NotNull Chunk chunk);

    /**
     * Schedules a synchronous task associated with a specific location.
     * The task runs on the main thread when the location's chunk is loaded.
     *
     * @param task     the task to run
     * @param location the location associated with the task
     * @return a {@link SchedulerTask} instance representing the scheduled task
     */
    SchedulerTask createTaskForLocation(Runnable task, Location location);

    /**
     * Schedules a synchronous task associated with a location that returns a result.
     * The supplier runs on the main server thread when the location's chunk is loaded.
     *
     * @param task     the supplier providing the result
     * @param location the location associated with the task
     * @param <T>      the type of the result
     * @return a {@link CompletableFuture} that will be completed with the supplier's result
     */
    <T> CompletableFuture<T> createTaskForLocation(Supplier<T> task, Location location);

    /**
     * Schedules a synchronous task associated with an entity. If the entity is retired,
     * the retired runnable is executed.
     *
     * @param task    the task to run
     * @param retired the runnable to execute if the entity is retired
     * @param entity  the entity associated with the task
     * @return a {@link SchedulerTask} instance representing the scheduled task
     */
    SchedulerTask createTaskForEntity(Runnable task, Runnable retired, Entity entity);

    /**
     * Schedules a synchronous task associated with an entity that returns a result.
     * If the entity is retired, the retired runnable is executed.
     *
     * @param task    the supplier providing the result
     * @param retired the runnable to execute if the entity is retired
     * @param entity  the entity associated with the task
     * @param <T>     the type of the result
     * @return a {@link CompletableFuture} that will be completed with the supplier's result
     */
    <T> CompletableFuture<T> createTaskForEntity(Supplier<T> task, Runnable retired, Entity entity);

    /**
     * Schedules a synchronous repeating task in the specified world and chunk context.
     * The task repeats only if the chunk is loaded.
     *
     * @param task   the task to run
     * @param world  the world associated with the task
     * @param chunk  the chunk associated with the task (must not be null)
     * @param delay  the delay in ticks before the first execution
     * @param period the period in ticks between subsequent executions
     * @return a {@link SchedulerTask} instance representing the scheduled task
     */
    SchedulerTask createRepeatingTaskForWorld(Runnable task, World world, @NotNull Chunk chunk, long delay, long period);

    /**
     * Schedules a synchronous repeating task associated with a specific location.
     * The task runs on the main thread when the location's chunk is loaded.
     *
     * @param task     the task to run
     * @param location the location associated with the task
     * @param delay    the delay in ticks before the first execution
     * @param period   the period in ticks between subsequent executions
     * @return a {@link SchedulerTask} instance representing the scheduled task
     */
    SchedulerTask createRepeatingTaskForLocation(Runnable task, Location location, long delay, long period);

    /**
     * Schedules a synchronous repeating task associated with an entity. The task runs with an initial delay
     * and repeats with the specified period. If the entity is retired, the retired runnable is called.
     *
     * @param task    the task to run
     * @param retired the runnable to execute if the entity is retired
     * @param entity  the entity associated with the task
     * @param delay   the delay in ticks before the first execution
     * @param period  the period in ticks between subsequent executions
     */
    SchedulerTask createRepeatingTaskForEntity(Runnable task, Runnable retired, Entity entity, long delay, long period);

    /**
     * Cancels all tasks.
     */
    void cancelTasks();

    /**
     * Gets the scheduler
     *
     * @return The scheduler
     */
    MinecraftScheduler getScheduler();

}