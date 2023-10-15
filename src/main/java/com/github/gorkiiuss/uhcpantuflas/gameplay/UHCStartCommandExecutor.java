package com.github.gorkiiuss.uhcpantuflas.gameplay;

import com.github.gorkiiuss.uhcpantuflas.TimeManager;
import com.github.gorkiiuss.uhcpantuflas.player.PlayerManager;
import com.github.gorkiiuss.uhcpantuflas.teams.TeamManager;
import com.github.gorkiiuss.uhcpantuflas.title.TitleManager;
import com.github.gorkiiuss.uhcpantuflas.world.WorldManager;
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
        PlayerManager.get().setImmobilized(true);

        // Set world size
        WorldManager.get().updateDiameter();
        WorldManager.get().setWorldBorder(WorldManager.get().getDiameter(), 0);

        // TP players
        TeamManager.get().tpToInitialPositions();

        // Show starting title
        TitleManager.get().sendTitle(TitleManager.BuiltInTitle.STARTING);

        // Reset player
        PlayerManager.get().resetAll();

        // Mobilize players
        PlayerManager.get().setImmobilized(false);

        // Give slow-falling
        PlayerManager.get().slowFallingAll();

        // Set timers
        TimeManager.get().initTimers();

        GameplayManager.get().setGameState(GameState.PLAYING);
        return true;
    }
}
