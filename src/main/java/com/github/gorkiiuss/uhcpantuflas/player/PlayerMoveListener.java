package com.github.gorkiiuss.uhcpantuflas.player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMoveListener implements Listener { // TODO: 14/10/2023 may have bad performance!!!
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        String playerName = event.getPlayer().getName();

        if (PlayerManager.get().isPlayerImmobilized(playerName))
            event.setCancelled(true);
    }
}
