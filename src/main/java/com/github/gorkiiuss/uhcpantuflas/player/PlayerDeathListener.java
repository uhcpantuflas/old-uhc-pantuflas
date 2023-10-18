package com.github.gorkiiuss.uhcpantuflas.player;

import com.github.gorkiiuss.uhcpantuflas.TimeManager;
import com.github.gorkiiuss.uhcpantuflas.gameplay.GameState;
import com.github.gorkiiuss.uhcpantuflas.gameplay.GameplayManager;
import com.github.gorkiiuss.uhcpantuflas.teams.TeamManager;
import com.github.gorkiiuss.uhcpantuflas.title.TitleManager;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.List;

public class PlayerDeathListener implements Listener {
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        if (event.getEntity().getWorld().getEnvironment() == World.Environment.THE_END)
            event.setDeathMessage("");
        else if (GameplayManager.get().getGameState() == GameState.BEGINNING) {
            event.setDeathMessage("Pathetic... " + event.getEntity().getName() + " died, and we haven't even started playing.");
        } else if (GameplayManager.get().getGameState() == GameState.PLAYING) {
            PlayerManager.get().playSound(Sound.ENTITY_LIGHTNING_BOLT_THUNDER, 1, 0.8f);
            TitleManager.get().sendTitle(TitleManager.BuiltInArgTitle.PLAYER_DEATH, TitleManager.TitlePosition.HOT_BAR, event.getEntity().getName());
            UHCPlayer deletedPlayer = TeamManager.get().deleteMemberFromTeam(event.getEntity().getName());
            List<String> deletedTeamNames = TeamManager.get().updateTeams();
            deletedTeamNames.forEach( deletedTeamName -> {
                PlayerManager.get().playSound(Sound.BLOCK_ANVIL_LAND, 1, 0.5f);
                TitleManager.get().sendTitle(TitleManager.BuiltInArgTitle.TEAM_DEATH, TitleManager.TitlePosition.HOT_BAR, deletedTeamName);
            });

            PlayerManager.get().died(deletedPlayer);

            if (TeamManager.get().count() == 1) {
                TeamManager.get().setWinner();
                PlayerManager.get().tpToLobby();
                PlayerManager.get().setGameMode(GameMode.ADVENTURE);
                TimeManager.get().executeAFrameLater(() -> TitleManager.get().sendTitle(TitleManager.BuiltInArgTitle.WINNING, TitleManager.TitlePosition.SCREEN, TeamManager.get().getWinnerName()));
            }
        }
    }
}
