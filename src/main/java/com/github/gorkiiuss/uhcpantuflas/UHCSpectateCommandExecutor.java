package com.github.gorkiiuss.uhcpantuflas;

import com.github.gorkiiuss.uhcpantuflas.player.PlayerManager;
import com.github.gorkiiuss.uhcpantuflas.title.TitleManager;
import com.github.gorkiiuss.uhcpantuflas.world.WorldManager;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class UHCSpectateCommandExecutor implements CommandExecutor {
    public static final String NAME = "uhc-spectate";

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length > 0)
            return false;

        if (!(sender instanceof Player))
            return false; // TODO: 18/10/2023 handle

        Player playerSender = (Player) sender;
        if (!PlayerManager.get().isPlayerDeath(playerSender.getName()))
            return false; // TODO: 18/10/2023 handle

        playerSender.setGameMode(GameMode.SPECTATOR);
        playerSender.teleport(WorldManager.get().getOverworld00());
        TimeManager.get().executeAFrameLater(() -> TitleManager.get().sendTitle(playerSender.getName(), TitleManager.BuiltInTitle.SPECTATOR, TitleManager.TitlePosition.SCREEN));

        return true;
    }
}
