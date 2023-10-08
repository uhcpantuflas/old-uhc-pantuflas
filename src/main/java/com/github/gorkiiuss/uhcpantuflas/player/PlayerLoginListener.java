package com.github.gorkiiuss.uhcpantuflas.player;

import com.github.gorkiiuss.uhcpantuflas.gameplay.GameState;
import com.github.gorkiiuss.uhcpantuflas.teams.TeamManager;
import com.github.gorkiiuss.uhcpantuflas.gameplay.GameplayManager;
import com.github.gorkiiuss.uhcpantuflas.gameplay.UHCGameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

/**
 * The PlayerLoginListener class is responsible for handling player login events.
 *
 * @version 0.0.1-ALPHA.0
 * @since 04/10/2023-ALPHA.0
 */
public class PlayerLoginListener implements Listener {
    private final String PLAYER_NO_TEAM_KICK_MSG = "You have to be on a team to participate!";

    /**
     * Handles player login events and prevents players from logging in if they are not on a team in the MIN_ZERO_TEAMS game mode.
     *
     * @param event The PlayerLoginEvent to handle.
     */
    @EventHandler
    public void onPlayerLogin(PlayerLoginEvent event) {
        if (GameplayManager.get().getGameState() == GameState.BEGINNING) {
            if (GameplayManager.get().getGameMode() == UHCGameMode.MIN_ZERO_TEAMS) {
                String loggedPlayerName = event.getPlayer().getName();

                if (!TeamManager.get().isPlayerInATeam(loggedPlayerName))
                    event.disallow(PlayerLoginEvent.Result.KICK_OTHER, PLAYER_NO_TEAM_KICK_MSG);
            }
        }
    }
}
