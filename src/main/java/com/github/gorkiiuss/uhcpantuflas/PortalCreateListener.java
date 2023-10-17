package com.github.gorkiiuss.uhcpantuflas;

import com.github.gorkiiuss.uhcpantuflas.world.WorldManager;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.BlockState;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.PortalCreateEvent;

public class PortalCreateListener implements Listener {
    @EventHandler
    public void onPortalCreate(PortalCreateEvent event) {
        if (
                event.getWorld().getEnvironment() != World.Environment.NETHER ||
                event.getReason() != PortalCreateEvent.CreateReason.FIRE
        )
            return;

        int netherBorderRadius = WorldManager.get().getDiameter() / 16;
        if (
                // Detect if any block is outside the border in x direction
                 event.getBlocks().stream()
                    .map(BlockState::getLocation)
                    .map(Location::getBlockX)
                    .map(Math::abs).anyMatch(x -> netherBorderRadius <= x) ||
                // Detect if any block is outside the border in x direction
                event.getBlocks().stream()
                    .map(BlockState::getLocation)
                    .map(Location::getBlockZ)
                    .map(Math::abs).anyMatch(z -> netherBorderRadius <= z)
        ) {
            event.getWorld().playSound(event.getBlocks().get(0).getLocation(), Sound.BLOCK_FIRE_EXTINGUISH, 1, 0.7f);
            event.getBlocks().stream().map(BlockState::getLocation).forEach( location -> {
                event.getWorld().spawnParticle(Particle.CAMPFIRE_COSY_SMOKE, location, 50, 0.5, 0.5, 0.5, 0);
            });
            event.setCancelled(true);
        }
    }
}
