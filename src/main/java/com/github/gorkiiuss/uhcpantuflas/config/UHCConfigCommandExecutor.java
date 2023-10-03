package com.github.gorkiiuss.uhcpantuflas.config;

import com.github.gorkiiuss.uhcpantuflas.config.exceptions.UHCConfigException;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

/**
 * The UHCConfigCommandExecutor class is responsible for handling the /uhc-config command.
 * It allows users to configure UHC Pantuflas settings via commands.
 *
 * @version 0.0.1-ALPHA.0
 * @since 03/10/2023-ALPHA.0
 */
public class UHCConfigCommandExecutor implements CommandExecutor {
    /** The name of the /uhc-config command. */
    public static final String NAME = "uhc-config";

    /**
     * Called when the /uhc-config command is executed.
     *
     * @param sender   The command sender.
     * @param command  The executed command.
     * @param s        The label of the command.
     * @param args     The command arguments.
     * @return True if the command was executed successfully, false otherwise.
     */
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, String[] args) {
        // Check if the command has at least 3 arguments
        if (args.length < 3)
            return false;

        // Get the section, option, and value from the command arguments
        String section = args[0];
        String option = args[1];
        StringBuilder value = new StringBuilder(args[2]);
        for (int i = 3; i < args.length; i++) {
            value.append(" ").append(args[i]);
        }

        try {
            // Set the specified configuration option with the given value
            ConfigurationManager.get().set(section, option, value.toString());
        } catch (UHCConfigException e) {
            sender.sendMessage(e.getMessage());
            return false;
        }

        return true;
    }
}
