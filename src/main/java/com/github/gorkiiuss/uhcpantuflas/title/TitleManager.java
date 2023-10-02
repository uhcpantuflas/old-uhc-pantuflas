package com.github.gorkiiuss.uhcpantuflas.title;

import com.github.gorkiiuss.uhcpantuflas.text.MinecraftColor;
import com.github.gorkiiuss.uhcpantuflas.text.MinecraftTextBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.command.CommandSender;

/**
 * The TitleManager class provides functionality to manage and display titles with optional JSON formatting to players.
 * It acts as a replacement for the org.bukkit.entity.Player.sendTitle method with enhanced features.
 * @version 0.0.1-ALPHA.0
 * @since 02/10/2023-ALPHA-0
 */
public class TitleManager {
    private static TitleManager instance;

    private Title joiningTitle;

    private TitleManager() {
        // Private constructor to enforce singleton pattern
    }

    /**
     * Get the instance of the TitleManager.
     *
     * @return The TitleManager instance.
     */
    public static synchronized TitleManager get() {
        if (instance == null) instance = new TitleManager();
        return instance;
    }

    /**
     * Sends a title with optional JSON formatting to a player.
     * This method allows for enhanced text formatting using JSON.
     *
     * @param playerName The name of the player to send the title to.
     * @param title      The Title object containing the title, subtitle, and display parameters.
     */
    public void sendTitle(String playerName, Title title) {
        Server server = Bukkit.getServer();
        CommandSender sender = server.getConsoleSender();

        server.dispatchCommand(
                sender,
                "title " + playerName + " times " + title.getFadeIn() + " " + title.getStay() + " " +
                        title.getFadeOut()
        );
        server.dispatchCommand(
                sender,
                "title " + playerName + " subtitle " + title.getSubtitle()
        );
        server.dispatchCommand(
                sender,
                "title " + playerName + " title " + title.getTitle()
        );
    }

    /**
     * Sends a predefined built-in title to a player.
     *
     * @param playerName The name of the player to send the title to.
     * @param title      The BuiltInTitle enum specifying the default title to send.
     */
    public void sendTitle(String playerName, BuiltInTitle title) {
        sendTitle(
                playerName,
                switch (title) {
                    case JOINING -> joiningTitle;
                }
        );
    }

    /**
     * Sets the joining title with optional formatting.
     *
     * @param joiningTitle The Title object containing the joining title information.
     */
    public void setJoiningTitle(Title joiningTitle) {
        this.joiningTitle = formatAsJoining(joiningTitle);
    }

    private Title formatAsJoining(Title title) {
        String joiningTitle = new MinecraftTextBuilder()
                .addText("xxX ").strikethrough().obfuscated()
                .addText(title.getTitle()).bold()
                .addText(" Xxx").strikethrough().obfuscated()
                .colorAll(MinecraftColor.YELLOW).build();
        String joiningSubtitle = new MinecraftTextBuilder().addText(title.getSubtitle()).color(MinecraftColor.GRAY).build();

        return new Title(joiningTitle, joiningSubtitle, title.getFadeIn(), title.getStay(), title.getFadeOut());
    }

    /**
     * An enum containing predefined built-in titles.
     */
    public enum BuiltInTitle {
        JOINING
    }
}
