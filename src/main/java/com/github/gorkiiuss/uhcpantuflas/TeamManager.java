package com.github.gorkiiuss.uhcpantuflas;

import org.bukkit.scoreboard.Team;

import java.util.ArrayList;

public class TeamManager {
    private static TeamManager instance;
    private final ArrayList<Team> teams = new ArrayList<>();

    private TeamManager() {

    }

    public static synchronized TeamManager get() {
        if (instance == null) instance = new TeamManager();
        return instance;
    }

    public boolean isPlayerInATeam(String playerName) {
        return teams.stream().anyMatch(team -> team.hasEntry(playerName));
    }
}
