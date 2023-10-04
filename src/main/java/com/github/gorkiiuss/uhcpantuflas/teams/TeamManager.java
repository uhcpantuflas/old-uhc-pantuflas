package com.github.gorkiiuss.uhcpantuflas.teams;

import org.bukkit.scoreboard.Team;

import java.util.ArrayList;

/**
 * The TeamManager class is responsible for managing teams in the plugin.
 * It provides methods to interact with teams and check if a player is in any team.
 *
 * @version 0.0.1-ALPHA.0
 * @since 04/10/2023-ALPHA.0
 */
public class TeamManager {
    private static TeamManager instance;
    private final ArrayList<Team> teams = new ArrayList<>();
    private int teamsSize;

    private TeamManager() {
        // Private constructor to enforce singleton pattern
    }

    /**
     * Get the instance of the TeamManager.
     *
     * @return The TeamManager instance.
     */
    public static synchronized TeamManager get() {
        if (instance == null) instance = new TeamManager();
        return instance;
    }

    /**
     * Check if a player is in any team.
     *
     * @param playerName The name of the player to check.
     * @return True if the player is in any team, otherwise false.
     */
    public boolean isPlayerInATeam(String playerName) {
        return teams.stream().anyMatch(team -> team.hasEntry(playerName));
    }

    public void setTeamsSize(int teamsSize) {
        this.teamsSize = teamsSize;

        teams.stream().filter(team -> team.getSize() > teamsSize).forEach(team -> {
            // TODO: 04/10/2023 Do something with the leftover teammates
        });

        System.out.println("Team size set to " + teamsSize);
    }
}
