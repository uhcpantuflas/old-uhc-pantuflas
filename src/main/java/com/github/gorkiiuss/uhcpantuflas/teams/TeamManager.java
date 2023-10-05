package com.github.gorkiiuss.uhcpantuflas.teams;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * The TeamManager class is responsible for managing teams in the plugin.
 * It provides methods to interact with teams and check if a player is in any team.
 *
 * @version 0.0.1-ALPHA.0
 * @since 04/10/2023-ALPHA.0
 */
public class TeamManager {
    private static TeamManager instance;
    private final Map<String, UHCTeam> teams = new HashMap<>();
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
        return teams.values().stream().anyMatch(team -> team.hasMember(playerName));
    }

    public void setTeamsSize(int teamsSize) {
        this.teamsSize = teamsSize;

        teams.values().stream().filter(team -> team.getSize() > teamsSize).forEach(team -> {
            // TODO: 04/10/2023 Do something with the leftover teammates
        });

        System.out.println("Team size set to " + teamsSize);
    }

    public void createTeam(String teamName) {
        this.teams.put(teamName, new UHCTeam());
    }

    public void addMembers(String teamName, String[] teamMembers) throws UHCTeamError {
        UHCTeam team = teams.get(teamName);
        team.addMembers(teamMembers);
    }

    public int getTeamsSize() {
        return teamsSize;
    }

    public String[] getMembers(String teamName) throws UnknownUHCTeamException {
        if (!teams.containsKey(teamName)) throw new UnknownUHCTeamException(/* TODO 05/10/2023 teamName */);
        return teams.get(teamName).getMembers();
    }

    public Set<String> getTeamNames() {
        return teams.keySet();
    }

    public void deleteTeam(String teamName) {
        teams.remove(teamName);
    }
}
