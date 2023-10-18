package com.github.gorkiiuss.uhcpantuflas.gameplay;

import com.github.gorkiiuss.uhcpantuflas.TimeManager;
import com.github.gorkiiuss.uhcpantuflas.player.PlayerManager;
import com.github.gorkiiuss.uhcpantuflas.teams.TeamManager;
import com.github.gorkiiuss.uhcpantuflas.title.TitleManager;
import com.github.gorkiiuss.uhcpantuflas.world.WorldManager;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class UHCStartCommandExecutor implements CommandExecutor {
    public static final String NAME = "uhc-start";

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (GameplayManager.get().getGameState() != GameState.BEGINNING) return false; // TODO: 14/10/2023 handle exception

        // Delete bad teams
        TeamManager.get().deleteBadTeams();

        if (TeamManager.get().count() < 1) // TODO: 18/10/2023 change to 2
            return false;

        // Immobilize players
        PlayerManager.get().setImmobilized(true);

        // Set world size
        WorldManager.get().updateDiameter();
        WorldManager.get().setWorldBorder(WorldManager.get().getDiameter(), 0);

        // Pre-generate chunks
        WorldManager.get().preGenerateChunks();

        // Delete platform
        WorldManager.get().deletePlatform();

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

        // Change game mode
        PlayerManager.get().changeGameMode(GameMode.SURVIVAL);

        // Deactivate PVP
        GameplayManager.get().setPVP(false);

        // Activate daylight cycle
        WorldManager.get().setDaylightCycle(true);

        // Set timers
        TimeManager.get().initTimers();

        GameplayManager.get().setGameState(GameState.PLAYING);
        return true;
    }
}
