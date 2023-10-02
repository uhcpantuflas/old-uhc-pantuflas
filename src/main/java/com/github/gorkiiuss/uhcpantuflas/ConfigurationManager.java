package com.github.gorkiiuss.uhcpantuflas;

import com.github.gorkiiuss.uhcpantuflas.title.Title;
import com.github.gorkiiuss.uhcpantuflas.title.TitleManager;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.Objects;

/**
 * The ConfigurationManager class is responsible for managing the plugin's configuration.
 * It provides methods to initialize and access configuration settings.
 * @version 0.0.1-ALPHA.0
 * @since 02/10/2023-ALPHA-0
 */
public class ConfigurationManager {
    private static ConfigurationManager instance;

    private static final String JOINING_TITLE_KEY = "joining-title";
    private static final String TITLE_KEY = "title";
    private static final String SUBTITLE_KEY = "subtitle";
    private static final String FADE_IN_KEY = "fade-in";
    private static final String STAY_KEY = "stay";
    private static final String FADE_OUT_KEY = "fade-out";

    private ConfigurationManager() {
        // Private constructor to enforce singleton pattern
    }

    /**
     * Get the instance of the ConfigurationManager.
     *
     * @return The ConfigurationManager instance.
     */
    public static synchronized ConfigurationManager get() {
        if (instance == null) instance = new ConfigurationManager();
        return instance;
    }

    /**
     * Initialize the configuration manager with default configuration settings.
     * This method loads the default configuration from the plugin.
     *
     * @param plugin The UHCPantuflas plugin instance.
     */
    public void init(UHCPantuflas plugin) {
        // Save the default configuration if it does not exist
        plugin.saveDefaultConfig();
        // Get the FileConfiguration instance for the plugin's configuration
        FileConfiguration config = plugin.getConfig();

        // Create a joining title from configuration values
        createJoiningTitle(Objects.requireNonNull(config.getConfigurationSection(JOINING_TITLE_KEY)));
    }

    /**
     * Create a joining title based on configuration settings and set it using TitleManager.
     *
     * @param joiningTitleSection The configuration section containing joining title settings.
     */
    private void createJoiningTitle(ConfigurationSection joiningTitleSection) {
        Title joiningTitle = new Title(
                joiningTitleSection.getString(TITLE_KEY, ""),
                joiningTitleSection.getString(SUBTITLE_KEY, ""),
                joiningTitleSection.getInt(FADE_IN_KEY, 0),
                joiningTitleSection.getInt(STAY_KEY, 0),
                joiningTitleSection.getInt(FADE_OUT_KEY, 0)
        );

        TitleManager.get().setJoiningTitle(joiningTitle);
    }
}
