package com.github.gorkiiuss.uhcpantuflas.gameplay;

import com.github.gorkiiuss.uhcpantuflas.player.PlayerManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class UHCStartCommandExecutor implements CommandExecutor {
    public static final String NAME = "uhc-start";

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (GameplayManager.get().getGameState() != GameState.BEGINNING) return false; // TODO: 14/10/2023 handle exception

        // Immobilize players
        PlayerManager.get().immobilizeAll();

        GameplayManager.get().setGameState(GameState.PLAYING);
        return true;
    }
}
