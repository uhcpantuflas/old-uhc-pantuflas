package com.github.gorkiiuss.uhcpantuflas;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        // Show title
        Player joinedPlayer = event.getPlayer();

        TitleManager.get().sendTitle(
                joinedPlayer.getName(),
                TitleManager.DefaultTitle.JOINING
        );
    }
}
