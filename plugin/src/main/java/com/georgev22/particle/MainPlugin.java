package com.georgev22.particle;

import com.georgev22.particle.commands.ChunkCommand;
import com.georgev22.particle.scheduler.MinecraftScheduler;
import com.georgev22.particle.scheduler.impl.MinecraftBukkitScheduler;
import com.georgev22.particle.scheduler.impl.MinecraftFoliaScheduler;
import org.bukkit.plugin.java.JavaPlugin;

public class MainPlugin extends JavaPlugin {

    private MinecraftScheduler minecraftScheduler;
    private ChunkSeeManager chunkSeeManager;

    @Override
    public void onEnable() {
        this.minecraftScheduler = isFolia() ? new MinecraftFoliaScheduler(this) : new MinecraftBukkitScheduler(this);
        this.chunkSeeManager = new ChunkSeeManager(this);

        this.getCommand("chunksee").setExecutor(new ChunkCommand(this));
    }

    @Override
    public void onDisable() {
        this.minecraftScheduler.cancelTasks();
    }

    public MinecraftScheduler getMinecraftScheduler() {
        return minecraftScheduler;
    }

    public ChunkSeeManager getChunkSeeManager() {
        return chunkSeeManager;
    }

    /**
     * Checks if the current environment is {@code Paper} by attempting to load the {@code io.papermc.paper.threadedregions.RegionizedServer} class.
     *
     * @return {@code true} if the environment is {@code Folia} otherwise {@code false}
     */
    private static boolean isFolia() {
        try {
            Class.forName("io.papermc.paper.threadedregions.RegionizedServer");
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }
}
