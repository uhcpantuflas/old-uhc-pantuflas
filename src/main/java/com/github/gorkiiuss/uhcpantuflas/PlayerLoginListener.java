package com.github.gorkiiuss.uhcpantuflas;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class PlayerLoginListener implements Listener {
    private final String PLAYER_NO_TEAM_KICK_MSG = "You have to be on a team to participate!";
    @EventHandler
    public void onPlayerLogin(PlayerLoginEvent event) {
        if (GameplayManager.get().getGameMode() == UHCGameMode.MIN_ZERO_TEAMS) {
            String loggedPlayerName = event.getPlayer().getName();

            if (!TeamManager.get().isPlayerInATeam(loggedPlayerName))
                event.disallow(PlayerLoginEvent.Result.KICK_OTHER, PLAYER_NO_TEAM_KICK_MSG);
        }
    }
}
