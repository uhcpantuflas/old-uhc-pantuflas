package com.github.gorkiiuss.uhcpantuflas.player;

import com.github.gorkiiuss.uhcpantuflas.gameplay.GameState;
import com.github.gorkiiuss.uhcpantuflas.gameplay.GameplayManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeathListener implements Listener {
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        if (GameplayManager.get().getGameState() == GameState.BEGINNING) {
            event.setDeathMessage("Pathetic... " + event.getEntity().getName() + " died, and we haven't even started playing.");
        }
    }
}
