package com.github.gorkiiuss.uhcpantuflas.player;

import com.github.gorkiiuss.uhcpantuflas.TimeManager;
import com.github.gorkiiuss.uhcpantuflas.gameplay.GameState;
import com.github.gorkiiuss.uhcpantuflas.gameplay.GameplayManager;
import com.github.gorkiiuss.uhcpantuflas.title.TitleManager;
import com.github.gorkiiuss.uhcpantuflas.world.WorldManager;
import org.bukkit.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class PlayerRespawnListener implements Listener {
    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        if (event.getPlayer().getWorld().getEnvironment() == World.Environment.THE_END) {
            event.setRespawnLocation(new Location(Bukkit.getWorlds().get(2), 515, 101, 597));
        }

        if (GameplayManager.get().getGameState() == GameState.BEGINNING) {
            event.setRespawnLocation(WorldManager.get().getOverworld00());
            event.getPlayer().playSound(WorldManager.get().getOverworld00(), Sound.ENTITY_GOAT_SCREAMING_AMBIENT, 1, 0.7f);
            TimeManager.get().executeAFrameLater(() -> TitleManager.get().sendTitle(event.getPlayer().getName(), TitleManager.BuiltInTitle.BEGINNING_DEATH));
        } else if (GameplayManager.get().getGameState() == GameState.PLAYING) {
            TimeManager.get().executeAFrameLater(() -> event.getPlayer().performCommand("uhc-lobby"));
        }
    }
}
