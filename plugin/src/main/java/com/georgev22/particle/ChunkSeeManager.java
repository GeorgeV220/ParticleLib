package com.georgev22.particle;

import com.georgev22.particle.scheduler.SchedulerTask;
import com.google.common.collect.Sets;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.Set;
import java.util.logging.Level;

/**
 * Manages displaying particle outlines around the chunk
 * a player is currently standing in.
 *
 * <p>
 * Players can be added or removed dynamically. While at least
 * one player is tracked, an asynchronous repeating task runs
 * that renders particles along the chunk borders.
 * </p>
 */
public class ChunkSeeManager {

    /**
     * Reference to the main plugin instance
     */
    private final MainPlugin mainPlugin;

    /**
     * Players currently viewing their chunk boundaries
     */
    private final Set<Player> chunk = Sets.newHashSet();

    /**
     * Scheduled repeating task responsible for rendering particles
     */
    private SchedulerTask task = null;

    /**
     * Particle effect used for chunk visualization
     */
    private ParticleEffect effect;

    /**
     * Creates a new ChunkSeeManager.
     *
     * @param mainPlugin the main plugin instance
     */
    public ChunkSeeManager(MainPlugin mainPlugin) {
        this.mainPlugin = mainPlugin;
    }

    /**
     * Reloads the chunk visualization task.
     *
     * <p>
     * Stops the current task and restarts it if there are
     * still players registered.
     * </p>
     */
    public void reloadChunkSee() {
        this.stopTask();
        if (!this.chunk.isEmpty()) {
            this.startTask();
        }
    }

    /**
     * Adds a player to the chunk visualization system.
     *
     * <p>
     * If the player is already registered, nothing happens.
     * Starting the task is handled automatically.
     * </p>
     *
     * @param player the player to add
     */
    public void addPlayer(final Player player) {
        if (this.isInside(player)) {
            return;
        }

        chunk.add(player);
        this.startTask();
    }

    /**
     * Checks whether a player is already registered.
     *
     * @param player the player to check
     * @return {@code true} if the player is already tracked
     */
    public boolean isInside(final Player player) {
        return chunk.stream()
                .map(Player::getUniqueId)
                .anyMatch(id -> player.getUniqueId().equals(id));
    }

    /**
     * Removes a player from the chunk visualization system.
     *
     * <p>
     * If no players remain after removal, the task is stopped.
     * </p>
     *
     * @param player the player to remove
     */
    public void removePlayer(final Player player) {
        this.chunk.remove(player);

        if (this.chunk.isEmpty()) {
            this.stopTask();
        }
    }

    /**
     * Starts the repeating task responsible for displaying
     * chunk border particles.
     *
     * <p>
     * If a task is already running, it will be stopped and
     * restarted to avoid duplicates.
     * </p>
     */
    private void startTask() {
        if (task != null) {
            this.stopTask();
        }

        task = this.mainPlugin.getMinecraftScheduler()
                .createRepeatingTask(() -> {
                    try {
                        for (Player player : this.chunk) {
                            Location loc = player.getLocation();
                            Chunk c = loc.getChunk();

                            int minX = c.getX() * 16;
                            int minZ = c.getZ() * 16;

                            int maxX = minX + 16;
                            int maxZ = minZ + 16;

                            int minY = loc.getBlockY();

                            for (int x = minX; x < minX + 17; x++) {
                                displayEffect(player,
                                        new Location(player.getWorld(), x, minY, minZ));
                            }

                            for (int z = minZ; z < minZ + 17; z++) {
                                displayEffect(player,
                                        new Location(player.getWorld(), minX, minY, z));
                            }

                            for (int x = maxX; x > maxX - 17; x--) {
                                displayEffect(player,
                                        new Location(player.getWorld(), x, minY, maxZ));
                            }

                            for (int z = maxZ; z > maxZ - 17; z--) {
                                displayEffect(player,
                                        new Location(player.getWorld(), maxX, minY, z));
                            }
                        }
                    } catch (Throwable t) {
                        mainPlugin.getLogger().log(Level.SEVERE, "Failed", t);
                    }
                }, 5L, 20);
    }

    /**
     * Sets the particle effect used to display chunk borders.
     *
     * @param effect the particle effect to use
     */
    public void setEffect(ParticleEffect effect) {
        this.effect = effect;
    }

    /**
     * Displays the configured particle effect at a given location
     * for a specific player.
     *
     * <p>
     * If no effect is set, {@link ParticleEffect#VILLAGER_HAPPY}
     * is used as the default.
     * </p>
     *
     * @param player the player who will see the effect
     * @param loc    the location to display the particle
     */
    private void displayEffect(final Player player, Location loc) {
        if (effect == null) {
            effect = ParticleEffect.VILLAGER_HAPPY;
        }
        effect.display(loc, 0f, 0f, 0f, 0f, 2, null, player);
    }

    /**
     * Stops the currently running chunk visualization task,
     * if one exists.
     */
    private void stopTask() {
        if (this.task == null) {
            return;
        }
        this.task.cancel();
        this.task = null;
    }
}
