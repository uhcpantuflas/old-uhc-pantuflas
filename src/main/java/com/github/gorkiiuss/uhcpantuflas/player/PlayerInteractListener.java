package com.github.gorkiiuss.uhcpantuflas.player;

import com.github.gorkiiuss.uhcpantuflas.gameplay.GameState;
import com.github.gorkiiuss.uhcpantuflas.gameplay.GameplayManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerInteractListener implements Listener { // TODO: 17/10/2023 may still be inefficient
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
//        if (PlayerManager.get().isInteractor(event.getPlayer().getName()))
//            event.setCancelled(true);

        if (GameplayManager.get().getGameState() == GameState.BEGINNING)
            event.setCancelled(true);
    }
}
