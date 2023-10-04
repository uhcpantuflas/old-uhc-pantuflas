package com.github.gorkiiuss.uhcpantuflas.config;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * The UHCConfigTabCompleter class provides tab-completion suggestions for the /uhc-config command.
 * It suggests valid sections and options based on the user's input.
 *
 * @version 0.0.1-ALPHA.0
 * @since 03/10/2023-ALPHA.0
 */
public class UHCConfigTabCompleter implements TabCompleter {
    /**
     * Provides tab-completion suggestions for the /uhc-config command.
     *
     * @param sender The command sender.
     * @param command The executed command.
     * @param alias The alias of the command.
     * @param args The command arguments.
     * @return A list of tab-completion suggestions.
     */
    @Nullable
    @Override
    public List<String> onTabComplete(
            @NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args
    ) {
        if (!alias.equals(UHCConfigCommandExecutor.NAME))
            return null;

        if (args.length >= 3)
            return null;

        final ArrayList<String> completions = new ArrayList<>();
        switch (args.length) {
            case 1 -> {
                String partialSection = args[0];
                addCompletion(completions, ConfigurationManager.JOINING_TITLE_KEY, partialSection);
                addCompletion(completions, ConfigurationManager.GAMEPLAY_KEY, partialSection);
            }
            case 2 -> {
                String partialOption = args[1];

                switch (args[0]) {
                    case ConfigurationManager.JOINING_TITLE_KEY -> {
                        addCompletion(completions, ConfigurationManager.TITLE_KEY, partialOption);
                        addCompletion(completions, ConfigurationManager.SUBTITLE_KEY, partialOption);
                        addCompletion(completions, ConfigurationManager.FADE_IN_KEY, partialOption);
                        addCompletion(completions, ConfigurationManager.STAY_KEY, partialOption);
                        addCompletion(completions, ConfigurationManager.FADE_OUT_KEY, partialOption);
                    }
                    case ConfigurationManager.GAMEPLAY_KEY -> addCompletion(completions, ConfigurationManager.GAME_MODE_KEY, partialOption);
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
