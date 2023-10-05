package com.github.gorkiiuss.uhcpantuflas.teams;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class UHCTeamCommandExecutor implements CommandExecutor {
    public static final String NAME = "uhc-team";
    public static final String ADD_OPTION = "add";
    public static final String SHOW_OPTION = "show";

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        // Check if the command has at least 2 arguments
        if (args.length < 2)
            return false;

        String option = args[0];
        String teamName = args[1];

        switch (option) {
            case ADD_OPTION -> {
                if (args.length < 3)
                    return false;

                // Get the team name and the team members
                @NotNull String[] teamMembers = Arrays.copyOfRange(args, 2, args.length);

                TeamManager.get().createTeam(teamName);
                try {
                    TeamManager.get().addMembers(teamName, teamMembers);
                } catch (UHCTeamError e) {
                    TeamManager.get().deleteTeam(teamName);
                    sender.sendMessage(e.getMessage());
                }

            }
            case SHOW_OPTION -> {
                try {
                    String[] members = TeamManager.get().getMembers(teamName);

                    StringBuilder msg = new StringBuilder(teamName + ":\n");
                    for (int i = 0; i < members.length; i++) {
                        msg.append(i + 1).append(". ").append(members[i]).append("\n");
                    }
                    sender.sendMessage(
                            msg.toString()
                    );
                } catch (UnknownUHCTeamException e) {
                    sender.sendMessage(e.getMessage());
                }
            }
        }

        return true;
    }
}
