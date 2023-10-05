package com.github.gorkiiuss.uhcpantuflas.teams;

import com.github.gorkiiuss.uhcpantuflas.teams.exceptions.UHCTeamSizeExceededException;
import com.github.gorkiiuss.uhcpantuflas.teams.exceptions.UnknownUHCTeamException;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.command.CommandSender;

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
    private boolean friendlyFire;

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

    /**
     * Set the maximum size for teams.
     *
     * @param teamsSize The maximum size for teams.
     */
    public void setTeamsSize(int teamsSize) {
        this.teamsSize = teamsSize;

        teams.values().stream().filter(team -> team.getSize() > teamsSize).forEach(team -> {
            // TODO: 04/10/2023 Do something with the leftover teammates
        });
    }

    /**
     * Set the friendly fire option for teams.
     *
     * @param friendlyFire True to enable friendly fire, false to disable it.
     */
    public void setFriendlyFire(boolean friendlyFire) {
        this.friendlyFire = friendlyFire;

        Server server = Bukkit.getServer();
        CommandSender sender = server.getConsoleSender();
        teams.keySet().forEach(teamName ->
                server.dispatchCommand(
                        sender,
                        "team modify " + teamName + " friendlyFire " + friendlyFire
                )
        );
    }

    /**
     * Create a new team with the specified name.
     *
     * @param teamName The name of the new team.
     */
    public void createTeam(String teamName) {
        this.teams.put(teamName, new UHCTeam());

        Server server = Bukkit.getServer();
        CommandSender sender = server.getConsoleSender();
        server.dispatchCommand(
                sender,
                "team add " + teamName
        );
        server.dispatchCommand(
                sender,
                "team modify " + teamName + " friendlyFire " + friendlyFire
        );
    }

    /**
     * Add members to an existing team.
     *
     * @param teamName     The name of the team.
     * @param teamMembers  An array of player names to add to the team.
     * @throws UHCTeamSizeExceededException If adding members exceeds the team's size limit.
     */
    public void addMembers(String teamName, String[] teamMembers) throws UHCTeamSizeExceededException {
        UHCTeam team = teams.get(teamName);
        team.addMembers(teamMembers);

        Server server = Bukkit.getServer();
        CommandSender sender = server.getConsoleSender();
        for (String teamMember : teamMembers) {
            server.dispatchCommand(
                    sender,
                    "team join " + teamName + " " + teamMember
            );
        }
    }

    /**
     * Get the maximum size for teams.
     *
     * @return The maximum size for teams.
     */
    public int getTeamsSize() {
        return teamsSize;
    }

    /**
     * Get an array of member names for a specified team.
     *
     * @param teamName The name of the team.
     * @return An array of member names.
     * @throws UnknownUHCTeamException If the team does not exist.
     */
    public String[] getMembers(String teamName) throws UnknownUHCTeamException {
        if (!teams.containsKey(teamName)) throw new UnknownUHCTeamException(teamName);
        return teams.get(teamName).getMembers();
    }

    /**
     * Get a set of team names.
     *
     * @return A set of team names.
     */
    public Set<String> getTeamNames() {
        return teams.keySet();
    }

    /**
     * Delete an existing team.
     *
     * @param teamName The name of the team to delete.
     * @throws UnknownUHCTeamException If the team does not exist.
     */
    public void deleteTeam(String teamName) throws UnknownUHCTeamException {
        if (!teams.containsKey(teamName)) throw new UnknownUHCTeamException(teamName);
        teams.remove(teamName);
        Server server = Bukkit.getServer();
        server.dispatchCommand(
                server.getConsoleSender(),
                "team remove " + teamName
        );
    }

    /**
     * Delete all teams.
     */
    public void wipe() {
        for (String teamName : teams.keySet()) {
            try {
                deleteTeam(teamName);
            } catch (UnknownUHCTeamException ignore) {

            }
        }
    }
}
