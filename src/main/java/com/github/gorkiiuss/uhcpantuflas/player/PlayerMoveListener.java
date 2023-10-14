package com.github.gorkiiuss.uhcpantuflas.player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMoveListener implements Listener {
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        String playerName = event.getPlayer().getName();

        System.out.println("Player " + playerName + " is moving");
        if (PlayerManager.get().isPlayerImmobilized(playerName))
            event.setCancelled(true);
    }
}
