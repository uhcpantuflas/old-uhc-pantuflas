package com.github.gorkiiuss.uhcpantuflas;

import org.bukkit.configuration.file.FileConfiguration;

public class ConfigurationManager {
    private static ConfigurationManager instance;

    private ConfigurationManager() {

    }

    public static synchronized ConfigurationManager get() {
        if (instance == null) instance = new ConfigurationManager();
        return instance;
    }

    public void init(UHCPantuflas plugin) {
        plugin.saveDefaultConfig();
        FileConfiguration config = plugin.getConfig();

        // Joining title
        Title joiningTitle = new Title(
                config.getString("joining-title.title"),
                config.getString("joining-title.subtitle"),
                config.getInt("joining-title.fade-in"),
                config.getInt("joining-title.stay"),
                config.getInt("joining-title.fade-out")
        ).formatAsJoining();

        TitleManager.get().setJoiningTitle(joiningTitle);
    }
}
