package com.github.gorkiiuss.uhcpantuflas;

import com.github.gorkiiuss.uhcpantuflas.player.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class UHCLobbyCommandExecutor implements CommandExecutor {
    public static final String NAME = "uhc-lobby";

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length > 0)
            return false;

        if (!(sender instanceof Player))
            return false; // TODO: 18/10/2023 handle

        Player playerSender = (Player) sender;
        if (!PlayerManager.get().isPlayerDeath(playerSender.getName()))
            return false; // TODO: 18/10/2023 handle

        playerSender.setGameMode(GameMode.ADVENTURE);
        playerSender.teleport(new Location(Bukkit.getWorlds().get(2), 500, 101, 500));

        return true;
    }
}
