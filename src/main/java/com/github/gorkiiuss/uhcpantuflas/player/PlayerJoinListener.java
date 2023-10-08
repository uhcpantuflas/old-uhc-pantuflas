package com.github.gorkiiuss.uhcpantuflas.player;

import com.github.gorkiiuss.uhcpantuflas.gameplay.GameState;
import com.github.gorkiiuss.uhcpantuflas.gameplay.GameplayManager;
import com.github.gorkiiuss.uhcpantuflas.title.TitleManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * The PlayerJoinListener class is responsible for handling player join events.
 * It shows a title to players when they join the server.
 * @version 0.0.1-ALPHA.0
 * @since 02/10/2023-ALPHA-0
 */
public class PlayerJoinListener implements Listener {
    /**
     * Handles the player join event.
     *
     * @param event The PlayerJoinEvent instance.
     */
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player joinedPlayer = event.getPlayer();
        PlayerManager.get().registerPlayer(new UHCPlayer(joinedPlayer.getName()));

        if (GameplayManager.get().getGameState() == GameState.BEGINNING) {
            // Show title

            TitleManager.get().sendTitle(
                    joinedPlayer.getName(),
                    TitleManager.BuiltInTitle.JOINING
            );
        }
    }
}
