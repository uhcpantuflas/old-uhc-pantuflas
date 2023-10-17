package com.github.gorkiiuss.uhcpantuflas;

import com.github.gorkiiuss.uhcpantuflas.gameplay.GameState;
import com.github.gorkiiuss.uhcpantuflas.gameplay.GameplayManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class EntityDamageByEntityListener implements Listener {
    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player) {
            if (GameplayManager.get().getGameState() == GameState.BEGINNING) { // TODO: 17/10/2023 may still be inefficient
//            String damager = event.getDamager().getName();
//            if (!PlayerManager.get().isPlayerAttacker(damager)) {
                event.setCancelled(true);
            } else if (event.getEntity() instanceof Player && !GameplayManager.get().isPvp()) {
                event.setCancelled(true);
            }
        }
    }
}
