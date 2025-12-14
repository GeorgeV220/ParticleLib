package com.georgev22.particle.commands;

import com.georgev22.particle.MainPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ChunkCommand implements CommandExecutor {

    private final MainPlugin mainPlugin;

    public ChunkCommand(MainPlugin mainPlugin) {
        this.mainPlugin = mainPlugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
        if (sender instanceof Player player) {
            if (this.mainPlugin.getChunkSeeManager().isInside(player)) {
                this.mainPlugin.getChunkSeeManager().removePlayer(player);
                sender.sendMessage("ChunkSee disabled");
            } else {
                this.mainPlugin.getChunkSeeManager().addPlayer(player);
                sender.sendMessage("ChunkSee enabled");
            }
            return true;
        }
        sender.sendMessage("Only players can use this command.");
        return true;
    }
}
