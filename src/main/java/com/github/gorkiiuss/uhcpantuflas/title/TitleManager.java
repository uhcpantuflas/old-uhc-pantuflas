package com.github.gorkiiuss.uhcpantuflas.title;

import com.github.gorkiiuss.uhcpantuflas.utils.text.MinecraftColor;
import com.github.gorkiiuss.uhcpantuflas.utils.text.MinecraftTextBuilder;
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
    private final Title startingTitle;
    private final Title shrinkingTitle;
    private final Title shrinkWarningTitle;
    private final Title pvpTitle;
    private final Title pvpWarningTitle;
    private final Title beginningDeathTitle;
    private final Title playerDeathArgTitle;
    private final Title teamDeathArgTitle;
    private final Title spectatorTitle;
    private final Title winningArgTitle;
    private final Title chunkGenerationTitle;

    private TitleManager() {
        // Private constructor to enforce singleton pattern
        startingTitle  = formatAsWarning(new Title(
                "Starting the game...",
                "May the last player win...",
                20,
                100,
                20
        )); // TODO: 14/10/2023 make configurable

        shrinkingTitle = formatAsDanger(new Title(
                "The world is shrinking!",
                "",
                -1,
                -1,
                -1
        )); // TODO: 15/10/2023 make configurable

        shrinkWarningTitle = formatAsWarning(new Title(
                "The world will start shrinking in 5 min...",
                "",
                -1,
                -1,
                -1
        )); // TODO: 15/10/2023 make configurable
        pvpTitle = formatAsDanger(new Title(
                "The PVP is activated!",
                "",
                -1,
                -1,
                -1
        )); // TODO: 16/10/2023 make configurable
        pvpWarningTitle = formatAsWarning(new Title(
                "The PVP will be activated in 5 min...",
                "",
                -1,
                -1,
                -1
        )); // TODO: 16/10/2023 make configurable
        beginningDeathTitle = formatAsDanger(new Title(
                "Pathetic...",
                "You're dead and we haven't even started playing",
                20,
                100,
                20
        ));
        playerDeathArgTitle = new Title(
                "Player $1 died",
                "",
                -1,
                -1,
                -1
        );
        teamDeathArgTitle = new Title(
                "Team $1 disappeared",
                "",
                -1,
                -1,
                -1
        );
        spectatorTitle = formatAsWarning(new Title(
                "You are spectating",
                "Press 1 for tp-ing to players",
                20,
                100,
                20
        ));
        winningArgTitle = new Title(
                "Team $1 won!",
                "Congratulations",
                20,
                100,
                20
        );
        chunkGenerationTitle = formatAsWarning(new Title(
                "Creating world...",
                "Waiting till pre-generation finishes",
                20,
                100,
                20
        ));
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
     * Sends a title with optional JSON formatting to all players.
     * This method allows for enhanced text formatting using JSON.
     *
     * @param title      The Title object containing the title, subtitle, and display parameters.
     */
    public void sendTitle(Title title) {
        Server server = Bukkit.getServer();
        CommandSender sender = server.getConsoleSender();

        server.dispatchCommand(
                sender,
                "title @a times " + title.getFadeIn() + " " + title.getStay() + " " +
                        title.getFadeOut()
        );
        server.dispatchCommand(
                sender,
                "title @a subtitle " + title.getSubtitle()
        );
        server.dispatchCommand(
                sender,
                "title @a title " + title.getTitle()
        );
    }

    /**
     * Sends a title with optional JSON formatting to all players in the specified position.
     * This method allows for enhanced text formatting using JSON.
     *
     * @param title      The Title object containing the title, subtitle, and display parameters.
     */
    public void sendTitle(Title title, TitlePosition position) {
        switch (position) {
            case SCREEN -> sendTitle(title);
            case HOT_BAR -> {
                Server server = Bukkit.getServer();
                server.dispatchCommand(
                        server.getConsoleSender(),
                        "title @a actionbar " + title.getTitle()
                );
            }
        }
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
                    case STARTING -> startingTitle;
                    case SHRINKING -> shrinkingTitle;
                    case SHRINK_WARNING -> shrinkWarningTitle;
                    case PVP -> pvpTitle;
                    case BEGINNING_DEATH -> beginningDeathTitle;
                    case SPECTATOR -> spectatorTitle;
                    case CHUNK_GENERATION -> chunkGenerationTitle;
                    case PVP_WARNING -> pvpWarningTitle;
                }
        );
    }

    /**
     * Sends a predefined built-in title to all players.
     *
     * @param title      The BuiltInTitle enum specifying the default title to send.
     */
    public void sendTitle(BuiltInTitle title) {
        sendTitle(
                switch (title) {
                    case JOINING -> joiningTitle;
                    case STARTING -> startingTitle;
                    case SHRINKING -> shrinkingTitle;
                    case SHRINK_WARNING -> shrinkWarningTitle;
                    case PVP -> pvpTitle;
                    case BEGINNING_DEATH -> beginningDeathTitle;
                    case SPECTATOR -> spectatorTitle;
                    case CHUNK_GENERATION -> chunkGenerationTitle;
                    case PVP_WARNING -> pvpWarningTitle;
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

    private Title formatAsDanger(Title title) {
        String startingTitle = new MinecraftTextBuilder()
                .addText(title.getTitle()).bold().color(MinecraftColor.RED)
                .build();

        String startingSubtitle = new MinecraftTextBuilder().addText(title.getSubtitle()).color(MinecraftColor.GRAY).build();

        return new Title(startingTitle, startingSubtitle, title.getFadeIn(), title.getStay(), title.getFadeOut());
    }

    private Title formatAsWarning(Title title) {
        String startingTitle = new MinecraftTextBuilder()
                .addText(title.getTitle()).bold().color(MinecraftColor.YELLOW)
                .build();

        String startingSubtitle = new MinecraftTextBuilder().addText(title.getSubtitle()).color(MinecraftColor.GRAY).build();

        return new Title(startingTitle, startingSubtitle, title.getFadeIn(), title.getStay(), title.getFadeOut());
    }

    public void sendTitle(BuiltInTitle builtInTitle, TitlePosition titlePosition) {
        sendTitle(switch (builtInTitle) {
            case SHRINK_WARNING -> shrinkWarningTitle;
            case JOINING -> joiningTitle;
            case STARTING -> startingTitle;
            case SHRINKING -> shrinkingTitle;
            case PVP -> pvpTitle;
            case BEGINNING_DEATH -> beginningDeathTitle;
            case SPECTATOR -> spectatorTitle;
            case CHUNK_GENERATION -> chunkGenerationTitle;
            case PVP_WARNING -> pvpWarningTitle;
        }, titlePosition);
    }

    public void sendTitle(BuiltInArgTitle builtInArgTitle, TitlePosition position, String... args) {
        sendTitle(switch (builtInArgTitle) {
            case TEAM_DEATH -> getTeamDeathTitle(args);
            case WINNING -> getWinningTitle(args);
            case PLAYER_DEATH -> getPlayerDeathTitle(args);
        }, position);
    }

    private Title getWinningTitle(String... args) {
        return formatAsWinning(winningArgTitle, args);
    }

    private Title formatAsWinning(Title title, String... args) {
        String titleWithoutFormat = title.getTitle();
        for (int i = 0; i < args.length; i++) {
            titleWithoutFormat = titleWithoutFormat.replace("$" + (i + 1), args[i]);
        }
        String winningTitle = new MinecraftTextBuilder()
                .addText(titleWithoutFormat)
                .color(MinecraftColor.YELLOW)
                .bold()
                .build();
        String winningSubtitle = new MinecraftTextBuilder()
                .addText(title.getSubtitle())
                .color(MinecraftColor.YELLOW)
                .bold()
                .build();

        return new Title(winningTitle, winningSubtitle, title.getFadeIn(), title.getStay(), title.getFadeOut());
    }

    private Title getTeamDeathTitle(String... args) {
        return formatAsDeath(teamDeathArgTitle, args);
    }

    private Title getPlayerDeathTitle(String... args) {
        return formatAsDeath(playerDeathArgTitle, args);
    }

    private Title formatAsDeath(Title title, String[] args) {
        String textWithoutFormat = title.getTitle();
        for (int i = 0; i < args.length; i++) {
            textWithoutFormat = textWithoutFormat.replace("$" + (i + 1), args[i]);
        }
        String deathTitle = new MinecraftTextBuilder()
                .addText(textWithoutFormat)
                .bold()
                .color(MinecraftColor.RED)
                .build();
        return new Title(deathTitle, title.getSubtitle(), title.getFadeIn(), title.getStay(), title.getFadeOut());
    }

    public void sendTitle(String name, Title title, TitlePosition position) {
        if (position == TitlePosition.SCREEN) {
            sendTitle(name, title);
        } else {
            Bukkit.getServer().dispatchCommand(
                    Bukkit.getServer().getConsoleSender(),
                    "title " + name + " actionbar " + title.getTitle()
            );
        }
    }

    public void sendTitle(String name, BuiltInTitle builtInTitle, TitlePosition titlePosition) {
        sendTitle(name, switch (builtInTitle) {
            case JOINING -> joiningTitle;
            case STARTING -> startingTitle;
            case SHRINKING -> shrinkingTitle;
            case SHRINK_WARNING -> shrinkWarningTitle;
            case PVP -> pvpTitle;
            case BEGINNING_DEATH -> beginningDeathTitle;
            case SPECTATOR -> spectatorTitle;
            case CHUNK_GENERATION -> chunkGenerationTitle;
            case PVP_WARNING -> pvpWarningTitle;
        }, titlePosition);
    }

    /**
     * An enum containing predefined built-in titles.
     */
    public enum BuiltInTitle {
        JOINING,
        STARTING,
        SHRINKING, SHRINK_WARNING,
        PVP, BEGINNING_DEATH, SPECTATOR, CHUNK_GENERATION, PVP_WARNING
    }

    public enum TitlePosition {
        SCREEN,
        HOT_BAR
    }

    public enum BuiltInArgTitle {
        TEAM_DEATH, WINNING, PLAYER_DEATH
    }
}
