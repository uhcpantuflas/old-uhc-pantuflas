package com.github.gorkiiuss.uhcpantuflas.teams;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class UHCTeamTabCompleter implements TabCompleter {
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
            }
            case 2 -> {
                String option = args[0];
                String partial = args[1];
                if (option.equals(UHCTeamCommandExecutor.SHOW_OPTION)) {
                    TeamManager.get().getTeamNames().forEach(teamName ->
                            addCompletion(completions, teamName, partial)
                    );
                }
            }
        }

        return completions;
    }

    /**
     * Adds a completion suggestion to the list if it matches the partial completion input.
     *
     * @param completions The list of completions to add to.
     * @param completion The full completion option.
     * @param partialCompletion The partial completion input.
     */
    private void addCompletion(ArrayList<String> completions, String completion, String partialCompletion) {
        if (completion.contains(partialCompletion) && !partialCompletion.equals(completion))
            completions.add(completion);
    }
}
