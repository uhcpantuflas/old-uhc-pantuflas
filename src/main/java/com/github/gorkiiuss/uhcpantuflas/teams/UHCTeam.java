package com.github.gorkiiuss.uhcpantuflas.teams;

import com.github.gorkiiuss.uhcpantuflas.teams.exceptions.UHCTeamSizeExceededException;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * The UHCTeam class represents a team in the UHC plugin.
 * It tracks the members of the team and provides methods for managing them.
 *
 * @version 0.0.1-ALPHA.0
 * @since 05/10/2023-ALPHA.0
 */
public final class UHCTeam {
    private final Set<String> members = new HashSet<>();

    /**
     * Constructs a new UHCTeam instance.
     * This constructor initializes an empty team.
     */
    public UHCTeam() {

    }

    /**
     * Check if a player is a member of this team.
     *
     * @param playerName The name of the player to check.
     * @return True if the player is a member of this team, otherwise false.
     */
    public boolean hasMember(String playerName) {
        return members.contains(playerName);
    }

    /**
     * Get the current size of the team.
     *
     * @return The number of members in the team.
     */
    public int getSize() {
        return members.size();
    }

    /**
     * Add members to the team.
     *
     * @param teamMembers An array of player names to add to the team.
     * @throws UHCTeamSizeExceededException If adding members exceeds the team's size limit.
     */
    public void addMembers(String[] teamMembers) throws UHCTeamSizeExceededException {
        int alreadyTeamMembers = (int) Arrays.stream(teamMembers).filter(members::contains).count();
        if (teamMembers.length + members.size() - alreadyTeamMembers > TeamManager.get().getTeamsSize())
            throw new UHCTeamSizeExceededException();

        members.addAll(List.of(teamMembers));
    }

    /**
     * Get an array of member names in the team.
     *
     * @return An array of member names.
     */
    public String[] getMembers() {
        return members.toArray(new String[0]);
    }
}
