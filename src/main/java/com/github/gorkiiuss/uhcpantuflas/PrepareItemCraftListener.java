package com.github.gorkiiuss.uhcpantuflas;

import com.github.gorkiiuss.uhcpantuflas.teams.TeamManager;
import org.bukkit.Material;
import org.bukkit.block.Banner;
import org.bukkit.block.banner.Pattern;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;

public class PrepareItemCraftListener implements Listener {
    @EventHandler
    public void onPrepareItemCraft(PrepareItemCraftEvent event) {
        ItemStack result = event.getInventory().getResult();
        if (result != null && result.getType() == Material.SHIELD) {
            BlockStateMeta meta = (BlockStateMeta) result.getItemMeta();
            assert meta != null;
            Banner banner = (Banner) meta.getBlockState();
            UHCBanner teamBanner = TeamManager.get().getTeam(event.getView().getPlayer().getName()).getBanner();
            banner.setBaseColor(teamBanner.getBaseColor());
            Pattern[] patterns = teamBanner.getPatterns();
            for (Pattern pattern: patterns) {
                banner.addPattern(pattern);
            }
            banner.update();
            meta.setBlockState(banner);
            result.setItemMeta(meta);
        }
    }
}
