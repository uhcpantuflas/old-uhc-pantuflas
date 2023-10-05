package com.github.gorkiiuss.uhcpantuflas.teams;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * The UHCTeamTabCompleter class provides tab-completion suggestions for UHCTeam related commands.
 *
 * @version 0.0.1-ALPHA.0
 * @since 05/10/2023-ALPHA.0
 */
public class UHCTeamTabCompleter implements TabCompleter {
    /**
     * Provides tab-completion suggestions for UHCTeam related commands.
     *
     * @param sender   The command sender.
     * @param command  The executed command.
     * @param alias    The alias of the command.
     * @param args     The arguments provided by the sender.
     * @return A list of tab-completion suggestions based on the provided arguments.
     */
    @Nullable
    @Override
    public List<String> onTabComplete(
            @NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args
    ) {
        if (!alias.equals(UHCTeamCommandExecutor.NAME))
            return null;

        final ArrayList<String> completions = new ArrayList<>();
        switch (args.length) {
            case 1 -> {
                String partialOption = args[0];
                addCompletion(completions, UHCTeamCommandExecutor.ADD_OPTION, partialOption);
                addCompletion(completions, UHCTeamCommandExecutor.SHOW_OPTION, partialOption);
                addCompletion(completions, UHCTeamCommandExecutor.DELETE_OPTION, partialOption);
            }
            case 2 -> {
                String option = args[0];
                String partial = args[1];
                if (option.equals(UHCTeamCommandExecutor.SHOW_OPTION) || option.equals(UHCTeamCommandExecutor.DELETE_OPTION)) {
                    TeamManager.get().getTeamNames().forEach(teamName ->
                            addCompletion(completions, teamName, partial)
                    );
                }
            }
        }

        return completions;
    }

    private void addCompletion(ArrayList<String> completions, String completion, String partialCompletion) {
        if (completion.contains(partialCompletion) && !partialCompletion.equals(completion))
            completions.add(completion);
    }
}
