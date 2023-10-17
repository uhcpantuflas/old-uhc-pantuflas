package com.github.gorkiiuss.uhcpantuflas.player;

import com.github.gorkiiuss.uhcpantuflas.gameplay.GameState;
import com.github.gorkiiuss.uhcpantuflas.gameplay.GameplayManager;
import com.github.gorkiiuss.uhcpantuflas.title.TitleManager;
import com.github.gorkiiuss.uhcpantuflas.world.WorldManager;
import org.bukkit.GameMode;
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
     * Handles the player join event and displays a title to the joining player.
     *
     * @param event The PlayerJoinEvent instance.
     */
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (GameplayManager.get().getGameState() == GameState.BEGINNING) {
            Player joinedPlayer = event.getPlayer();

            // Show title
            TitleManager.get().sendTitle(
                    joinedPlayer.getName(),
                    TitleManager.BuiltInTitle.JOINING
            );

            // Register in PlayerManager
            PlayerManager.get().registerPlayer(new UHCPlayer(joinedPlayer.getName()));

            //TP to 0, 0
            joinedPlayer.teleport(WorldManager.get().getOverworld00());

            //Set game mode to Adventure
            joinedPlayer.setGameMode(GameMode.ADVENTURE);
        }
    }
}
