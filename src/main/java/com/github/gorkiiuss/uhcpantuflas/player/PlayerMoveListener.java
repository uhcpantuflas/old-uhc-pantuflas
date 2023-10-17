package com.github.gorkiiuss.uhcpantuflas.player;

import com.github.gorkiiuss.uhcpantuflas.gameplay.GameState;
import com.github.gorkiiuss.uhcpantuflas.gameplay.GameplayManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMoveListener implements Listener { // TODO: 17/10/2023 may have still bad performance!!!
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        if (GameplayManager.get().getGameState() == GameState.BEGINNING &&
                PlayerManager.get().isPlayerImmobilized(event.getPlayer().getName()))
            event.setCancelled(true);
    }
}
