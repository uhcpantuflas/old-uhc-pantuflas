package com.github.gorkiiuss.uhcpantuflas;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.command.CommandSender;

public class TitleManager {
    private static TitleManager instance;

    private Title joiningTitle;

    private TitleManager() {

    }

    public static synchronized TitleManager get() {
        if (instance == null) instance = new TitleManager();
        return instance;
    }

    /**
     * This replacement for the org.bukkit.entity.Player.sendTitle method enhances the text by allowing the user to
     * apply JSON formatting to it. To see what the function does refer to
     * <a href="https://hub.spigotmc.org/javadocs/bukkit/org/bukkit/entity/Player.html#sendTitle(java.lang.String,java.lang.String,int,int,int)">the Bukkit documentation</a>.
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

    public void sendTitle(String playerName, DefaultTitle title) {
        sendTitle(
                playerName,
                switch (title) {
                    case JOINING -> joiningTitle;
                }
        );
    }

    public void setJoiningTitle(Title joiningTitle) {
        this.joiningTitle = joiningTitle;
    }

    public enum DefaultTitle {
        JOINING
    }
}
